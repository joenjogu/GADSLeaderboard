package com.joenjogu.gadsleaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.SkillViewHolder> {

    private LayoutInflater mInflater;

    public SkillAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    static class SkillViewHolder extends RecyclerView.ViewHolder{

        public SkillViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.iq_recyclerview_item, parent, false);
        return new SkillViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
