package com.xykj.demo.adapter;

import android.content.Context;
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

import java.util.List;

public class CrosswiseAdapter extends RecyclerView.Adapter<CrosswiseAdapter.CrossWiseViewHolder> {
    private List<Story_block> list;
    private Context context;

    public CrosswiseAdapter(List<Story_block> list, Context context) {
        this.list = list;
        this.context = context;
    }

    class CrossWiseViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView imageView;
        TextView textView;
//
//        private TextView story_title;
//        private ImageView story_img;

        public CrossWiseViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            imageView = (ImageView)itemView.findViewById(R.id.story_image);
            textView = (TextView)itemView.findViewById(R.id.story_title);
        }
    }

    @NonNull
    @Override
    //创建新的View,被LayoutManager所调用

    public CrossWiseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.story_crosswise_block_item, viewGroup, false);
        return new CrossWiseViewHolder(view);
    }

    //      OnBindViewHolder():将数据与界面进行绑定
    @Override
    public void onBindViewHolder(@NonNull CrossWiseViewHolder crossWiseViewHolder, int i) {

        Story_block storyBlock = list.get(i);
        crossWiseViewHolder.textView.setText(storyBlock.getStory_title());
        Glide.with(context).load(storyBlock.getStory_imgId()).into(crossWiseViewHolder.imageView);

    }
    //      getItemCount() :返回数据的数量
    @Override
    public int getItemCount() {
        return list.size();
    }
}
