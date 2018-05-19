package com.example.arg_a.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.arg_a.bakingapp.Utilities.BackingAPI;
import com.example.arg_a.bakingapp.data.Baked;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recipe_card_recyclerView)
    RecyclerView recipeRecyclerView;

    private RecipeCardAdapter recipeCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        recipeCardAdapter = new RecipeCardAdapter(getApplicationContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recipeRecyclerView.setLayoutManager(layoutManager);
        recipeRecyclerView.setItemAnimator(new DefaultItemAnimator());
        recipeRecyclerView.setAdapter(recipeCardAdapter);

        BackingAPI.getBaked(getApplicationContext(), "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json",
                new BackingAPI.VolleyCallback() {
                    @Override
                    public void onSuccess(ArrayList<Baked> arrayList) {
                        recipeCardAdapter.updateBakedList(arrayList);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
