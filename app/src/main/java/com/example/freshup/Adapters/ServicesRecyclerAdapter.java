package com.example.freshup.Adapters;

import android.app.Activity;
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
import com.example.freshup.SharedPrefrences.Common;
import com.example.freshup.Util.App;
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
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        final GetHomeDataModel model=list.get(i);
        myViewHolder.title.setText(model.getDetails().get(i).getTitle());
        Picasso.with(context).load(model.getDetails().get(i).getImage1()).into(myViewHolder.main);
        Picasso.with(context).load(model.getDetails().get(i).getImage2()).into(myViewHolder.background);
        final String id=model.getDetails().get(i).getId();
        myViewHolder.services_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getSingleton().setService_id(id);
                Common.SaveToken((Activity) context,"Service title"+id,model.getDetails().get(i).getTitle());
                Common.SaveToken((Activity) context,"Service background"+id,model.getDetails().get(i).getImage2());
                Common.SaveToken((Activity) context,"Service image"+id,model.getDetails().get(i).getImage1());
                Intent intent=new Intent(context, SubServicesActivity.class);
                context.startActivity(intent);
            }
        });


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

        }
    }
}
