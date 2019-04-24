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
import android.widget.Toast;

import com.example.freshup.Activities.SubSubProductActivity;
import com.example.freshup.Models.SingleProductCategoryModel;
import com.example.freshup.R;
import com.example.freshup.Util.App;
import com.example.freshup.Util.CommonUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SubProductsRecyclerAdapter extends RecyclerView.Adapter<SubProductsRecyclerAdapter.MyViewHolder> {
    Context context;
    List<SingleProductCategoryModel> list;
    List<SingleProductCategoryModel.Product> list2=new ArrayList<>();

    public SubProductsRecyclerAdapter(Context context, List<SingleProductCategoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SubProductsRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.sub_products_recycler_item_view,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubProductsRecyclerAdapter.MyViewHolder myViewHolder, final int i) {
        final SingleProductCategoryModel model=list.get(i);
        myViewHolder.sub_products_title.setText(model.getDetails().get(i).getTitle());
        Picasso.with(context).load(model.getDetails().get(i).getImage()).into(myViewHolder.sub_products_image);
//        list2.add(model.getDetails().get(i).getProduct().get(i));

        myViewHolder.sub_products_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (model.getDetails().get(i).getProduct().size()>0){
                   App.getSingleton().setSubSubProducts(list.get(i).getDetails().get(i).getProduct());
                    Intent intent=new Intent(context, SubSubProductActivity.class);
                    String id=model.getDetails().get(i).getCategoryId();
                    intent.putExtra("id",id);
                    context.startActivity(intent);

                }else {

                    Toast.makeText(context, "No items in it", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView sub_products_card;
        ImageView sub_products_image;
        TextView sub_products_title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sub_products_card=itemView.findViewById(R.id.sub_products_card);
            sub_products_image=itemView.findViewById(R.id.sub_products_image);
            sub_products_title=itemView.findViewById(R.id.sub_products_title);


        }
    }
}
