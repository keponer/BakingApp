package com.example.arg_a.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.arg_a.bakingapp.data.Baked;
import com.example.arg_a.bakingapp.data.Step;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewStepActivity extends AppCompatActivity {

    private List<Step> stepList;
    private int id;

    @BindView(R.id.exo_player)
    SimpleExoPlayerView simpleExoPlayerView;
    @BindView(R.id.next_step)
    Button nextStep;
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

        stepDescription.setText(stepList.get(id).getDescription());


        Log.d("viewStepActivity", stepList.get(id).toString());
    }

    public void nextStep(View view){

        id++;
        setUI();

    }

    public void setUI(){

        stepDescription.setText(stepList.get(id).getDescription());


    }


}
