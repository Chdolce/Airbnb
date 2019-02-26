package com.xykj.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xykj.demo.Class.Story_block;
import com.xykj.demo.R;
import com.xykj.demo.activity.StoryActivity;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder>{

    private Context mContext;
    private List<Story_block> mstoryblock;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView storyImage;
        TextView storynameText;
        TextView storytitleText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = (CardView)itemView;
            cardView.setPreventCornerOverlap(false); //去边框
            storyImage = (ImageView)itemView.findViewById(R.id.story_image);
            storynameText = (TextView) itemView.findViewById(R.id.story_name);
            storytitleText = (TextView)itemView.findViewById(R.id.story_title);

        }
    }

    public StoryAdapter(List<Story_block> storyBlockList){
        mstoryblock = storyBlockList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(mContext == null){
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.story_block_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Story_block storyBlock = mstoryblock.get(position);
                Intent intent = new Intent(mContext,StoryActivity.class);
                intent.putExtra(StoryActivity.Story_imgID, storyBlock.getStory_imgId());
                intent.putExtra(StoryActivity.Story_title,storyBlock.getStory_title());
                intent.putExtra(StoryActivity.Story_name,storyBlock.getStory_place());
                intent.putExtra(StoryActivity.Story_content,storyBlock.getStory_content());
                intent.putExtra(StoryActivity.Author_name,storyBlock.getAuthor_name());
                intent.putExtra(StoryActivity.Author_img,storyBlock.getAuthor_img());
                intent.putExtra(StoryActivity.Author_id,storyBlock.getAuthor_id());
                intent.putExtra(StoryActivity.Story_img,storyBlock.getStory_img());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Story_block storyBlock = mstoryblock.get(position);
        holder.storynameText.setText(storyBlock.getStory_place());
        holder.storytitleText.setText(storyBlock.getStory_title());
      //  Glide.with(mContext).load(storyBlock.getStory_imgId()).into(holder.storyImage);
        Glide.with(mContext).load(storyBlock.getStory_img()).into(holder.storyImage);
    }

    @Override
    public int getItemCount() {
        return mstoryblock.size();
    }
}
