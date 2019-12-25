package com.jnu.myitime.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.jnu.myitime.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ListView homelistView;
    ThingSaver thingSaver;
    private ThingAdapter adapter;
    private List<Thing> listThings=new ArrayList<>();
    private ImageButton buttonAdd;

    @Override
    public void onDestroy() {
        super.onDestroy();
        thingSaver.save();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        thingSaver=new ThingSaver(root.getContext());
        listThings=thingSaver.load();
        if(listThings.size()==0)
            init();
        homelistView=root.findViewById(R.id.home_listview);
        buttonAdd=root.findViewById(R.id.add_button);

        adapter=new ThingAdapter(root.getContext(),R.layout.thing,listThings);
        homelistView.setAdapter(adapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),AddActivity.class);
                startActivityForResult(intent,901);
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 901:
                if(resultCode==902){
                    String title=data.getStringExtra("title");
                    String more=data.getStringExtra("more");
                    String date=data.getStringExtra("twd");
                    int year=data.getIntExtra("year",0);
                    int month=data.getIntExtra("month",0);
                    int day=data.getIntExtra("day",0);
                    int hour=data.getIntExtra("hour",0);
                    int minute=data.getIntExtra("minute",0);
                    listThings.add(new Thing(title,date,more,R.drawable.a1,year,month,day,hour,minute));
                    adapter.notifyDataSetChanged();
                }
                break;
            case 903:
                    if(resultCode==904){
                        String id=data.getStringExtra("position");
                        int w=Integer.parseInt(id);
                        listThings.remove(w);
                        adapter.notifyDataSetChanged();
                    }
                    else if(resultCode==905){
                        String id=data.getStringExtra("position");
                        String title=data.getStringExtra("title");
                        String more=data.getStringExtra("more");
                        String date=data.getStringExtra("twd");
                        int year=data.getIntExtra("year",0);
                        int month=data.getIntExtra("month",0);
                        int day=data.getIntExtra("day",0);
                        int hour=data.getIntExtra("hour",0);
                        int minute=data.getIntExtra("minute",0);
                        int w=Integer.parseInt(id);


                        Thing thingAtPosition = listThings.get(w);
                        thingAtPosition.setTitle(title);
                        thingAtPosition.setMore(more);
                        thingAtPosition.setData(date);
                        thingAtPosition.setThingyear(year);
                        thingAtPosition.setThingmonth(month);
                        thingAtPosition.setThingday(day);
                        thingAtPosition.setThinghour(hour);
                        thingAtPosition.setThingminute(minute);
                        adapter.notifyDataSetChanged();
                    }
                    break;
        }
    }

    private void init() {
        listThings.add(new Thing("Android开发","2019年12月8日","完成倒计时设计",R.drawable.book_1,2019,12,31,19,30));
    }

    class ThingAdapter extends ArrayAdapter<Thing> {

        private int resourceId;

        public ThingAdapter(Context context, int resource, List<Thing> objects) {
            super(context, resource, objects);
            resourceId = resource;
        }

        @NonNull
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Thing thing = getItem(position);//获取当前项的实例
            View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

            ((ImageView) view.findViewById(R.id.image__cover)).setImageResource(thing.getCoverResourceId());
            ((TextView) view.findViewById(R.id.text_title)).setText(thing.getTitle());
            ((TextView) view.findViewById(R.id.text_day)).setText(thing.getData());
            ((TextView) view.findViewById(R.id.text_more)).setText(thing.getMore());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getContext(), ThingDetillActivity.class);

                    intent.putExtra("thing_image", thing.getCoverResourceId());
                    intent.putExtra("thing_title", thing.getTitle());
                    intent.putExtra("thing_data",thing.getData());
                    intent.putExtra("thing_more",thing.getMore());
                    intent.putExtra("thing_year",thing.getThingyear());
                    intent.putExtra("thing_month",thing.getThingmonth());
                    intent.putExtra("thing_day",thing.getThingday());
                    intent.putExtra("thing_hour",thing.getThinghour());
                    intent.putExtra("thing_minute",thing.getThingminute());
                    String s=String.valueOf(position);
                    intent.putExtra("thing_position",s);

                    startActivityForResult(intent,903);
                }
            });

            return view;
        }
    }
}