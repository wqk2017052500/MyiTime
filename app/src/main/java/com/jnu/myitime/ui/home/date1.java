package com.jnu.myitime.ui.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.jnu.myitime.R;

import java.util.Calendar;

public class date1 extends AppCompatActivity {

    private DatePicker mDatepicker;
    private Calendar mCalendar;
    private int mYear;
    private int mMonth;
    private int mDay;

    Button buttondateok;
    Button buttondatecancel;
    TextView textView;

    int tyear;
    int tmonth;
    int tday;
    int date1hour;
    int date1minute;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==920&&resultCode==922){
            date1hour=data.getIntExtra("hour",0);
            date1minute=data.getIntExtra("minute",0);
            textView.setText(date1hour+","+date1minute);
            Intent intent3=new Intent();
            intent3.putExtra("year",tyear);
            intent3.putExtra("month",tmonth);
            intent3.putExtra("day",tday);
            intent3.putExtra("hour",date1hour);
            intent3.putExtra("minute",date1minute);
            setResult(911,intent3);
            date1.this.finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date1);

        mCalendar=Calendar.getInstance();

        mYear=mCalendar.get(Calendar.YEAR);
        mMonth=mCalendar.get(Calendar.MONTH);
        mDay=mCalendar.get(Calendar.DAY_OF_MONTH);

        mDatepicker=findViewById(R.id.datePicker);
        buttondateok=findViewById(R.id.date1_ok);
        buttondatecancel=findViewById(R.id.date1_cancel);
        textView=findViewById(R.id.textView);

        mDatepicker.init(mYear, mMonth, mDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Toast.makeText(date1.this,year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日",Toast.LENGTH_SHORT).show();
                tyear=year;
                tmonth=monthOfYear;
                tday=dayOfMonth;
            }
        });


        buttondateok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4=new Intent(date1.this,date2.class);
                intent4.putExtra("year",tyear);
                intent4.putExtra("month",tmonth);
                intent4.putExtra("day",tday);
                startActivityForResult(intent4,920);
            }
        });
        buttondatecancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date1.this.finish();
            }
        });
    }
}
