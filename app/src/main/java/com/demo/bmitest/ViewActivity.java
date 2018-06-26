package com.demo.bmitest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.demo.bmitest.db.BMIRecord;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class ViewActivity extends AppCompatActivity {
    private LineChart chart;
    private List<BMIRecord> bmiList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        //初始化数据
        init();
        chart = (LineChart) findViewById(R.id.chart_view);
        BMIAdapter bmiAdapter=new BMIAdapter(ViewActivity.this,R.layout.bmi_item,bmiList);
        ListView listView=(ListView)findViewById(R.id.list_view);
        listView.setAdapter(bmiAdapter);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return dateList.get((int) value);
//            }
//        });
        chart.setData(getLineData());
    }

    private void init() {
        bmiList = DataSupport.findAll(BMIRecord.class);
    }

    private LineData getLineData() {
        List<Entry> entries = new ArrayList<Entry>();
        for (int i = 0; i < bmiList.size(); i++) {
            entries.add(new Entry(i, (float) bmiList.get(i).getResult()));
        }
        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "BMI");
        //平滑曲线
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        LineData data = new LineData(lineDataSet);
        return data;
    }

}