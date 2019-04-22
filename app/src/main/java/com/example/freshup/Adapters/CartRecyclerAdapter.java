package com.example.freshup.Adapters;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.freshup.Models.AddToCartModel;
import com.example.freshup.Models.GetAddToCartListModel;
import com.example.freshup.R;
import com.example.freshup.SharedPrefrences.Common;
import com.example.freshup.Util.App;
import com.example.freshup.ViewModels.CartViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.MyViewHolder> {
    Context context;
    List<GetAddToCartListModel> list;
    CartViewModel viewModel;
    Quantity quantity_face;
    Delete delete;


    public interface Quantity{
        void choose(int quantity);
    }
    public interface Delete{
        void choose(int position);
    }

    public CartRecyclerAdapter(Context context, List<GetAddToCartListModel> list, Quantity quantity_face, Delete delete) {
        this.context = context;
        this.list = list;
        this.quantity_face = quantity_face;
        this.delete = delete;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.cart_item_recycler_item_view,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        final GetAddToCartListModel model=list.get(i);
        String image=model.getDetails().get(i).getProductImage().toString();
        String[] images=image.split(",");
        Picasso.with(context).load(images[0]).into(myViewHolder.image);
        myViewHolder.quantity.setNumber(model.getDetails().get(i).getQuantity());
        myViewHolder.description.setText(model.getDetails().get(i).getDescription().toString());
        myViewHolder.price.setText("Rs. "+model.getDetails().get(i).getPrice().toString());
        myViewHolder.name.setText(model.getDetails().get(i).getTitle().toString());




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements ElegantNumberButton.OnValueChangeListener, View.OnClickListener {
        ImageView image,delete_item;
        TextView price,description,name;
        ElegantNumberButton quantity;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            viewModel= ViewModelProviders.of((FragmentActivity) context).get(CartViewModel.class);
            image=itemView.findViewById(R.id.products_image_cart);
            name=itemView.findViewById(R.id.products_title_cart);
            price=itemView.findViewById(R.id.product_price_cart);
            description=itemView.findViewById(R.id.description);
            quantity=itemView.findViewById(R.id.quantity_button_cart);
            delete_item=itemView.findViewById(R.id.delete_item);
            delete_item.setOnClickListener(this);
            quantity.setOnValueChangeListener(this);

        }

        @Override
        public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
            quantity_face.choose(newValue);
        }

        @Override
        public void onClick(View v) {
            delete.choose(getLayoutPosition());
        }
    }
}
