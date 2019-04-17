package com.example.freshup.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshup.Models.BarberDetailsModel;
import com.example.freshup.R;
import com.example.freshup.Util.App;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Calendar;
public class BarberRecyclerAdapter extends RecyclerView.Adapter<BarberRecyclerAdapter.MyViewHolder> {
    Context context;
    List<BarberDetailsModel> list;
    List<BarberDetailsModel.TimeSlotDetails> slotDetails=new ArrayList<>();
    List<String> slot;
    Boolean expanded=false;
    int check_position;
    int position;

    public BarberRecyclerAdapter(Context context, List<BarberDetailsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.barber_recycler_item_view,viewGroup,false);
        slot=new ArrayList<>();

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        BarberDetailsModel model=list.get(i);
        myViewHolder.barber_name.setText(model.getDetails().getBarberDeatils().get(i).getName());
        slotDetails.add(model.getDetails().getTimeSlotDetails());
        myViewHolder.time_slot_recycler.setLayoutManager(new GridLayoutManager(context.getApplicationContext(),5));


        myViewHolder.barber_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position=i;

                if (expanded==false){
                    slot.add("09:20");
                    check_position=position;
                    myViewHolder.time_slot_recycler.setVisibility(View.VISIBLE);
                    expanded = true;

                    String regularFirstStartTime=slotDetails.get(i).getRegularFirstShiftStartTime();
                    String regularFirstEndTime=slotDetails.get(i).getFirstShiftEndTime();

                    String[] regularFirstShiftStart=regularFirstStartTime.split(":");
                    String[] regularFirstShiftEnd=regularFirstEndTime.split(":");
                    int startTimeFirst1=Integer.valueOf(regularFirstShiftStart[0]);
                    int startTimeFirst2=Integer.valueOf(regularFirstShiftStart[1]);
                    int endTimeFirst1=Integer.valueOf(regularFirstShiftEnd[0]);
                    int endTimeFirst2=Integer.valueOf(regularFirstShiftEnd[1]);

                    int j=startTimeFirst2;
                    if (startTimeFirst1<endTimeFirst1){
                        for (int l=startTimeFirst1;l<endTimeFirst2;l++){
                            if ((j+35)<60){
                                j=startTimeFirst2=startTimeFirst2+35;
                                slot.add(String.valueOf(startTimeFirst1)+":"+String.valueOf(startTimeFirst2));
                            }
                            if ((j+35)>60){
                                int k=35-(60-j);
                                startTimeFirst2=k;
                                startTimeFirst1=startTimeFirst1+1;
                                slot.add(String.valueOf(startTimeFirst1)+":"+String.valueOf(startTimeFirst2));
                            }else if ((j+35)<60){
                                j=startTimeFirst2=startTimeFirst2+35;
                                slot.add(String.valueOf(startTimeFirst1)+":"+String.valueOf(startTimeFirst2));
                            }
                        }
                    }

                    Toast.makeText(context, ""+slot.size(), Toast.LENGTH_SHORT).show();

                }else if (position==check_position && expanded==true){
                    myViewHolder.time_slot_recycler.setVisibility(View.GONE);
                    expanded=false;
                    slot.clear();
                }else {
                    Toast.makeText(context, "Close previously one", Toast.LENGTH_SHORT).show();
                }



            }
        });

        myViewHolder.time_slot_recycler.setAdapter(new TimeSlotRecyclerAdapter(context,slot));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView barber_card;
        TextView barber_name;
        RecyclerView time_slot_recycler;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            barber_card=itemView.findViewById(R.id.barber_card);
            barber_name=itemView.findViewById(R.id.barber_name);
            time_slot_recycler=itemView.findViewById(R.id.time_slots_recycler);

        }
    }
}
