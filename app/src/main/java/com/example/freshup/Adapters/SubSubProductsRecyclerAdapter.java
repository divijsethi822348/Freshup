package com.example.freshup.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.freshup.Models.SingleProductCategoryModel;
import com.example.freshup.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SubSubProductsRecyclerAdapter extends RecyclerView.Adapter<SubSubProductsRecyclerAdapter.MyViewHolder> {
    Context context;
    List<SingleProductCategoryModel.Product> products;

    public SubSubProductsRecyclerAdapter(Context context, List<SingleProductCategoryModel.Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.sub_sub_products_recycler_item_view,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        SingleProductCategoryModel.Product product=products.get(i);
        myViewHolder.name.setText(product.getTitle());
        Picasso.with(context).load(product.getProductImage()).into(myViewHolder.pic);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView product;
        TextView name;
        ImageView pic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pic=itemView.findViewById(R.id.sub_sub_products_image);
            product=itemView.findViewById(R.id.sub_sub_products_card);
            name=itemView.findViewById(R.id.sub_sub_products_title);


        }
    }
}
