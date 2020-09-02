package com.joenjogu.gadsleaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LearningAdapter extends RecyclerView.Adapter<LearningAdapter.LearningViewHolder> {

    private LayoutInflater mInflater;
    public LearningAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    static class LearningViewHolder extends RecyclerView.ViewHolder{

        public LearningViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public LearningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.hours_recyclerview_item, parent, false);
        return new LearningViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LearningViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
