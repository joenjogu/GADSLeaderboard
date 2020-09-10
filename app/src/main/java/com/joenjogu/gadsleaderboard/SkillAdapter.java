package com.joenjogu.gadsleaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.SkillViewHolder> {

    private LayoutInflater mInflater;
    private List<SkillIQ> skillIQS;

    public SkillAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    static class SkillViewHolder extends RecyclerView.ViewHolder{
        private final ImageView iqImageView;
        private final TextView iqName;
        private final TextView iqSkillIQ;
        private final TextView iqCountry;

        private SkillViewHolder(@NonNull View itemView) {
            super(itemView);
            iqImageView = itemView.findViewById(R.id.iv_top_learner);
            iqName = itemView.findViewById(R.id.tv_iq_name);
            iqSkillIQ = itemView.findViewById(R.id.tv_iq_skilliq);
            iqCountry = itemView.findViewById(R.id.tv_iq_country);
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
        if (skillIQS != null){
            SkillIQ skill = skillIQS.get(position);

            holder.iqName.setText(skill.getName());
            holder.iqSkillIQ.setText(String.format("%s Skill IQ Score,", skill.getScore().toString()));
            holder.iqCountry.setText(skill.getCountry());

            Glide.with(holder.itemView.getContext()).load(skill.getBadgeUrl()).into(holder.iqImageView);
        }
    }

    @Override
    public int getItemCount() {
        if (skillIQS != null){
            return skillIQS.size();
        }else {
            return 0;
        }

    }


    public void setSkillIQ(List<SkillIQ> skillIQList) {
        this.skillIQS = skillIQList;
        notifyDataSetChanged();
    }
}
