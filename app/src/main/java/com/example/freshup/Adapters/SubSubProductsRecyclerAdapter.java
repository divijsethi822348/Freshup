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
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.freshup.Activities.NavigatorActivity;
import com.example.freshup.Fragments.CartFragment;
import com.example.freshup.Models.AddToCartModel;
import com.example.freshup.Models.SingleProductCategoryModel;
import com.example.freshup.R;
import com.example.freshup.SharedPrefrences.Common;
import com.example.freshup.Util.App;
import com.example.freshup.ViewModels.CartViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.freshup.Util.App.getContext;

public class SubSubProductsRecyclerAdapter extends RecyclerView.Adapter<SubSubProductsRecyclerAdapter.MyViewHolder> {
    Context context;
    List<SingleProductCategoryModel.Product> products;

    int quantity=0;
    Add_To_Cart_Click add_to_cart_click;
    Quantity_Change quantity_change;
    GO_To_Cart_Click go_to_cart_click;

    public interface Add_To_Cart_Click{
        void choose(int position);
    }

    public interface GO_To_Cart_Click{
        void choose();
    }
    public interface Quantity_Change{
        void newQuantity(int number);
    }


    public SubSubProductsRecyclerAdapter(Context context, List<SingleProductCategoryModel.Product> products,
                                         Quantity_Change quantity_change, Add_To_Cart_Click add_to_cart_click,
                                         GO_To_Cart_Click go_to_cart_click) {
        this.context = context;
        this.products = products;
        this.add_to_cart_click = add_to_cart_click;
        this.quantity_change = quantity_change;
        this.go_to_cart_click = go_to_cart_click;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.sub_sub_products_recycler_item_view,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        final SingleProductCategoryModel.Product product=products.get(i);
        myViewHolder.name.setText(product.getTitle());
        myViewHolder.price.setText("Rs. "+product.getPrice());
        final String productId=product.getId();
        App.getSingleton().setProductId(productId);
        String logo=product.getProductImage();
        String[] image=logo.split(",");
        String product_logo=image[0];
        final String addToCartStatus=product.getAddToCartStatus();
        App.getSingleton().setAddToCartStatus(addToCartStatus);
        if (addToCartStatus.equalsIgnoreCase("1")){
            myViewHolder.add_to_cart.setVisibility(View.GONE);
            myViewHolder.go_to_cart.setVisibility(View.VISIBLE);
            myViewHolder.quantity_button.setVisibility(View.GONE);
        }else if (addToCartStatus.equalsIgnoreCase("0")){
            myViewHolder.add_to_cart.setVisibility(View.VISIBLE);
            myViewHolder.go_to_cart.setVisibility(View.GONE);
            myViewHolder.quantity_button.setVisibility(View.VISIBLE);
        }
        Toast.makeText(context, "cart status "+product.getAddToCartStatus(), Toast.LENGTH_SHORT).show();
        Picasso.with(context).load(product_logo).into(myViewHolder.pic);





    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ElegantNumberButton.OnValueChangeListener {
        CardView product;
        TextView name;
        ImageView pic;
        Button add_to_cart,go_to_cart;
        ElegantNumberButton quantity_button;
        TextView price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pic=itemView.findViewById(R.id.sub_sub_products_image);
            product=itemView.findViewById(R.id.sub_sub_products_card);
            name=itemView.findViewById(R.id.sub_sub_products_title);
            add_to_cart=itemView.findViewById(R.id.add_to_cart);
            quantity_button=itemView.findViewById(R.id.quantity_button);
            price=itemView.findViewById(R.id.product_price);
            go_to_cart=itemView.findViewById(R.id.go_to_cart);
            add_to_cart.setOnClickListener(this);
            quantity_button.setOnValueChangeListener(this);
            go_to_cart.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.add_to_cart:
                    add_to_cart_click.choose(getLayoutPosition());
                    break;

                case R.id.go_to_cart:
                    go_to_cart_click.choose();
            }
        }

        @Override
        public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
            quantity_change.newQuantity(newValue);
        }
    }


}
