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


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
public class BarberRecyclerAdapter extends RecyclerView.Adapter<BarberRecyclerAdapter.MyViewHolder> {
    Context context;
    List<BarberDetailsModel> list;
    List<BarberDetailsModel.TimeSlotDetails> slotDetails=new ArrayList<>();
    List<String> Slot=new ArrayList<>();
    Boolean expanded=false;
    int check_position;
    int position;
    String dateData,day;
    String regularFirstShiftStartTime,regularFirstShiftEndTime,regularSecondShiftStartTime,regularSecondShiftEndTime,
            weekendFirstShiftStartTime,weekendFirstShiftEndTime,weekendSecondShiftStartTime,weekendSecondShiftEndTime;






    public BarberRecyclerAdapter(Context context, List<BarberDetailsModel> list1,String dateData,String day) {
        if (list!=null){
            list.clear();
        }
        this.context = context;
        list = list1;
        this.dateData=dateData;
        this.day=day;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.barber_recycler_item_view,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        BarberDetailsModel model=list.get(i);
        myViewHolder.barber_name.setText(model.getDetails().getBarberDeatils().get(i).getName());
        slotDetails.add(model.getDetails().getTimeSlotDetails());
        myViewHolder.time_slot_recycler.setLayoutManager(new GridLayoutManager(context.getApplicationContext(),5));
        regularFirstShiftStartTime=model.getDetails().getTimeSlotDetails().getRegularFirstShiftStartTime();
        regularFirstShiftEndTime=model.getDetails().getTimeSlotDetails().getRegularFirstShiftEndTime();
        regularSecondShiftStartTime=model.getDetails().getTimeSlotDetails().getRegularSecondShiftStartTime();
        regularSecondShiftEndTime=model.getDetails().getTimeSlotDetails().getRegularSecondShiftEndTime();
        weekendFirstShiftStartTime=model.getDetails().getTimeSlotDetails().getWeekendFirstShiftStartTime();
        weekendFirstShiftEndTime=model.getDetails().getTimeSlotDetails().getWeekendFirstShiftEndTime();
        weekendSecondShiftStartTime=model.getDetails().getTimeSlotDetails().getWeekendSecondShiftStartTime();
        weekendSecondShiftEndTime=model.getDetails().getTimeSlotDetails().getWeekendSecondShiftEndTime();


        myViewHolder.barber_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position=i;

                if (expanded==false){
                    check_position=position;
                    myViewHolder.time_slot_recycler.setVisibility(View.VISIBLE);
                    expanded = true;
                    getSlot();


                }else if (position==check_position && expanded==true){
                    myViewHolder.time_slot_recycler.setVisibility(View.GONE);
                    expanded=false;
                    Slot.clear();
                }else {
                    Toast.makeText(context, "Close previously one", Toast.LENGTH_SHORT).show();
                }



            }
        });
        TimeSlotRecyclerAdapter adapter=new TimeSlotRecyclerAdapter(context,Slot);
        myViewHolder.time_slot_recycler.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
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
    public void getSlot(){
        try{
            if (day.equalsIgnoreCase("Saturday")){
                Slot.clear();
                String date1 = dateData;
                String timeStart1 = weekendFirstShiftStartTime+" AM";
                String timeEnd1 = weekendFirstShiftEndTime+" PM";

                String format = "yyyy-MM-dd hh:mm a";
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                Date dateObj1 = sdf.parse(date1 + " " + timeStart1);
                Date dateObj2 = sdf.parse(date1 + " " + timeEnd1);


                long dif = dateObj1.getTime();
                while (dif < dateObj2.getTime()) {
                    Date slot = new Date(dif);

                    String s=String.valueOf(slot);
                    String[] array;
                    array=s.split(" ");
                    String time_slot=array[3];
                    StringBuffer st=new StringBuffer(time_slot);
                    st.delete(5, 8);
                    Slot.add(String.valueOf(st));
                    dif += (1800000+60000*10);
                }

                String timeStart2 = weekendSecondShiftStartTime;
                String timeEnd2 = weekendSecondShiftEndTime;
                String format2 = "yyy-MM-dd hh:mm";
                SimpleDateFormat sdf2 = new SimpleDateFormat(format2);
                Date dateObj3=sdf2.parse(date1+" "+timeStart2);
                Date dateObj4=sdf2.parse(date1+" "+timeEnd2);

                long dif2=dateObj3.getTime();
                while (dif2 < dateObj4.getTime()) {
                    Date slot = new Date(dif2);

                    String s=String.valueOf(slot);
                    String[] array;
                    array=s.split(" ");
                    String time_slot=array[3];
                    StringBuffer st=new StringBuffer(time_slot);
                    st.delete(5, 8);
                    Slot.add(String.valueOf(st));
                    dif2 += (1800000+60000*10);
                }

            }
            else {
                Slot.clear();
                String date1 = dateData;
                String timeStart1 = regularFirstShiftStartTime+" AM";
                String timeEnd1 = regularFirstShiftEndTime+" PM";

                String format = "yyyy-MM-dd hh:mm a";
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                Date dateObj1 = sdf.parse(date1 + " " + timeStart1);
                Date dateObj2 = sdf.parse(date1 + " " + timeEnd1);


                long dif = dateObj1.getTime();
                while (dif < dateObj2.getTime()) {
                    Date slot = new Date(dif);

                    String s=String.valueOf(slot);
                    String[] array;
                    array=s.split(" ");
                    String time_slot=array[3];
                    StringBuffer st=new StringBuffer(time_slot);
                    st.delete(5, 8);
                    Slot.add(String.valueOf(st));
                    dif += (1800000+60000*10);
                }

                String timeStart2 = regularSecondShiftStartTime;
                String timeEnd2 = regularSecondShiftEndTime;
                String format2 = "yyy-MM-dd hh:mm";
                SimpleDateFormat sdf2 = new SimpleDateFormat(format2);
                Date dateObj3=sdf2.parse(date1+" "+timeStart2);
                Date dateObj4=sdf2.parse(date1+" "+timeEnd2);

                long dif2=dateObj3.getTime();
                while (dif2 < dateObj4.getTime()) {
                    Date slot = new Date(dif2);

                    String s=String.valueOf(slot);
                    String[] array;
                    array=s.split(" ");
                    String time_slot=array[3];
                    StringBuffer st=new StringBuffer(time_slot);
                    st.delete(5, 8);
                    Slot.add(String.valueOf(st));
                    dif2 += (1800000+60000*10);
                }

            }
        }catch(Exception e)
        {

        }
    }
}
