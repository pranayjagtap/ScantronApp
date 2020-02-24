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

public class viewGradeStatistics extends AppCompatActivity {
    String testName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent oldIntent = getIntent();
        testName = oldIntent.getStringExtra("nameOfTest");
        setContentView(R.layout.activity_view_grade_statistics);
        TextView name = findViewById(R.id.name);
        name.setText(testName);
    }
}
