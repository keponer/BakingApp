package com.example.arg_a.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.arg_a.bakingapp.data.Baked;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsActivity extends AppCompatActivity {

    @BindView(R.id.activity_steps_recyclerView)
    RecyclerView activityStepsRecyclerView;

    private RecipeStepAdapter recipeStepAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        Baked baked = Parcels.unwrap(bundle.getParcelable("baked"));


        recipeStepAdapter = new RecipeStepAdapter(getApplicationContext(), baked.getSteps(), baked.getIngredients());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        activityStepsRecyclerView.setLayoutManager(layoutManager);
        activityStepsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        activityStepsRecyclerView.setAdapter(recipeStepAdapter);

        Log.d("StepsActivity", baked.getName());
    }
}
