package com.example.mobilecomputingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import java.util.ArrayList;

public class createRubricActivity extends AppCompatActivity {

    String testName;
    String mode;
    int numOfQuestions;
    boolean multiAnswers;
    boolean partialCredit;
    String answers;

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(createRubricActivity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent oldIntent = getIntent();
        testName = oldIntent.getStringExtra("nameOfTest");
        mode = oldIntent.getStringExtra("mode");
        numOfQuestions = oldIntent.getIntExtra("numOfQuestions", 0);
        multiAnswers = oldIntent.getBooleanExtra("multiAnswers", false);
        partialCredit = oldIntent.getBooleanExtra("partialCredit", false);
        answers = oldIntent.getStringExtra("answers");
        System.out.println(testName);
        System.out.println("Mode: " + mode);
        System.out.println("numOfQuestions: " + numOfQuestions);
        System.out.println("multiAnswers: " + multiAnswers);
        System.out.println("partialCredit" + partialCredit);
        System.out.println("answers" + answers);
        setContentView(R.layout.activity_create_rubric);

        final Switch multiAnswersSwitch        = findViewById(R.id.multiAnswersSwitch);
        final Switch partialCreditSwitch       = findViewById(R.id.partialCreditSwitch);
        final EditText numQuestions            = findViewById(R.id.numOfQuestions);
        final EditText name                    = findViewById(R.id.nameOfTest);
        Button backButton                      = findViewById(R.id.backButton);
        Button forwardButton                   = findViewById(R.id.forwardButton);

        multiAnswersSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    partialCreditSwitch.setVisibility(View.VISIBLE);
                } else {
                    partialCreditSwitch.setVisibility(View.INVISIBLE);
                    partialCreditSwitch.setChecked(false);
                }
            }
        });

        numQuestions.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        if(mode.equals("create") == true) {

            if (testName != null) {

                name.setText(testName);
                numQuestions.setText("" + numOfQuestions);
                multiAnswersSwitch.setChecked(multiAnswers);
                partialCreditSwitch.setChecked(partialCredit);
            }
        }
        if(mode.equals("edit") == true) {
            System.out.println("In edit mode, populating fields");
            name.setText(testName);
            numQuestions.setText("" + numOfQuestions);
            multiAnswersSwitch.setChecked(multiAnswers);
            partialCreditSwitch.setChecked(partialCredit);
        }


        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(mode.equals("create") == true) {
                    Intent mainIntent = new Intent(createRubricActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                }
                else {
                    Intent mainIntent = new Intent(createRubricActivity.this, TestSelectionActivity.class);
                    startActivity(mainIntent);
                }
            }
        });

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String testName = name.getText().toString();
                System.out.println("Test name = " + testName);
                int numOfQuestions = Integer.parseInt(numQuestions.getText().toString());
                System.out.println("Number of Questions = " + numOfQuestions);
                boolean multiAnswers = multiAnswersSwitch.isChecked();
                System.out.println("mutipleAnswers = " + multiAnswers);
                boolean partialCredit = partialCreditSwitch.isChecked();
                System.out.println("partialCredit = " + partialCredit);
                Intent newIntent = new Intent(createRubricActivity.this, answerKeyActivity.class);
                newIntent.putExtra("mode", mode);
                newIntent.putExtra("nameOfTest", testName);
                newIntent.putExtra("numOfQuestions", numOfQuestions);
                newIntent.putExtra("multiAnswers", multiAnswers);
                newIntent.putExtra("partialCredit", partialCredit);
                newIntent.putExtra("answers", answers);
                startActivity(newIntent);
            }
        });
    }
}
