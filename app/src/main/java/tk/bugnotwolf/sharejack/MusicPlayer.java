package tk.bugnotwolf.sharejack;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;
import android.media.MediaPlayer.OnPreparedListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;


/**
 * Created by tosch on 01.12.2016.
 */

public class MusicPlayer implements OnPreparedListener{
    private MediaPlayer mPlayer;
    AppCompatActivity activity;
    private String SERVER_ADDRESS;
    private boolean ready;


    public MusicPlayer(AppCompatActivity a){
        mPlayer = new MediaPlayer();
        activity = a;
    }



    public void setFromPath(String s){

        try {
            mPlayer.setDataSource(activity, Uri.parse(s));
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.prepare();
            Toast.makeText(activity, "Файл загружен", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(activity, "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        }
    }

    public void setFromBytes(byte arr []){
        try {
            String outputFile= Environment.getExternalStorageDirectory().getAbsolutePath();
            File path = new File(outputFile);

            // create temp file that will hold byte array
            File tempMp3 = File.createTempFile("song1", "mp3", path);
            tempMp3.deleteOnExit();
            FileOutputStream fos = new FileOutputStream(tempMp3);
            fos.write(arr);
            fos.close();

            // resetting mediaplayer instance to evade problems
            mPlayer.reset();

            // In case you run into issues with threading consider new instance like:
            // MediaPlayer mediaPlayer = new MediaPlayer();

            // Tried passing path directly, but kept getting
            // "Prepare failed.: status=0x1"
            // so using file descriptor instead
            FileInputStream fis = new FileInputStream(tempMp3);
            mPlayer.setDataSource(fis.getFD());

            mPlayer.prepare();

        } catch (IOException ex) {
            String s = ex.toString();
            ex.printStackTrace();
        }
    }

    public void setFromServer(String s){
        SERVER_ADDRESS = s;
        try {
            mPlayer.setDataSource(SERVER_ADDRESS);
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.setOnPreparedListener(this);
            mPlayer.prepareAsync();
        } catch (IOException e) {
            Toast.makeText(activity, "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        }
        Toast.makeText(activity, "Стрим с сервера готов", Toast.LENGTH_LONG).show();
    }

    public void startAudio(){
        if(ready)
            mPlayer.start();
    }

    public void startStreamAudio(){
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(SERVER_ADDRESS);
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.setOnPreparedListener(this);
            mPlayer.prepareAsync();

        } catch (IOException e) {
            Toast.makeText(activity, "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        }
        mPlayer.start();
    }

    public void pauseAudio(){
        mPlayer.pause();
    }

    public void stopAudio(){
        mPlayer.stop();
            try {
                mPlayer.prepareAsync();
                mPlayer.seekTo(0);
            }
            catch (Throwable t) {
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
    }

    public void stopStreamAudio(){
        mPlayer.stop();
        releaseMP();
    }

    public void rebootStream(){
        releaseMP();
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(SERVER_ADDRESS);
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.setOnPreparedListener(this);
            mPlayer.prepareAsync();

        } catch (IOException e) {
            Toast.makeText(activity, "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        }
        mPlayer.start();
    }

    private void releaseMP() {
        if (mPlayer != null) {
            try {
                mPlayer.release();
                mPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        ready = true;
        //TODO mPlayer.seekTo() current position and mPlayer.start() if music is playing now
    }

    public MediaPlayer getPlayer(){
        return mPlayer;
    }


}
