package com.xykj.demo.adapter;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.xykj.demo.Class.Order_block;
import com.xykj.demo.R;
import java.util.List;
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
        private Context mContext;
        private List<Order_block> mOrderList;

        static class ViewHolder extends RecyclerView.ViewHolder
        {
            CardView OrderView;
            ImageView show_order;
            TextView starttime;
            TextView endtime;
            TextView cost;
            TextView title;
            TextView guestcount;
            public ViewHolder(View view)
            {
                super(view);
                OrderView=(CardView) view;
                //去边框
                OrderView.setPreventCornerOverlap (false);
                show_order=(ImageView)view.findViewById(R.id.show_order);
                starttime=(TextView)view.findViewById(R.id.start);
                endtime = (TextView)view.findViewById(R.id.end);
                cost = (TextView)view.findViewById(R.id.cost);
                title = (TextView)view.findViewById(R.id.title);
                guestcount = (TextView)view.findViewById(R.id.guestcount);
            }
        }
        public OrderAdapter(List<Order_block> order_block_List)
        {
            mOrderList=order_block_List;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(mContext == null){mContext = parent.getContext();}
            View view= LayoutInflater.from(mContext).inflate(R.layout.order_block_item,parent,false);
            return new ViewHolder(view);
        }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Order_block order=mOrderList.get(position);
        holder.show_order.setImageResource(order.getShow_house());
        holder.title.setText(order.getTitle());
        holder.cost.setText("¥"+order.getCost()+" ");
        holder.guestcount.setText(order.getGuestcount()+" ");
        holder.starttime.setText(order.getStart()+"");
        holder.endtime.setText(order.getEnd()+"");
    }
        @Override
        public int getItemCount() {
            return mOrderList.size();
        }
}



