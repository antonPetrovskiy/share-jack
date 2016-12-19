package tk.bugnotwolf.sharejack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ClientActivity extends AppCompatActivity {

    Button playButton;
    Button streamButton;
    Button stopButton;

    MusicPlayer musicPlayer = new MusicPlayer(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        playButton = (Button)findViewById(R.id.playButton);
        streamButton = (Button)findViewById(R.id.streamButton);
        stopButton = (Button)findViewById(R.id.stopButton);

        playButton.setEnabled(false);
        stopButton.setEnabled(false);
    }

    public void setStreamButton(View view){
        musicPlayer.setFromServer("http://stream.basso.fi:8000/stream");
        playButton.setEnabled(true);
        streamButton.setEnabled(false);
    }

    public void playButton(View view){
        musicPlayer.startStreamAudio();
        stopButton.setEnabled(true);
        playButton.setEnabled(false);
    }

    public void stopButton(View view){
        musicPlayer.stopStreamAudio();
        stopButton.setEnabled(false);
        playButton.setEnabled(true);
    }

}
