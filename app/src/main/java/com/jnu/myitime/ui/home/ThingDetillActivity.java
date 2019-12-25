package com.jnu.myitime.ui.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jnu.myitime.R;

import java.util.Calendar;

public class ThingDetillActivity extends AppCompatActivity {
    private FloatingActionButton back_button,change_buton,delete_button;
    private ImageView thing_detail_image;
    private TextView thing_detail_title,thing_detail_more,thing_detail_data,detail_daojishi;
    private int imageId;
    private String title,data,more;
    private String ws;
    private String position;

    long between;
    long between_year;
    long between_month;
    long between_day;
    long between_hour;
    long between_minute;
    long between_second;
    int detailhour,detailminute,detailyear,detailmonth,detailday;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==902&&requestCode==906){
            String title=data.getStringExtra("title");
            String more=data.getStringExtra("more");
            String id=data.getStringExtra("position");
            ws=data.getStringExtra("date");
            String w=data.getStringExtra("twd");
            detailyear=data.getIntExtra("year",0);
            detailmonth=data.getIntExtra("month",0);
            detailday=data.getIntExtra("day",0);
            detailhour=data.getIntExtra("hour",0);
            detailminute=data.getIntExtra("minute",0);
            //back to home
            Intent intent=new Intent();
            intent.putExtra("position",id);
            intent.putExtra("title",title);
            intent.putExtra("date",ws);
            intent.putExtra("twd",w);
            intent.putExtra("more",more);
            intent.putExtra("year",detailyear);
            intent.putExtra("month",detailmonth);
            intent.putExtra("day",detailday);
            intent.putExtra("hour",detailhour);
            intent.putExtra("minute",detailminute);
            setResult(905,intent);
            ThingDetillActivity.this.finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thing_detill);


        imageId=getIntent().getIntExtra("thing_image",0);
        title=getIntent().getStringExtra("thing_title");
        data=getIntent().getStringExtra("thing_data");
        more=getIntent().getStringExtra("thing_more");
        position=getIntent().getStringExtra("thing_position");

        detailhour=getIntent().getIntExtra("thing_hour",0);
        detailminute=getIntent().getIntExtra("thing_minute",0);
        detailyear=getIntent().getIntExtra("thing_year",0);
        detailmonth=getIntent().getIntExtra("thing_month",0);
        detailday=getIntent().getIntExtra("thing_day",0);

        String wh=detailyear+"年"+detailmonth+"月"+detailday+"日"+detailhour+"时"+detailminute+"分";

        thing_detail_title=findViewById(R.id.detail_title);
        thing_detail_data=findViewById(R.id.detali_data);
        thing_detail_more=findViewById(R.id.detail_more);
        thing_detail_image=findViewById(R.id.thing_imageView);
        detail_daojishi=findViewById(R.id.textView4);

        back_button=findViewById(R.id.back_floatingActionButton);
        delete_button=findViewById(R.id.delete_floatingActionButton);
        change_buton=findViewById(R.id.change_floatingActionButton);

        thing_detail_image.setImageResource(imageId);
        thing_detail_title.setText(title);
        thing_detail_data.setText(wh);
        thing_detail_more.setText(more);

        //获取当前系统时间
        Calendar calendar = Calendar.getInstance();
        int year_now = calendar.get(Calendar.YEAR);
        int month_now = calendar.get(Calendar.MONTH)+1;
        int day_now = calendar.get(Calendar.DAY_OF_MONTH);//获取系统时间//小时
        int hour_now = calendar.get(Calendar.HOUR_OF_DAY);//分钟
        int minute_now = calendar.get(Calendar.MINUTE);//秒
        int second_now = calendar.get(Calendar.SECOND);
        //倒计时
        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.set(year_now,month_now-1,day_now,hour_now,minute_now,second_now);		//设定时间为2017年3月3日 1:0:0
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(detailyear,detailmonth-1,detailday,detailhour,detailminute,0);		//设定时间为2017年3月3日 14:0:0
        long beginTime = beginCalendar.getTime().getTime();
        long endTime = endCalendar.getTime().getTime();
        between = (long)(endTime - beginTime)/1000;
        detail_daojishi.setText("剩余时间："+between+"");

        final Handler mHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 901:
                        between=between-1;
                        between_year=between/31536000;
                        between_month=(between%31536000)/2592000;
                        between_day=((between%31536000)%2592000)/86400;
                        between_hour=(((between%31536000)%2592000)%86400)/3600;
                        between_minute=((((between%31536000)%2592000)%86400)%3600)/60;
                        between_second=((((between%31536000)%2592000)%86400)%3600)%60;
                        detail_daojishi.setText("剩余时间："+between_year+"年"+between_month+"月"+between_day+"日"+between_hour+"小时"+between_minute+"分"+between_second+"秒");//在这里写需要刷新完成的代码
                        break;
                }
            }
        };
        Thread mythread=new Thread(){
            @Override
            public void run() {
                while (true) {
//                    Message message=new Message();
                    mHandler.sendEmptyMessage(901);
//                    message.what=901;
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };
        mythread.start();

        //back
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThingDetillActivity.this.finish();
            }
        });
        //delete
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("position",position);
                setResult(904,intent);
                ThingDetillActivity.this.finish();
            }
        });
        //change
        change_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ThingDetillActivity.this, AddActivity.class);
                intent2.putExtra("title",title);
                intent2.putExtra("more",more);
                intent2.putExtra("data",data);
                intent2.putExtra("position",position);
                startActivityForResult(intent2,906);
            }
        });


    }
}
