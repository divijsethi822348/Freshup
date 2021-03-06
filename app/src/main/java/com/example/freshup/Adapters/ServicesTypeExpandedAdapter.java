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
import android.widget.Toast;

import com.example.freshup.Models.GetServicesDataModel;
import com.example.freshup.R;

import java.util.ArrayList;
import java.util.List;

public class ServicesTypeExpandedAdapter extends RecyclerView.Adapter<ServicesTypeExpandedAdapter.MyViewHolder> {
    Context context;
    List<GetServicesDataModel.SubSubService> list2;
    Boolean status=false;
    List<String> id=new ArrayList<>();

    public ServicesTypeExpandedAdapter(Context context, List<GetServicesDataModel.SubSubService> list2) {
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
    public void onBindViewHolder(@NonNull final ServicesTypeExpandedAdapter.MyViewHolder myViewHolder, final int i) {
        final GetServicesDataModel.SubSubService model=list2.get(i);
        myViewHolder.sub_service_title.setText(model.getTitle());
        myViewHolder.sub_service_price.setText("Rs. "+model.getPrice());
        myViewHolder.sub_sub_service_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status==false){
                    if (id.contains(model.getId())){

                    }else {
                        id.add(model.getId());
                        myViewHolder.radio.setImageResource(R.drawable.ic_radio_on_button);
                        status=true;
                        Toast.makeText(context, "position: "+i+" title: "+model.getTitle()+" id: "+model.getId(), Toast.LENGTH_SHORT).show();
                    }

                    }else if (status==true){
                    if (id.contains(model.getId())){
                        id.remove(model.getId());
                        myViewHolder.radio.setImageResource(R.drawable.ic_circle_shape_outline);
                        status=false;
                    }
                    else {
                        id.add(model.getId());
                        myViewHolder.radio.setImageResource(R.drawable.ic_radio_on_button);
                        status=true;
                        Toast.makeText(context, "position: "+i+" title: "+model.getTitle()+" id: "+model.getId(), Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return list2.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sub_service_title,sub_service_price;
        ImageView radio;
        CardView sub_sub_service_card;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sub_service_title=itemView.findViewById(R.id.sub_services_title);
            sub_service_price=itemView.findViewById(R.id.price);
            radio=itemView.findViewById(R.id.radio);
            sub_sub_service_card=itemView.findViewById(R.id.sub_sub_service_card);

        }
    }
}
