package com.example.freshup.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshup.Adapters.BarberRecyclerAdapter;
import com.example.freshup.Models.BarberDetailsModel;
import com.example.freshup.R;
import com.example.freshup.SharedPrefrences.Common;
import com.example.freshup.Util.App;
import com.example.freshup.ViewModels.BarberViewModel;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import noman.weekcalendar.WeekCalendar;
import noman.weekcalendar.listener.OnDateClickListener;

public class BookSlotActivity extends AppCompatActivity {

    WeekCalendar weekCalendar;
    TextView getDate, shop_close, holiday_tv, title;
    ImageView background, main;
    Calendar cal;
    SimpleDateFormat sdf, sdf1;
    String datedata = "";
    String day = "";
    String service_id;
    String[] weekday;
    RecyclerView barber_recycler;
    BarberViewModel viewModel;
    List<BarberDetailsModel> list = new ArrayList<>();
    List<BarberDetailsModel.BarberDeatil> listmodel = new ArrayList<>();

    Boolean flag = true;
    String currentDate, Price;
    DateFormat dateFormat;
    int dateDatavalue = 0;
    String[] day1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_slot);
        viewModel = ViewModelProviders.of(this).get(BarberViewModel.class);
        barber_recycler = findViewById(R.id.barber_recycler);
        holiday_tv = findViewById(R.id.holiday_tv);
        weekCalendar = (WeekCalendar) findViewById(R.id.weekCalendar);
        getDate = (TextView) findViewById(R.id.getDate);
        shop_close = findViewById(R.id.close_shop_tv);
        background = findViewById(R.id.book_Slot_Back_Ground_Image);
        main = findViewById(R.id.book_Slot_Main_Image);
        title = findViewById(R.id.book_Slot_title);
        barber_recycler.setLayoutManager(new LinearLayoutManager(this));

        cal = Calendar.getInstance();
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf1 = new SimpleDateFormat("EEEE MMMM dd, yyyy");
        datedata = sdf.format(cal.getTime());

        final Date date = new Date();
        currentDate = sdf.format(date);
        getDate.setText(sdf1.format(date));
        day = getDate.getText().toString();
        weekday = day.split(" ");
        list.clear();
        getBarberDetails();
        App.getSingleton().setAppointmentDate(datedata);
        weekCalendar.reset();

        service_id = App.getSingleton().getService_id();
        title.setText(Common.GetToken(BookSlotActivity.this, "Service title" + service_id));
        Picasso.with(getApplicationContext()).load(Common.GetToken(BookSlotActivity.this, "Service background" + service_id)).into(background);
        Picasso.with(getApplicationContext()).load(Common.GetToken(BookSlotActivity.this, "Service image" + service_id)).into(main);

        weekCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(DateTime dateTime) {
//                list.clear();
                barber_recycler.setVisibility(View.GONE);


                StringTokenizer stringTokenizer = new StringTokenizer(String.valueOf(dateTime.toDateTime().dayOfWeek().getDateTime()), "T");
                String dateValue = stringTokenizer.nextElement().toString();
                System.out.println("Date: " + dateTime.toDateTime());
                datedata = dateValue;
                try {
                    Date select = sdf.parse(datedata);
                    Date Current = sdf.parse(currentDate);

                    if (Current.getTime() <= select.getTime()) {
                        dateDatavalue = 0;
//                        list.clear();
                        getBarberDetails();
                        App.getSingleton().setAppointmentDate(datedata);
                        getDate.setText(sdf1.format(dateTime.toDate()));
                        day = getDate.getText().toString();
                        weekday = day.split(" ");


                    } else {
                        //flag=true;
                        dateDatavalue = 1;
                        Toast.makeText(BookSlotActivity.this, "Choose future date", Toast.LENGTH_SHORT).show();
//                        list.clear();
                        barber_recycler.setVisibility(View.GONE);
                        shop_close.setVisibility(View.GONE);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void getBarberDetails() {
        if (list != null) {
            list.clear();
        }
        viewModel.barber(this, datedata).observe(this, new Observer<BarberDetailsModel>() {
            @Override
            public void onChanged(@Nullable BarberDetailsModel barberDetailsModel) {
                if (barberDetailsModel.getSuccess().equalsIgnoreCase("1")) {
                    for (int i = 0; i < barberDetailsModel.getDetails().getBarberDeatils().size(); i++) {
                        BarberDetailsModel model = new BarberDetailsModel();
                        BarberDetailsModel.Details getdetail = new BarberDetailsModel.Details();
                        BarberDetailsModel.BarberDeatil detail = new BarberDetailsModel.BarberDeatil();

                        detail.setName(barberDetailsModel.getDetails().getBarberDeatils().get(i).getName());
                        getdetail.setTimeSlotDetails(barberDetailsModel.getDetails().getTimeSlotDetails());
                        holiday_tv.setText("Holidays: " + barberDetailsModel.getDetails().getTimeSlotDetails().getNonWorkingDays());
                        listmodel.add(detail);
                        getdetail.setBarberDeatils(listmodel);
                        model.setDetails(getdetail);

                        list.add(model);
                        String date = getDate.getText().toString();
                        day1 = date.split(" ");
//                        list.clear();
                    }
                    BarberRecyclerAdapter adapter = new BarberRecyclerAdapter(BookSlotActivity.this, list, datedata, day1[0]);
                    adapter.notifyDataSetChanged();
                    barber_recycler.setAdapter(adapter);
                    shop_close.setVisibility(View.GONE);
                    barber_recycler.setVisibility(View.VISIBLE);
                } else if (barberDetailsModel.getSuccess().equalsIgnoreCase("2")) {
                    list.clear();
                    barber_recycler.setVisibility(View.GONE);
                    shop_close.setVisibility(View.VISIBLE);
                }
            }
        });

    }

}
