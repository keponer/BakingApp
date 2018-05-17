package com.example.arg_a.bakingapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class RecipeCardAdapter extends RecyclerView.Adapter<RecipeCardAdapter.RecipeCardAdapterViewHolder> {

    @NonNull
    @Override
    public RecipeCardAdapter.RecipeCardAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeCardAdapter.RecipeCardAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecipeCardAdapterViewHolder extends RecyclerView.ViewHolder {
        public RecipeCardAdapterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
