package com.example.arg_a.bakingapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.arg_a.bakingapp.data.Baked;
import com.example.arg_a.bakingapp.data.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewStepActivity extends AppCompatActivity {

    private List<Step> stepList;
    private int id;
    private SimpleExoPlayer player;

    @BindView(R.id.exo_player)
    PlayerView playerView;
    @BindView(R.id.next_step)
    Button nextStep;
    @BindView(R.id.previous_step)
    Button previousStep;
    @BindView(R.id.step_description)
    TextView stepDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_step);

        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        stepList = Parcels.unwrap(bundle.getParcelable("steps"));

        id = bundle.getInt("id");

        setUI();

        initializePlayer();
    }

    /**
     * On nextStep pressed add +1 to the current step
     * @param view
     */
    public void nextStep(View view){
        id++;
        setUI();
    }

    /**
     * On previousStep pressed add -1 to the current step
     * @param view
     */
    public void previousStep(View view){
        id--;
        setUI();
    }

    /**
     * Set the description the video and put invisible and visible the buttons
     */
    public void setUI(){
        stepDescription.setText(stepList.get(id).getDescription());

        if(id == 0){
            previousStep.setVisibility(View.INVISIBLE);
        }
        else if(id == stepList.size() - 1){
            nextStep.setVisibility(View.INVISIBLE);
        }
        else{
            previousStep.setVisibility(View.VISIBLE);
            nextStep.setVisibility(View.VISIBLE);
        }
        setVideo();
    }

    /**
     * Initialize the payer the first time and set the video
     */
    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(this),
                new DefaultTrackSelector(), new DefaultLoadControl());

        playerView.setPlayer(player);

        Uri uri = Uri.parse(stepList.get(id).getVideoURL());
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);

        player.setPlayWhenReady(false);
        //player.seekTo(currentWindow, playbackPosition);

    }

    /**
     * Set the video
     */
    private void setVideo(){
        Uri uri = Uri.parse(stepList.get(id).getVideoURL());
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);

        player.setPlayWhenReady(false);
    }

    /**
     * Build the MediaSource for the player
     * @param uri
     * @return
     */
    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("exoplayer-codelab")).
                createMediaSource(uri);
    }

}
