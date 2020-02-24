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

public class TestSelectionActivity extends AppCompatActivity {

    ArrayList<RubricModel> rubricList = new ArrayList<RubricModel>();
    RecyclerView recyclerView;
    Button returnButton;

    CustomAdapterTestSel customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_selection);

        recyclerView = (RecyclerView) findViewById(R.id.rView);
        returnButton = (Button) findViewById(R.id.returnButton);
        recyclerView.setHasFixedSize(true);

        Intent oldIntent = getIntent();
        String mode = oldIntent.getStringExtra("mode");

        SharedPreferences prefs_meta = getSharedPreferences("datastore", MODE_PRIVATE);
        SharedPreferences.Editor editor_meta = getSharedPreferences("datastore", MODE_PRIVATE).edit();
        Map<String, ?> allEntries = prefs_meta.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String temp = entry.getKey();
            String testName = new String();
            if(temp != null) {
                int i = temp.indexOf("-numOfQuestions");
                if (i != -1) {
                    testName = temp.substring(0, i);
                    System.out.println("Test Name: " + temp.substring(0, i));
                    System.out.println(entry.getKey() + ": " + entry.getValue().toString());
                    String answers = prefs_meta.getString(testName+"-answers","");
                    boolean multiAnswers = prefs_meta.getBoolean(testName+"-multiAnswers", false);
                    boolean partialCredit = prefs_meta.getBoolean(testName+"-partialCredit", false);
                    int num = prefs_meta.getInt(testName+"-numOfQuestions", 0);
                    int rev = prefs_meta.getInt(testName+"-Rev", 0);
                    RubricModel t1 = new RubricModel();
                    t1.setTestName(temp.substring(0, i));
                    t1.setMode(mode);
                    t1.setqNumbers(num);
                    t1.setMultiAnswers(multiAnswers);
                    t1.setPartialCredit(partialCredit);
                    t1.setAnswers(answers);
                    t1.setRev(rev);
                    rubricList.add(t1);
                }
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        customAdapter = new CustomAdapterTestSel(getApplicationContext());
        customAdapter.setModel(rubricList);
        recyclerView.setAdapter(customAdapter);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(TestSelectionActivity.this, MainActivity.class);
                startActivity(newIntent);
            }
        });
    }
}
