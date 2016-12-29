package tk.bugnotwolf.sharejack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class ServerActivity extends AppCompatActivity {

    Button playButton;
    Button pauseButton;
    Button stopButton;
    Button shareButton;
    Button setFileButton;
    private SeekBar seekBar;
    MusicPlayer musicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        initViews();
        musicPlayer = new MusicPlayer(this);
        setFileButton.setEnabled(true);
        playButton.setEnabled(false);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
        shareButton.setEnabled(false);
    }

    private void initViews() {
        playButton = (Button)findViewById(R.id.playButton);
        pauseButton = (Button)findViewById(R.id.pauseButton);
        stopButton = (Button)findViewById(R.id.stopButton);
        shareButton = (Button)findViewById(R.id.shareButton);
        setFileButton = (Button)findViewById(R.id.setFileButton);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
    }

    public void setFileButton(View view){
        musicPlayer.setFromPath("android.resource://tk.bugnotwolf.sharejack/" + R.raw.song);
        playButton.setEnabled(true);
        pauseButton.setEnabled(true);
        shareButton.setEnabled(true);

        seekBar.setMax(musicPlayer.getPlayer().getDuration());
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                seekChange(v);
                return false;
            }
        });
    }

    private void seekChange(View v){
        if (musicPlayer.getPlayer().isPlaying()) {
            SeekBar sb = (SeekBar) v;
            musicPlayer.getPlayer().seekTo(sb.getProgress());
        }
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
