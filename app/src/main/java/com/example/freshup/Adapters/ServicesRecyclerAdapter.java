package com.example.freshup.Adapters;

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

import com.example.freshup.Activities.SubServicesActivity;
import com.example.freshup.Models.GetHomeDataModel;
import com.example.freshup.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ServicesRecyclerAdapter extends RecyclerView.Adapter<ServicesRecyclerAdapter.MyViewHolder> {
    Context context;
    List<GetHomeDataModel> list;

    public ServicesRecyclerAdapter(Context context, List<GetHomeDataModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.services_recycler_item_view,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        GetHomeDataModel model=list.get(i);
        myViewHolder.title.setText(model.getDetails().get(i).getTitle());
        Picasso.with(context).load(model.getDetails().get(i).getImage1()).into(myViewHolder.main);
        Picasso.with(context).load(model.getDetails().get(i).getImage2()).into(myViewHolder.background);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView background,main;
        CardView services_card;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.services_title);
            background=itemView.findViewById(R.id.services_background_image);
            main= itemView.findViewById(R.id.services_image);
            services_card=itemView.findViewById(R.id.services_card);
            services_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, SubServicesActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
