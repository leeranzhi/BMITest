package com.demo.bmitest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.demo.bmitest.db.BMIRecord;

import java.util.List;

public class BMIAdapter extends ArrayAdapter<BMIRecord> {
    private int resourceId;

    public BMIAdapter(@NonNull Context context, int textViewresourceId, @NonNull List<BMIRecord> objects) {
        super(context, textViewresourceId, objects);
        resourceId=textViewresourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        BMIRecord bmiRecord=getItem(position);
        String result= String.valueOf((float) bmiRecord.getResult());
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView bmi_content=(TextView)view.findViewById(R.id.bmi_content);
        TextView bmi_time=(TextView)view.findViewById(R.id.bmi_time);
        bmi_content.setText(result);
        bmi_time.setText(bmiRecord.getNowTime());
        return view;
    }
}
