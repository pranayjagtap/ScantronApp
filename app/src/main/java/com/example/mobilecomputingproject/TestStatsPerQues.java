package com.example.mobilecomputingproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TestStatsPerQues  extends AppCompatActivity {
    Button returnButton;
    TextView name, questionStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teststats);
        returnButton = (Button) findViewById(R.id.returnButton);
        name = (TextView) findViewById(R.id.name);
        questionStats = (TextView) findViewById(R.id.testStats);
        Intent oldIntent = getIntent();
        //String testlist = new String();

//               SharedPreferences prefs_meta = getSharedPreferences("datastore", MODE_PRIVATE);
//        Map<String, ?> allEntries = prefs_meta.getAll();
//        for(Map.Entry<String, ?> entry: allEntries.entrySet()) {
//            String temp = entry.getKey();
//            if(temp != null) {
//                int i = temp.indexOf("-scores");
//                if(i != -1) {
//                    testlist = prefs_meta.getString(temp,"");
//                    break;
//                }
//            }
//        }
//
//        String[] tests = testlist.split(",");
//        for(int i = 1; i< tests.length; i++) {
//            String[] perstud = tests[i].split(":");
//            testlistarr.add(perstud[0]);
//
//        }
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//
//        recyclerView.setLayoutManager(layoutManager);
//
//        customAdapter = new CustomAdapterStudent(getApplicationContext());
//        customAdapter.setModel(studentList);
//        recyclerView.setAdapter(customAdapter);

        String testname= oldIntent.getStringExtra("nameOfTest");
        name.setText(testname);

        SharedPreferences prefsget2 = getSharedPreferences("datastore", MODE_PRIVATE);
        String quesstats = prefsget2.getString(testname+"-stats", "");//"No name defined" is the default value.
        int numOfQuestions = prefsget2.getInt(testname+"-numOfQuestions", 1);//"No name defined" is the default value.
//tes-numOfQuestions
        System.out.println(quesstats);
        System.out.println(numOfQuestions);
        int num=numOfQuestions;

        int stats[]=new int[num];
        for(int i=0;i<quesstats.length();i++){
            if(quesstats.charAt(i)=='0')
                stats[i%num]++;
        }

        for(int i=0;i<stats.length;i++){
            System.out.println("Ques number "+(i+1)+":" + stats[i]);
        }
        System.out.println(quesstats);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(TestStatsPerQues.this, MainActivity.class);
                startActivity(newIntent);
            }
        });
    }
}

