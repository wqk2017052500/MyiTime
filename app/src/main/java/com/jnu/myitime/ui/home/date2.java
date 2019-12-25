package com.jnu.myitime.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.jnu.myitime.R;

import java.util.Calendar;

public class date2 extends AppCompatActivity {
    private TimePicker mTimePicker=null;
    private Calendar mClender=null;
    private int mHour;
    private int mMinute;
    TextView textView3;

    int date2year;
    int date2month;
    int date2day;
    int thour;
    int tminute;
    Button buttondate2ok;
    Button buttondate2cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date2);

        mClender= Calendar.getInstance(); //获取日历的实例
        mHour=mClender.get(Calendar.HOUR_OF_DAY);
        mMinute=mClender.get(Calendar.MINUTE);

        mTimePicker=(TimePicker)findViewById(R.id.timePicker);
        buttondate2cancel=findViewById(R.id.date2_cancel);
        buttondate2ok=findViewById(R.id.date2_ok);
        textView3=findViewById(R.id.textView3);


        mTimePicker.setIs24HourView(true);
        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(date2.this,hourOfDay+":"+minute,Toast.LENGTH_SHORT).show();
                thour=hourOfDay;
                tminute=minute;
            }
        });

        date2year=getIntent().getIntExtra("year",-1);
        date2month=getIntent().getIntExtra("month",-1);
        date2day=getIntent().getIntExtra("day",-1);

        textView3.setText(date2year+","+date2month+","+date2day);

        buttondate2ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4=new Intent();
                intent4.putExtra("year",date2year);
                intent4.putExtra("month",date2month);
                intent4.putExtra("day",date2day);
                intent4.putExtra("hour",thour);
                intent4.putExtra("minute",tminute);
                setResult(922,intent4);
                date2.this.finish();;
            }
        });

        buttondate2cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date2.this.finish();
            }
        });
    }
}
