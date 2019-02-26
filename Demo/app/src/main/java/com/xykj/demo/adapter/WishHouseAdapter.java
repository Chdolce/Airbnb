package com.xykj.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.xykj.demo.Class.house_block;
import com.xykj.demo.R;
import com.xykj.demo.activity.RoomActivity;

import java.util.List;

public class WishHouseAdapter extends RecyclerView.Adapter<WishHouseAdapter.ViewHolder> {
        private Context mContext;
        private List<house_block> HouseList;
        static class ViewHolder extends RecyclerView.ViewHolder
        {
            CardView houseView;
            RoundedImageView show_house;
            TextView price;
            TextView house_type;
            TextView house_name;
            public ViewHolder(View view)
            {
                super(view);
                houseView=(CardView) view;
                //去边框
                houseView.setPreventCornerOverlap (false);
                show_house=(RoundedImageView)view.findViewById(R.id.show_house);
                price=(TextView)view.findViewById(R.id.price);
                house_type = (TextView)view.findViewById(R.id.house_type);
                house_name = (TextView)view.findViewById(R.id.house_name);
            }
        }
        public WishHouseAdapter(List<house_block> HouseList) {
            this .HouseList = HouseList;
        }
        @Override
        public com.xykj.demo.adapter.WishHouseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(mContext == null){mContext = parent.getContext();}
            View view= LayoutInflater.from(mContext).inflate(R.layout.wish_block_item,parent,false);
            // 传参给RoomActivity
            final com.xykj.demo.adapter.WishHouseAdapter.ViewHolder holder = new com.xykj.demo.adapter.WishHouseAdapter.ViewHolder(view);
            holder.houseView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    house_block house_block = HouseList.get(position);
                    Intent intent = new Intent(mContext,RoomActivity.class);
                    intent.putExtra(RoomActivity.House_name,house_block.getHouse_name());
                    intent.putExtra(RoomActivity.House_type,house_block.getHouse_type());
                    intent.putExtra(String.valueOf(RoomActivity.House_price),house_block.getPrice());
                    //  intent.putExtra(String.valueOf(RoomActivity.House_show),house_block.getShow_house());
                    intent.putExtra(String.valueOf(RoomActivity.House_ID),house_block.getHouse_id());
                    intent.putExtra(RoomActivity.House_title,house_block.getHouse_title());
                    intent.putExtra(String.valueOf(RoomActivity.House_capacity),house_block.getHouse_capacity());
                    intent.putExtra(String.valueOf(RoomActivity.House_latitude),house_block.getHouse_latitude());
                    intent.putExtra(String.valueOf(RoomActivity.House_longitude),house_block.getHouse_longitude());
                    intent.putExtra(RoomActivity.House_country,house_block.getHouse_country());
                    intent.putExtra(RoomActivity.House_picture,house_block.getHouse_picture());
                    // Log.e("house_adapter","house_block.getHouse_country()");
                    intent.putExtra(RoomActivity.House_city,house_block.getHouse_city());
                    intent.putExtra(RoomActivity.House_province,house_block.getHouse_province());
                    mContext.startActivity(intent);
                }
            });
            return holder;
        }
        @Override
        public void onBindViewHolder(com.xykj.demo.adapter.WishHouseAdapter.ViewHolder holder, int position) {
            house_block house=HouseList.get(position);
            // holder.show_house.setImageResource(house.getShow_house());
            Glide.with(mContext).load(house.getHouse_picture()).into(holder.show_house);
            holder.house_type.setText(house.getHouse_type());
            holder.price.setText("¥"+house.getPrice()+"/晚");
            holder.house_name.setText(house.getHouse_title());
        }
        @Override
        public int getItemCount() {
            return HouseList.size();
        }
    }

