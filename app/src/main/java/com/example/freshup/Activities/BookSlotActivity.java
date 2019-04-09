package com.example.freshup.Activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.freshup.R;

import org.joda.time.DateTime;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import noman.weekcalendar.WeekCalendar;
import noman.weekcalendar.listener.OnDateClickListener;

public class BookSlotActivity extends AppCompatActivity {

    WeekCalendar weekCalendar;
    TextView getDate;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_slot);
        weekCalendar = (WeekCalendar)findViewById(R.id.weekCalendar);
        getDate = (TextView)findViewById(R.id.getDate);

        final String date = new SimpleDateFormat("EEEE MMMMM dd, yyyy", Locale.getDefault()).format(new Date());
        getDate.setText(""+date);

        weekCalendar.setOnDateClickListener(new OnDateClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateClick(DateTime dateTime) {
                String[] weekdays = new DateFormatSymbols().getWeekdays();
                String[] month = new DateFormatSymbols().getMonths();
                String chosen_date = weekdays[dateTime.getDayOfWeek()]+" "+month[dateTime.getMonthOfYear()]+
                        ""+dateTime.getDayOfMonth()+","+dateTime.getYear();

                getDate.setText(chosen_date);
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(BookSlotActivity.this,PresentationsActivity.class);
        startActivity(intent);
    }
}
