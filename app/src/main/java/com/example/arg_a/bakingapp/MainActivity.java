package com.example.arg_a.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.arg_a.bakingapp.Utilities.BackingAPI;
import com.example.arg_a.bakingapp.data.Baked;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BackingAPI.getBaked(getApplicationContext(), "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json",
                new BackingAPI.VolleyCallback() {
                    @Override
                    public void onSuccess(ArrayList<Baked> arrayList) {


                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
