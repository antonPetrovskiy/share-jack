package tk.bugnotwolf.sharejack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ClientActivity extends AppCompatActivity {

    Button playButton;
    Button disconnectStreamButton;
    Button connectStreamButton;
    Button muteButton;
    private boolean muted;

    MusicPlayer musicPlayer = new MusicPlayer(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        playButton = (Button)findViewById(R.id.playButton);
        connectStreamButton = (Button)findViewById(R.id.connectstreamButton);
        disconnectStreamButton = (Button)findViewById(R.id.disconnectStreamButton);
        muteButton = (Button)findViewById(R.id.muteButton);

        connectStreamButton.setEnabled(true);
        disconnectStreamButton.setEnabled(false);
        playButton.setEnabled(false);
        muteButton.setEnabled(false);
    }

    public void connectStreamButton(View view){
        musicPlayer.setFromServer("https://sharejack.tk/audio/ADC17605.mp3");
        connectStreamButton.setEnabled(false);
        playButton.setEnabled(true);
        disconnectStreamButton.setEnabled(true);
        muteButton.setEnabled(true);
    }

    public void disconnectStreamButton(View view){
        musicPlayer.releaseMP();
        connectStreamButton.setEnabled(true);
        playButton.setEnabled(false);
        disconnectStreamButton.setEnabled(false);
        muteButton.setEnabled(false);
    }

    public void playButton(View view){
        musicPlayer.startAudio();
        playButton.setEnabled(false);
    }

    public void muteButton(View view){

        if(!muted){
            musicPlayer.releaseMP();
        }else{
            musicPlayer.rebootStream();
        }

    }

}
