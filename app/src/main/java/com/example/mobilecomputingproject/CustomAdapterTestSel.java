package com.example.mobilecomputingproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.SharedPreferences;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterTestSel extends RecyclerView.Adapter<CustomAdapterTestSel.ViewHolder> {

    public static ArrayList<RubricModel> modelArrayList;
    private Context mcon;

    public CustomAdapterTestSel(Context con) {
        mcon = con;
    }

    public ArrayList<RubricModel> getModel() {
        return modelArrayList;
    }

    public void setModel(ArrayList<RubricModel> modelArrayList) {
        this.modelArrayList = modelArrayList;
    }

    public int getItemCount() {
        if(modelArrayList != null) {
            return modelArrayList.size();
        } else
            return 0;
    }

    public long getItemID(int i) {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected Button testButton;
        String answers;
        int num;
        boolean multiAnswers;
        boolean partialCredit;

        ViewHolder(View view) {
            super(view);
            testButton = (Button) view.findViewById(R.id.testSel);
            testButton.setOnClickListener(this);
            answers = new String();
            num = 0;
            multiAnswers = false;
            partialCredit = false;
        }
        public void onClick(View view) {
            String mode = modelArrayList.get(0).getMode();
            if(mode.equals("edit")) {
                Intent newIntent = new Intent(mcon, createRubricActivity.class);
                String testName = testButton.getText().toString();
                int t = testName.indexOf(" Rev.");
                testName = testName.substring(0, t);
                System.out.println(testName + " was selected");
                System.out.println("Mode: " + mode);
                System.out.println("numOfQuestions: " + num);
                System.out.println("multiAnswers: " + multiAnswers);
                System.out.println("partialCredit: " + partialCredit);
                System.out.println("answers: " + answers);
                newIntent.putExtra("mode", mode);
                newIntent.putExtra("nameOfTest", testName);
                newIntent.putExtra("numOfQuestions", num);
                newIntent.putExtra("multiAnswers", multiAnswers);
                newIntent.putExtra("partialCredit", partialCredit);
                newIntent.putExtra("answers", answers);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcon.startActivity(newIntent);
            } else if(mode.equals("view")) {
                Intent newIntent = new Intent(mcon, viewGradeStatistics.class);
                String testName = testButton.getText().toString();
                System.out.println(testName + " was selected here");
                System.out.println("Mode: " + mode);
                System.out.println("numOfQuestions: " + num);
                System.out.println("multiAnswers: " + multiAnswers);
                System.out.println("partialCredit: " + partialCredit);
                System.out.println("answers: " + answers);
                newIntent.putExtra("mode", mode);
                newIntent.putExtra("nameOfTest", testName);
                newIntent.putExtra("numOfQuestions", num);
                newIntent.putExtra("multiAnswers", multiAnswers);
                newIntent.putExtra("partialCredit", partialCredit);
                newIntent.putExtra("answers", answers);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcon.startActivity(newIntent);
            }
            else {}
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_test_selection, parent, false);
        return new ViewHolder(v);
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        String text = modelArrayList.get(position).getTestName() + " Rev. " + modelArrayList.get(position).getRev();
        viewHolder.testButton.setText(text);
        viewHolder.answers = modelArrayList.get(position).getAnswers();
        viewHolder.num = modelArrayList.get(position).getqNumbers();
        viewHolder.multiAnswers = modelArrayList.get(position).isMultiAnswers();
        viewHolder.partialCredit = modelArrayList.get(position).isPartialCredit();
    }
}