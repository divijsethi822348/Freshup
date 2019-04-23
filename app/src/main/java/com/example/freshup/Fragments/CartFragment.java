package com.example.freshup.Fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshup.Adapters.CartRecyclerAdapter;
import com.example.freshup.Models.AddToCartModel;
import com.example.freshup.Models.GetAddToCartListModel;
import com.example.freshup.Models.GetServicesDataModel;
import com.example.freshup.R;
import com.example.freshup.SharedPrefrences.Common;
import com.example.freshup.Util.App;
import com.example.freshup.Util.CommonUtils;
import com.example.freshup.ViewModels.CartViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {
    Context context;
    TextView total_price, cart_empty;
    Button buy;
    RecyclerView cart_item_recycler;
    CartViewModel viewModel;
    List<GetAddToCartListModel> list = new ArrayList<>();
    List<GetAddToCartListModel.Detail> details = new ArrayList<>();
    String userId, productId, Id;
    String Quantity;
    CartRecyclerAdapter adapter;


    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        viewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        total_price = view.findViewById(R.id.total_price);
        cart_empty = view.findViewById(R.id.cart_empty);
        buy = view.findViewById(R.id.buy_button_cart);


        cart_item_recycler = view.findViewById(R.id.cart_item_recycler);

        userId = Common.GetToken(getActivity(), "ID");

        getData();


        return view;


    }

    public void getData() {
        CommonUtils.showProgress(getActivity());
        viewModel.getCartData(getActivity(), userId).observe(this, new Observer<GetAddToCartListModel>() {
            @Override
            public void onChanged(@Nullable final GetAddToCartListModel getAddToCartListModel) {
                if (getAddToCartListModel.getSuccess().equalsIgnoreCase("1")) {
                    cart_item_recycler.setLayoutManager(new LinearLayoutManager(context));
                    cart_empty.setVisibility(View.GONE);
                    cart_item_recycler.setVisibility(View.VISIBLE);

                    if (list != null) {
                        list.clear();
                    }
                    if (details!=null){
                        details.clear();
                    }
                    for (int j = 0; j < getAddToCartListModel.getDetails().size(); j++) {
                        GetAddToCartListModel model = new GetAddToCartListModel();
                        GetAddToCartListModel.Detail detail = new GetAddToCartListModel.Detail();

                        detail.setProductImage(getAddToCartListModel.getDetails().get(j).getProductImage());
                        detail.setDescription(getAddToCartListModel.getDetails().get(j).getDescription());
                        detail.setPrice(getAddToCartListModel.getDetails().get(j).getPrice());
                        Quantity = getAddToCartListModel.getDetails().get(j).getQuantity();
                        detail.setQuantity(Quantity);
                        detail.setTitle(getAddToCartListModel.getDetails().get(j).getTitle());
                        productId = getAddToCartListModel.getDetails().get(j).getProductId();
                        detail.setProductId(productId);
                        Id = getAddToCartListModel.getDetails().get(j).getId();
                        detail.setId(Id);
                        total_price.setText("Rs. " + getAddToCartListModel.getTotalPrice());
                        details.add(detail);
                        model.setDetails(details);
                        list.add(model);
                    }


                    adapter = new CartRecyclerAdapter(getActivity(), list, new CartRecyclerAdapter.Quantity() {
                        @Override
                        public void choose(final int quantity) {
                            viewModel.addToCartItems(getActivity(), userId, productId, quantity).observe(getViewLifecycleOwner(), new Observer<AddToCartModel>() {
                                @Override
                                public void onChanged(@Nullable AddToCartModel addToCartModel) {
                                    Quantity = String.valueOf(quantity);
                                    getData();

                                }
                            });

                        }
                    }, new CartRecyclerAdapter.Delete() {
                        @Override
                        public void choose(int position) {
                            viewModel.delete(getActivity(), Id).observe(getViewLifecycleOwner(), new Observer<Map>() {
                                @Override
                                public void onChanged(@Nullable Map map) {
                                    getData();
                                    total_price.setText("Rs. 0");
                                }
                            });
                        }
                    });
                    adapter.notifyDataSetChanged();
                    cart_item_recycler.setAdapter(adapter);
                    CommonUtils.dismiss();


                } else if (getAddToCartListModel.getSuccess().equalsIgnoreCase("0")) {
                    cart_item_recycler.setVisibility(View.GONE);
                    cart_empty.setVisibility(View.VISIBLE);
                    CommonUtils.dismiss();
                }
            }
        });
    }

}
