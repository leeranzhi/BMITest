package com.demo.bmitest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.bmitest.db.BMIRecord;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText user_height, user_weight;
    private Button BMI_calculate, BMI_save,show_view;
    private TextView result_content;
    double result = 0.0;
    String suggestion;
    String nowTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_height = (EditText) findViewById(R.id.user_height);
        user_weight = (EditText) findViewById(R.id.user_weight);
        BMI_calculate = (Button) findViewById(R.id.BMI_calculate);
        BMI_save = (Button) findViewById(R.id.BMI_save);
        show_view=(Button) findViewById(R.id.show_view);
        result_content = (TextView) findViewById(R.id.result_content);
        BMI_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String height = user_height.getText().toString();
                String weight = user_weight.getText().toString();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                //获取当前时间
                Date date = new Date(System.currentTimeMillis());
                nowTime = simpleDateFormat.format(date);
                if (height == null || height.equals("")) {
                    Toast.makeText(getApplicationContext(), "身高不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (weight == null || weight.equals("")) {
                    Toast.makeText(getApplicationContext(), "体重不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                requestResult(height, weight);
                requestSuggestion();

            }
        });
        BMI_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.getDatabase();
                if (result != 0.0) {
                    saveToLocal(result, nowTime);
                    user_height.setText("");
                    user_weight.setText("");
                    Toast.makeText(getApplicationContext(), "保存成功！！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "请先输入！！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        show_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ViewActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 保存当前时间和BMI结果至本地
     */
    private void saveToLocal(double result, String nowTime) {
        Log.d("MainActivity", "现在时间" + nowTime);
        BMIRecord bmiRecord = new BMIRecord();
        bmiRecord.setResult(result);
        bmiRecord.setNowTime(nowTime);
        bmiRecord.save();
    }

    /**
     * 分析BMI值
     */
    private void requestSuggestion() {
        if (result <= 18) {
            suggestion = "\tBMI值" + result + "\n\t偏瘦体质";
            result_content.setText(suggestion);
        }
        if (result >= 18.5 && result <= 23.9) {
            suggestion = "\tBMI值" + result + "\n\t正常体质";
            result_content.setText(suggestion);
        }
        if (result >= 24 && result <= 27.9) {
            suggestion = "\tBMI值" + result + "\n\t偏胖体质";
            result_content.setText(suggestion);
        }
        if (result >= 28) {
            suggestion = "\tBMI值" + result + "\n\t肥胖体质";
            result_content.setText(suggestion);
        }

    }

    /**
     * 计算BMI结果
     */
    private void requestResult(String height, String weight) {
        double heightNumber = Double.valueOf(height);
        double weightNumber = Double.valueOf(weight);
        result = weightNumber / ((heightNumber * heightNumber) / (100 * 100));
    }
}
