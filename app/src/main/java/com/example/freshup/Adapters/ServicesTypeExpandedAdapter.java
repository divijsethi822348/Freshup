package com.example.freshup.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.freshup.R;

import java.util.List;

public class ServicesTypeExpandedAdapter extends RecyclerView.Adapter<ServicesTypeExpandedAdapter.MyViewHolder> {
    private Context context;
    List<String> list2;

    public ServicesTypeExpandedAdapter(Context context, List<String> list2) {
        this.context = context;
        this.list2 = list2;
    }

    @NonNull
    @Override
    public ServicesTypeExpandedAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.services_type_expanded_recycler_item,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesTypeExpandedAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.services_type_expanded_recycler_item_text_view.setText(list2.get(i));

    }

    @Override
    public int getItemCount() {
        return list2.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView services_type_expanded_recycler_item_text_view;
        ImageView radio;
        public Boolean status=false;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            services_type_expanded_recycler_item_text_view=itemView.findViewById(R.id.rservices_type_expanded_recycler_item_text_view);
            radio=itemView.findViewById(R.id.radio);
            radio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (status==false){
                        radio.setImageResource(R.drawable.ic_radio_on_button);
                        status=true;
                    }
                    else if (status==true){
                        radio.setImageResource(R.drawable.ic_circle_shape_outline);
                        status=false;
                    }

                }
            });
        }
    }
}
