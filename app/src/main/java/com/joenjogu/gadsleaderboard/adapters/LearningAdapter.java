package com.joenjogu.gadsleaderboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.joenjogu.gadsleaderboard.R;
import com.joenjogu.gadsleaderboard.models.LearningHours;

import java.util.List;

public class LearningAdapter extends RecyclerView.Adapter<LearningAdapter.LearningViewHolder> {

    private LayoutInflater mInflater;
    private List<LearningHours> learningHours;

    public LearningAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    static class LearningViewHolder extends RecyclerView.ViewHolder{
        private final ImageView hoursImageView;
        private final TextView hoursName;
        private final TextView hoursLearningHours;
        private final TextView hoursCountry;

        public LearningViewHolder(@NonNull View itemView) {
            super(itemView);

            hoursImageView = itemView.findViewById(R.id.iv_top_learner);
            hoursName = itemView.findViewById(R.id.tv_hours_name);
            hoursLearningHours = itemView.findViewById(R.id.tv_hours_learninghours);
            hoursCountry = itemView.findViewById(R.id.tv_hours_country);
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
        if (learningHours !=  null){
            LearningHours hours = learningHours.get(position);

            holder.hoursName.setText(hours.getName());
            holder.hoursLearningHours.setText(String.format("%s Learning Hours,",hours.getHours().toString()));
            holder.hoursCountry.setText(hours.getCountry());

            Glide.with(holder.itemView.getContext()).load(hours.getBadgeUrl()).into(holder.hoursImageView);
        }

    }

    @Override
    public int getItemCount() {
        if (learningHours != null){
            return learningHours.size();
        }else {
            return 0;
        }
    }


    public void setLearningHours(List<LearningHours> learningHoursList) {
        this.learningHours = learningHoursList;
        notifyDataSetChanged();
    }
}
