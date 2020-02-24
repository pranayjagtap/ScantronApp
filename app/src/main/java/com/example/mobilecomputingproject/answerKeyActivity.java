package com.example.mobilecomputingproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class answerKeyActivity extends AppCompatActivity implements OnOptionSelected{

    RecyclerView rView;
    Button backButton;
    Button forwardButton;
    String nameTest;
    int num;
    boolean multiAnswers;
    boolean partialCredit;
    String answers;
    String mode;

    CustomAdapterMulti customAdapter1;
    CustomAdapterSingle customAdapter2;

    ArrayList<ModelMulti>  list1 = new ArrayList<ModelMulti>();
    ArrayList<ModelSingle> list2 = new ArrayList<ModelSingle>();

    SharedPreferences prefs_meta;
    SharedPreferences.Editor editor_meta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs_meta = getSharedPreferences("datastore", MODE_PRIVATE);
        editor_meta = getSharedPreferences("datastore", MODE_PRIVATE).edit();
        setContentView(R.layout.activity_answer_key);
        rView = (RecyclerView) findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        //Get info from old intent
        Intent oldIntent = getIntent();

        nameTest = oldIntent.getStringExtra("nameOfTest");
        System.out.println("Test name = " + nameTest);
        num = oldIntent.getIntExtra("numOfQuestions", 0);
        editor_meta.putInt(nameTest+"-numOfQuestions",num);
        editor_meta.apply();

        System.out.println("Number of questions = " + num);
        multiAnswers = oldIntent.getBooleanExtra("multiAnswers", false);
        editor_meta.putBoolean(nameTest+"-multiAnswers",multiAnswers);
        editor_meta.apply();

        partialCredit = oldIntent.getBooleanExtra("partialCredit", false);
        editor_meta.putBoolean(nameTest+"-partialCredit",partialCredit);
        editor_meta.apply();
        mode = oldIntent.getStringExtra("mode");
        answers = oldIntent.getStringExtra("answers");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rView.setLayoutManager(layoutManager);
        //Create custom adapter based on options selected
        if(mode.equals("create")) {
            editor_meta.putInt(nameTest+"-Rev",-1);
            editor_meta.apply();
            if (multiAnswers) {
                list1 = new ArrayList<ModelMulti>();
                for (int i = 1; i <= num; i++) {
                    ModelMulti model = new ModelMulti(i);
                    list1.add(model);
                }
                customAdapter1 = new CustomAdapterMulti();
                customAdapter1.setOnOptionSelected(this);
                customAdapter1.setModel(list1);
                rView.setAdapter(customAdapter1);
                System.out.println("multiAnswer Adapter set");

            } else {
                list2 = new ArrayList<ModelSingle>();
                for (int i = 1; i <= num; i++) {
                    ModelSingle model = new ModelSingle(i);
                    list2.add(model);
                }
                customAdapter2 = new CustomAdapterSingle();
                customAdapter2.setOnOptionSelected(this);
                customAdapter2.setModel(list2);
                rView.setAdapter(customAdapter2);
                System.out.println("singleAnswer adapter set");
            }
        } else {
            for(int i = 2; i <=num; i++)
            {
                int t = answers.indexOf(""+i);
                String temp = answers;
                answers = temp.substring(0, t) + "\n" + temp.substring(t);
            }
            if(multiAnswers) {
                list1 = new ArrayList<ModelMulti>();
                for (int i = 1; i <= num; i++) {
                    if(i != num) {
                        ModelMulti model = new ModelMulti(i);
                        System.out.println(answers);
                        int start = answers.indexOf(":");
                        int end = answers.indexOf("\n");
                        String answer = answers.substring(start+1, end);
                        answers = answers.substring(end + 1);
                        System.out.println("Answer " + i + "= " + answer);
                        if(answer.contains("A")) {
                            model.setSelA(true);
                        }
                        if(answer.contains("B")) {
                            model.setSelB(true);
                        }
                        if(answer.contains("C")) {
                            model.setSelC(true);
                        }
                        if(answer.contains("D")) {
                            model.setSelD(true);
                        }
                        if(answer.contains("E")) {
                            model.setSelE(true);
                        }

                        list1.add(model);

                    } else {
                        ModelMulti model = new ModelMulti(i);
                        System.out.println(answers);
                        int start = answers.indexOf(":");
                        String answer = answers.substring(start+1);
                        System.out.println("Answer " + i + "= " + answer);
                        if(answer.contains("A")) {
                            model.setSelA(true);
                        }
                        if(answer.contains("B")) {
                            model.setSelB(true);
                        }
                        if(answer.contains("C")) {
                            model.setSelC(true);
                        }
                        if(answer.contains("D")) {
                            model.setSelD(true);
                        }
                        if(answer.contains("E")) {
                            model.setSelE(true);
                        }
                        list1.add(model);
                    }
                }
                customAdapter1 = new CustomAdapterMulti();
                customAdapter1.setOnOptionSelected(this);
                customAdapter1.setModel(list1);
                rView.setAdapter(customAdapter1);
                System.out.println("multiAnswer Adapter set");
            } else {
                list2 = new ArrayList<ModelSingle>();
                for (int i = 1; i <= num; i++) {
                    if(i != num) {
                        ModelSingle model = new ModelSingle(i);
                        int start = answers.indexOf(":");
                        int end = answers.indexOf("\n");
                        String answer = answers.substring(start+1, end);
                        answers = answers.substring(end + 1);
                        System.out.println("Answer " + i + "= " + answer);
                        if(answer.contains("A")) {
                            model.setSelA(true);
                        }
                        if(answer.contains("B")) {
                            model.setSelB(true);
                        }
                        if(answer.contains("C")) {
                            model.setSelC(true);
                        }
                        if(answer.contains("D")) {
                            model.setSelD(true);
                        }
                        if(answer.contains("E")) {
                            model.setSelE(true);
                        }
                        list2.add(model);
                    } else {
                        ModelSingle model = new ModelSingle(i);
                        System.out.println(answers);
                        int start = answers.indexOf(":");
                        String answer = answers.substring(start+1);
                        System.out.println("Answer " + i + "= " + answer);
                        if(answer.contains("A")) {
                            model.setSelA(true);
                        }
                        if(answer.contains("B")) {
                            model.setSelB(true);
                        }
                        if(answer.contains("C")) {
                            model.setSelC(true);
                        }
                        if(answer.contains("D")) {
                            model.setSelD(true);
                        }
                        if(answer.contains("E")) {
                            model.setSelE(true);
                        }
                        list2.add(model);
                    }
                }
                customAdapter2 = new CustomAdapterSingle();
                customAdapter2.setOnOptionSelected(this);
                customAdapter2.setModel(list2);
                rView.setAdapter(customAdapter2);
                System.out.println("singleAnswer adapter set");
            }
        }
        backButton = findViewById(R.id.backButtonNav);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent mainIntent = new Intent(answerKeyActivity.this, createRubricActivity.class);
                mainIntent.putExtra("nameOfTest", nameTest);
                mainIntent.putExtra("numOfQuestions", num);
                mainIntent.putExtra("multiAnswers", multiAnswers);
                mainIntent.putExtra("partialCredit", partialCredit);
                mainIntent.putExtra("mode", mode);
                startActivity(mainIntent);
            }
        });
        forwardButton = findViewById(R.id.forwardButtonNav);
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Test name = " + nameTest);
                System.out.println("Number of Questions = " + num);
                System.out.println("mutipleAnswers = " + multiAnswers);
                System.out.println("partialCredit = " + partialCredit);
                String message = "";
                //Storing rubrics
                StringBuilder result = new StringBuilder();

                int prev_rev = prefs_meta.getInt(nameTest+"-Rev", -1);
                editor_meta.putInt(nameTest+"-Rev", prev_rev+1);
                editor_meta.apply();

                if(multiAnswers) {

                    // get the value of selected answers from custom adapter
                    for (int i = 0; i < customAdapter1.modelArrayList.size(); i++) {
                        message = message + "\n" + (i + 1) + " " + customAdapter1.modelArrayList.get(i).getSelected();
                        result.append(i+1+":"+customAdapter1.modelArrayList.get(i).getSelected()+",");
                    }
                    // display the message on screen with the help of Toast.
                    //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                } else {
                    for (int i = 0; i < customAdapter2.modelArrayList.size(); i++) {
                        message = message + "\n" + (i + 1) + " " + customAdapter2.modelArrayList.get(i).getSelected();
                        result.append(i+1+":"+customAdapter2.modelArrayList.get(i).getSelected()+",");
                    }
                    // display the message on screen with the help of Toast.
                    //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
                editor_meta.putString(nameTest+"-answers", result.toString());
                editor_meta.apply();

                SharedPreferences prefsget = getSharedPreferences("datastore", MODE_PRIVATE);
                String trycount = prefsget.getString(nameTest+"-answers",""); //"No name defined" is the default value
                System.out.println("here here");
                System.out.println(trycount);

                Intent mainIntent = new Intent(answerKeyActivity.this, RubricConfirmationActivity.class);
                mainIntent.putExtra("nameOfTest", nameTest);
                mainIntent.putExtra("numOfQuestions", num);
                startActivity(mainIntent);
            }
        });

    }

    public void onOptionSelected(int position, int itemSelected, boolean isChecked) {
        if(multiAnswers) {
            list1.get(position).setSelectedAnswerPositions(itemSelected);
            switch (itemSelected) {
                case 1:
                    list1.get(position).setSelA(isChecked);
                    break;
                case 2:
                    list1.get(position).setSelB(isChecked);
                    break;
                case 3:
                    list1.get(position).setSelC(isChecked);
                    break;
                case 4:
                    list1.get(position).setSelD(isChecked);
                    break;
                case 5:
                    list1.get(position).setSelE(isChecked);
                    break;
            }

        } else {
            list2.get(position).setSelectedAnswerPosition(itemSelected);
            switch (itemSelected) {
                case 1:
                    list2.get(position).setSelA(isChecked);
                    break;
                case 2:
                    list2.get(position).setSelB(isChecked);
                    break;
                case 3:
                    list2.get(position).setSelC(isChecked);
                    break;
                case 4:
                    list2.get(position).setSelD(isChecked);
                    break;
                case 5:
                    list2.get(position).setSelE(isChecked);
                    break;
            }

            customAdapter2.setModel(list2);
            customAdapter2.notifyDataSetChanged();
        }
    }


}
