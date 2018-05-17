package com.example.arg_a.bakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.arg_a.bakingapp.data.Ingredient;
import com.example.arg_a.bakingapp.data.Step;

import java.util.List;

public class RecipeStepAdapter extends RecyclerView.Adapter<RecipeStepAdapter.RecipeStepAdapterViewHolder> {

    private Context context;
    private List<Step> stepList;
    private List<Ingredient> ingredientList;

    public RecipeStepAdapter(Context context, List<Step> stepList, List<Ingredient> ingredientList){

        this.context        = context;
        this.stepList       = stepList;
        this.ingredientList = ingredientList;
    }

    @NonNull
    @Override
    public RecipeStepAdapter.RecipeStepAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeStepAdapter.RecipeStepAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecipeStepAdapterViewHolder extends RecyclerView.ViewHolder {
        public RecipeStepAdapterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
