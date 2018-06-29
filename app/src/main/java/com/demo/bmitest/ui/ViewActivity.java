package com.demo.bmitest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.demo.bmitest.BMIAdapter;
import com.demo.bmitest.R;
import com.demo.bmitest.db.BMIRecord;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class ViewActivity extends AppCompatActivity {

    private LineChart chart;
    private List<BMIRecord> bmiList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        chart = (LineChart) findViewById(R.id.chart_view);
        ListView listView = (ListView) findViewById(R.id.list_view);
        //初始化本地数据
        initLocal();

        BMIAdapter bmiAdapter = new BMIAdapter(ViewActivity.this,
                R.layout.bmi_item, bmiList);
        listView.setAdapter(bmiAdapter);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.setData(getLineData());

    }

    /**
     * 取出本地数据并初始化
     */
    private void initLocal() {
        bmiList = DataSupport.findAll(BMIRecord.class);
    }

    private LineData getLineData() {
        List<Entry> entries = new ArrayList<>();
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