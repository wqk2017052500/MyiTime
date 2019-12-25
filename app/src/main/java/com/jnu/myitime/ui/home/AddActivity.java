package com.jnu.myitime.ui.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jnu.myitime.R;

public class AddActivity extends AppCompatActivity {
    private ImageButton buttonOK;
    private ImageButton buttonCancel;
    private Button buttondate;
    private EditText editTextTitle;
    private EditText editTextMore;
    private String id;
    private TextView textView2;
    private String whatday;
    private String wday;

    int nyear;
    int nmonth;
    int nday;
    int nhour;
    int nminute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        buttonOK=findViewById(R.id.buttonok);
        buttonCancel=findViewById(R.id.buttoncancel);
        buttondate=findViewById(R.id.add_date);
        editTextTitle=findViewById(R.id.editTextTitle);
        editTextMore=findViewById(R.id.editTextMore);
        textView2=findViewById(R.id.textView2);


        editTextTitle.setText(getIntent().getStringExtra("title"));
        editTextMore.setText(getIntent().getStringExtra("more"));

        id=getIntent().getStringExtra("position");
        textView2.setText(getIntent().getStringExtra("data"));

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("title",editTextTitle.getText().toString());
                intent.putExtra("more",editTextMore.getText().toString());
                intent.putExtra("date",whatday);
                intent.putExtra("year",nyear);
                intent.putExtra("month",nmonth);
                intent.putExtra("day",nday);
                intent.putExtra("hour",nhour);
                intent.putExtra("minute",nminute);
                intent.putExtra("position",id);
                intent.putExtra("twd",wday);
                setResult(902,intent);
                AddActivity.this.finish();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddActivity.this.finish();
            }
        });

        buttondate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(AddActivity.this,date1.class);
                startActivityForResult(intent3,910);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==910&&resultCode==911){
            nyear=data.getIntExtra("year",0);
            nmonth=data.getIntExtra("month",0);
            nday=data.getIntExtra("day",0);
            nhour=data.getIntExtra("hour",0);
            nminute=data.getIntExtra("minute",0);
            wday=nyear+"年"+nmonth+"月"+nday+"日";
            whatday=nyear+"年"+nmonth+"月"+nday+"日"+nhour+"时"+nminute+"分";
            textView2.setText(whatday);
        }
    }
}
