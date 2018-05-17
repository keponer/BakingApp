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

import com.example.arg_a.bakingapp.data.Baked;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeCardAdapter extends RecyclerView.Adapter<RecipeCardAdapter.RecipeCardAdapterViewHolder> {

    private Context context;
    private List<Baked> bakedList;

    public RecipeCardAdapter(Context context){
        this.context    = context;
    }

    @NonNull
    @Override
    public RecipeCardAdapter.RecipeCardAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutID = R.layout.recipe_card;

        View view = LayoutInflater.from(context).inflate(layoutID, parent, false);

        return new RecipeCardAdapter.RecipeCardAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeCardAdapter.RecipeCardAdapterViewHolder holder, int position) {

        Log.d("bind", Integer.toString(position));
        Baked baked = bakedList.get(position);

        holder.bakedName.setText(baked.getName());
        holder.bakedServings.setText(Integer.toString(baked.getServings()));
        holder.bakedCard.setTag(baked.getId());

    }

    @Override
    public int getItemCount() {
        if(bakedList == null) return 0;
        return bakedList.size();
    }

    public void updateBakedList(List<Baked> bakedList){
        this.bakedList = bakedList;

        notifyDataSetChanged();
    }

    public class RecipeCardAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.baked_name)
        TextView bakedName;
        @BindView(R.id.baked_servings)
        TextView bakedServings;
        @BindView(R.id.baked_card)
        CardView bakedCard;

        public RecipeCardAdapterViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            bakedCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("clickado", "clickado");
                    getAdapterPosition();
                }
            });

        }
    }
}
