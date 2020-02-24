package com.example.mobilecomputingproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Map;

public class StudentSelectionActivity extends AppCompatActivity {

    ArrayList<ModelStudent> studentList = new ArrayList<ModelStudent>();
    RecyclerView recyclerView;
    Button returnButton;

    CustomAdapterStudent customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_selection);

        recyclerView = (RecyclerView) findViewById(R.id.rView);
        returnButton = (Button) findViewById(R.id.returnButton);
        recyclerView.setHasFixedSize(true);
        String scoreList = new String();
        String studentName = new String();

        SharedPreferences prefs_meta = getSharedPreferences("datastore", MODE_PRIVATE);
        Map<String, ?> allEntries = prefs_meta.getAll();
        for(Map.Entry<String, ?> entry: allEntries.entrySet()) {
            String temp = entry.getKey();
            if(temp != null) {
                int i = temp.indexOf("-scores");
                if(i != -1) {
                    scoreList = prefs_meta.getString(temp,"");
                    break;
                }
            }
        }
        String[] studs = scoreList.split(",");
        for(int i = 1; i< studs.length; i++) {
            String[] perstud = studs[i].split(":");
            studentName = perstud[0];
            ModelStudent s1 = new ModelStudent(studentName);
            studentList.add(s1);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        customAdapter = new CustomAdapterStudent(getApplicationContext());
        customAdapter.setModel(studentList);
        recyclerView.setAdapter(customAdapter);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(StudentSelectionActivity.this, MainActivity.class);
                startActivity(newIntent);
            }
        });
    }
}
