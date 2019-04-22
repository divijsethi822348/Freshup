package com.example.freshup.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.freshup.Models.BarberDetailsModel;
import com.example.freshup.R;

import java.util.List;

public class TimeSlotRecyclerAdapter extends RecyclerView.Adapter<TimeSlotRecyclerAdapter.MyViewHolder> {
    Context context;
    List<String> slot;

    public TimeSlotRecyclerAdapter(Context context, List<String> slot) {
        this.context = context;
        this.slot = slot;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.time_slot_recycler_item_view,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        String time=slot.get(i);
        myViewHolder.time_slot.setText(time);

    }

    @Override
    public int getItemCount() {
        return slot.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView time_slot_card;
        TextView time_slot;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            time_slot=itemView.findViewById(R.id.time_slot);
            time_slot_card=itemView.findViewById(R.id.time_slot_card);
        }
    }
}
