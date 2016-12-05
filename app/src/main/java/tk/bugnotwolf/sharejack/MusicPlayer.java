package tk.bugnotwolf.sharejack;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;


/**
 * Created by tosch on 01.12.2016.
 */

public class MusicPlayer {
    MediaPlayer mPlayer = new MediaPlayer();
    AppCompatActivity activity;


    public MusicPlayer(AppCompatActivity a){
        activity = a;
    }



    public void setFromPath(){

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

    public void setFromUri(){
        //Uri myUri = Uri.parse("http://stream.basso.fi:8000/stream");
        Uri myUri = Uri.parse("android.resource://tk.bugnotwolf.sharejack/" + R.raw.song);
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mPlayer.setDataSource(activity, myUri);
            mPlayer.prepare();
            Toast.makeText(activity, "Файл загружен", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(activity, "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        }




    }

    public void startAudio(){
        if(checkAudio())
            mPlayer.start();
    }

    public void pauseAudio(){
        if(checkAudio())
            mPlayer.pause();
    }

    public void stopAudio(){
        if(checkAudio())
            stop();
    }

    private void stop(){
        mPlayer.stop();
        try {
            mPlayer.prepare();
            mPlayer.seekTo(0);
        }
        catch (Throwable t) {
            Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkAudio(){
        if(!(mPlayer.getDuration() > 0)){
            Toast toast = Toast.makeText(activity,
                    "Выберите файл", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }else{
            return true;
        }

    }




}
