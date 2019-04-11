package com.example.freshup.Adapters;

import android.arch.lifecycle.ViewModelProviders;
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

import com.example.freshup.Activities.VetementsActivity;
import com.example.freshup.Models.GetHomeDataModel;
import com.example.freshup.R;
import com.example.freshup.ViewModels.ProductsViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductsRecyclerAdapter extends RecyclerView.Adapter<ProductsRecyclerAdapter.MyViewHolder> {
    List<GetHomeDataModel> list;
    Context context;

    public ProductsRecyclerAdapter(List<GetHomeDataModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.products_recycler_item_view,viewGroup,false);
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
        CardView products_card;
        ImageView background,main;
        TextView title;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            products_card=itemView.findViewById(R.id.products_card);
            background=itemView.findViewById(R.id.products_back_image);
            main=itemView.findViewById(R.id.products_image);
            title=itemView.findViewById(R.id.products_title);
            products_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, VetementsActivity.class);
                    context.startActivity(intent);
                }
            });

        }
    }
}
