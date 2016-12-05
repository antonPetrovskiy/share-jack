package tk.bugnotwolf.sharejack;

import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

/**
 * Created by tosch on 04.12.2016.
 */

public class MusicExoPlayer {

    AppCompatActivity activity;
    private ExoPlayer exoPlayer;

    public MusicExoPlayer(AppCompatActivity a){
        activity = a;

        Handler handler = new Handler();
        TrackSelector trackSelector = new DefaultTrackSelector(handler);
        LoadControl loadControl = new DefaultLoadControl();
        SimpleExoPlayer exoPlayer = ExoPlayerFactory.newSimpleInstance(activity, trackSelector, loadControl);

        Uri audioUri = Uri.parse("http://stream.basso.fi:8000/stream");
        DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("ExoPlayerDemo");
        ExtractorsFactory extractor = new DefaultExtractorsFactory();
        MediaSource audioSource = new ExtractorMediaSource(audioUri, dataSourceFactory, extractor, null, null);
        exoPlayer.prepare(audioSource);
        exoPlayer.setPlayWhenReady(true);
    }
}
