package com.example.mobilecomputingproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;
import java.text.DecimalFormat;

public class viewGradeStatistics extends AppCompatActivity {
    String testName;
    TextView avg;
    TextView min;
    TextView max;

    TextView studentGrades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent oldIntent = getIntent();
        testName = oldIntent.getStringExtra("nameOfTest");
        testName = testName.substring(0, testName.indexOf(" Rev"));
        setContentView(R.layout.activity_view_grade_statistics);
        this.avg = (TextView) findViewById(R.id.averageVal);
        this.min = (TextView) findViewById(R.id.minVal);
        this.max = (TextView) findViewById(R.id.maxVal);
        this.studentGrades = (TextView) findViewById(R.id.studentScores);
        TextView name = findViewById(R.id.name);
        name.setText(testName);


        SharedPreferences prefsget4 = getSharedPreferences("datastore", MODE_PRIVATE);
        String classstat = prefsget4.getString(testName + "-scores", "");//"No name defined" is the default value.

        String[] studs=classstat.split(",");
        double _min=Double.MAX_VALUE;
        double _max= Double.MIN_VALUE;
        double _sum=0;
        for(int i=1;i<studs.length;i++){
            String[] perstud=studs[i].split(":");
            _sum=_sum+Double.parseDouble(perstud[1]);
            if(Double.parseDouble(perstud[1])>_max){
                _max=Double.parseDouble(perstud[1]);
            }
            if(Double.parseDouble(perstud[1])<_min){
                _min=Double.parseDouble(perstud[1]);
            }

        }


        if(studs.length>0) {
            DecimalFormat df = new DecimalFormat("###.##");
            double _avg = _sum / (studs.length-1);
            this.avg.setText(df.format(_avg) + "");
            this.min.setText(_min + "");
            this.max.setText(_max + "");
        }


            this.studentGrades.setText(classstat.replace(",", "\n"));

    }
}
