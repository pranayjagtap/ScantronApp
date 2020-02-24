package com.example.mobilecomputingproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterStudent extends RecyclerView.Adapter<CustomAdapterStudent.ViewHolder> {
    public static ArrayList<ModelStudent> modelArrayList;
    private Context mcon;

    public CustomAdapterStudent(Context con) {
        mcon = con;
    }

    public ArrayList<ModelStudent> getModel() {
        return modelArrayList;
    }

    public void setModel(ArrayList<ModelStudent> modelArrayList) {
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
        protected Button studentButton;

        ViewHolder(View view) {
            super(view);
            studentButton = (Button) view.findViewById(R.id.studentSel);
            studentButton.setOnClickListener(this);
        }

        public void onClick(View view) {
            Intent newIntent = new Intent(mcon, studentStatsActivity.class);
            String studentName = studentButton.getText().toString();
            newIntent.putExtra("student", studentName);
            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mcon.startActivity(newIntent);

        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_student_selection, parent, false);
        return new ViewHolder(v);
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        String text = modelArrayList.get(position).getsName();
        viewHolder.studentButton.setText(text);
    }
}
