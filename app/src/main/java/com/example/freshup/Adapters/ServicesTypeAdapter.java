package com.example.freshup.Adapters;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshup.Models.GetHomeDataModel;
import com.example.freshup.Models.GetServicesDataModel;
import com.example.freshup.R;
import com.example.freshup.ViewModels.ServicesViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ServicesTypeAdapter extends RecyclerView.Adapter<ServicesTypeAdapter.MyViewHolder> {
    private Context context;
    List<GetServicesDataModel> list;
    ServicesTypeExpandedAdapter expandedAdapter;



    public ServicesTypeAdapter(Context context, List<GetServicesDataModel> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public ServicesTypeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.services_type_recycler_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesTypeAdapter.MyViewHolder myViewHolder, int i) {
        GetServicesDataModel model=list.get(i);
        myViewHolder.services_type.setText(model.getDetails().get(i).getTitle());
        myViewHolder.expanded_recycler.setLayoutManager(new LinearLayoutManager(context));

        expandedAdapter=new ServicesTypeExpandedAdapter(context,list.get(i).getDetails().get(i).getSubSubServices());

        myViewHolder.expanded_recycler.setAdapter(expandedAdapter);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView services_type;
        ImageView expand;
        RecyclerView expanded_recycler;
        LinearLayout expand_card;
        Boolean expanded=false;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            services_type=itemView.findViewById(R.id.services_type_text_view);
            expand=itemView.findViewById(R.id.btn_expand);
            expanded_recycler=itemView.findViewById(R.id.services_type_expanded_recycler_view);
            expand_card=itemView.findViewById(R.id.expand_card);
            expand_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Expanded", Toast.LENGTH_SHORT).show();

                    if (expanded==false){
                        expanded_recycler.setVisibility(View.VISIBLE);
                        expanded=true;
                        expand.setImageResource(R.drawable.arrow_up);
                    }
                    else if (expanded==true){
                        expanded_recycler.setVisibility(View.GONE);
                        expanded=false;
                        expand.setImageResource(R.drawable.arrow_down);

                    }


                }
            });

        }
    }
}
