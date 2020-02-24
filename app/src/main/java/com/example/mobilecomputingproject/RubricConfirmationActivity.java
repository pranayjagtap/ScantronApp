package com.example.mobilecomputingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RubricConfirmationActivity extends AppCompatActivity {

    Button doneButton;
    TextView testName;
    TextView answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubric_confirmation);
        doneButton = (Button) findViewById(R.id.DoneButton);
        testName = (TextView) findViewById(R.id.testName);
        answers = (TextView) findViewById(R.id.Answers);

        Intent oldIntent = getIntent();
        String nameTest = oldIntent.getStringExtra("nameOfTest");
        System.out.println("nameTest = " + nameTest);
        int num = oldIntent.getIntExtra("numOfQuestions", 0);
        System.out.println("numOfQuestions = " + num);
        boolean multiAnswer = oldIntent.getBooleanExtra("multiAnswers", false);
        testName.setText(nameTest);
        SharedPreferences prefs = getSharedPreferences("datastore", MODE_PRIVATE);
        SharedPreferences.Editor editor_meta = getSharedPreferences("datastore", MODE_PRIVATE).edit();
        String ans = prefs.getString(nameTest+"-answers","");
        for(int i = 2; i <=num; i++)
        /*{
            int t = ans.indexOf(""+i);
            String temp = ans;
            ans = temp.substring(0, t) + "\n" + temp.substring(t);
        }*/
        ans = ans.replaceAll(",", "\n");
        System.out.println(ans);
        answers.setText(ans);

        doneButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(RubricConfirmationActivity.this, MainActivity.class);
                startActivity(newIntent);
            }
        });


    }
}
