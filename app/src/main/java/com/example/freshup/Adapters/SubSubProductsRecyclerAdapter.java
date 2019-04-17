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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    CartViewModel viewModel;
    int quantity=1;

    public SubSubProductsRecyclerAdapter(Context context, List<SingleProductCategoryModel.Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.sub_sub_products_recycler_item_view,viewGroup,false);
        viewModel= ViewModelProviders.of((FragmentActivity) context).get(CartViewModel.class);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        SingleProductCategoryModel.Product product=products.get(i);
        myViewHolder.name.setText(product.getTitle());
        final String productId=product.getId();
        App.getSingleton().setProductId(productId);
        String logo=product.getProductImage();
        String[] image=logo.split(",");
        String product_logo=image[0];
        Picasso.with(context).load(product_logo).into(myViewHolder.pic);

        myViewHolder.add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addToCartItems((Activity) context, Common.GetToken((Activity) context,"ID"),productId,quantity).observe((LifecycleOwner) context, new Observer<AddToCartModel>() {
                    @Override
                    public void onChanged(@Nullable AddToCartModel addToCartModel) {
                        Toast.makeText(context, "Total Price: "+addToCartModel.getTotalPrice(), Toast.LENGTH_SHORT).show();
                    }
                });
                Intent intent=new Intent(context, NavigatorActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView product;
        TextView name;
        ImageView pic;
        Button add_to_cart;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pic=itemView.findViewById(R.id.sub_sub_products_image);
            product=itemView.findViewById(R.id.sub_sub_products_card);
            name=itemView.findViewById(R.id.sub_sub_products_title);
            add_to_cart=itemView.findViewById(R.id.add_to_cart);


        }
    }
}
