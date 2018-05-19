package com.example.arg_a.bakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arg_a.bakingapp.data.Ingredient;
import com.example.arg_a.bakingapp.data.Step;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        int layoutID = R.layout.recipe_step_card;

        View view = LayoutInflater.from(context).inflate(layoutID, parent, false);

        return new RecipeStepAdapter.RecipeStepAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeStepAdapter.RecipeStepAdapterViewHolder holder, int position) {

        if(position == 0){
            holder.recipeStepName.setText("Ingredients");
            Log.d("steplist", stepList.toString());
        }
        else{
            holder.recipeStepName.setText(stepList.get(position-1).getDescription());
            Log.d(Integer.toString(position), stepList.get(position-1).getDescription());
        }

    }

    @Override
    public int getItemCount() {
        if(stepList == null) return 0;
        else return stepList.size() + 1;
    }

    public class RecipeStepAdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recipe_step_name)
        TextView recipeStepName;
        @BindView(R.id.recipe_step_cardView)
        CardView recipeStepCardView;

        public RecipeStepAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
