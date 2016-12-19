package tk.bugnotwolf.sharejack;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class ServerActivity extends AppCompatActivity {

    Button playButton;
    Button pauseButton;
    Button stopButton;
    Button shareButton;
    Button setFileButton;
    Button setStreamButton;
    MusicPlayer musicPlayer = new MusicPlayer(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        playButton = (Button)findViewById(R.id.playButton);
        pauseButton = (Button)findViewById(R.id.pauseButton);
        stopButton = (Button)findViewById(R.id.stopButton);
        shareButton = (Button)findViewById(R.id.shareButton);
        setFileButton = (Button)findViewById(R.id.setFileButton);

        setFileButton.setEnabled(false);
        playButton.setEnabled(false);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
        shareButton.setEnabled(false);

    }

    public void setFileButton(View view){
        musicPlayer.setFromPath("android.resource://tk.bugnotwolf.sharejack/" + R.raw.song);
        playButton.setEnabled(true);
        pauseButton.setEnabled(true);
        shareButton.setEnabled(true);
    }

    public void setStreamButton(View view){
        musicPlayer.setFromServer("http://stream.basso.fi:8000/stream");
        playButton.setEnabled(true);
        pauseButton.setEnabled(true);
        shareButton.setEnabled(true);
    }

    public void playButton(View view){
        musicPlayer.startAudio();
        stopButton.setEnabled(true);
        playButton.setEnabled(false);
        pauseButton.setEnabled(true);
    }

    public void pauseButton(View view){
        musicPlayer.pauseAudio();
        pauseButton.setEnabled(false);
        playButton.setEnabled(true);
        stopButton.setEnabled(true);
    }

    public void stopButton(View view){
        musicPlayer.stopAudio();
        playButton.setEnabled(true);
        stopButton.setEnabled(false);
        pauseButton.setEnabled(false);
    }

    public void shareButton(View view){
        shareButton.setEnabled(false);
    }


}
