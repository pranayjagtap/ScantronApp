package com.example.mobilecomputingproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class CustomAdapterSingle extends RecyclerView.Adapter<CustomAdapterSingle.ViewHolder> {

    public static ArrayList<ModelSingle> modelArrayList;

    public void setOnOptionSelected(OnOptionSelected onOptionSelected) {
        this.onOptionSelected = onOptionSelected;
    }

    private OnOptionSelected onOptionSelected;

    public ArrayList<ModelSingle> getModel() {
        return modelArrayList;
    }

    public void setModel(ArrayList<ModelSingle> modelArrayList) {
        this.modelArrayList = modelArrayList;
    }

    public int getItemCount() {
        if(modelArrayList != null) {
            return modelArrayList.size();
        } else
            return 0;
    }

    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected RadioButton rA;
        protected RadioButton rB;
        protected RadioButton rC;
        protected RadioButton rD;
        protected RadioButton rE;
        private TextView question;

        ViewHolder(View view) {
            super(view);
            question = (TextView) view.findViewById(R.id.QuestionNum);
            rA       = (RadioButton) view.findViewById(R.id.radioA);
            rB       = (RadioButton) view.findViewById(R.id.radioB);
            rC       = (RadioButton) view.findViewById(R.id.radioC);
            rD       = (RadioButton) view.findViewById(R.id.radioD);
            rE       = (RadioButton) view.findViewById(R.id.radioE);
            rA.setOnClickListener(this);
            rB.setOnClickListener(this);
            rC.setOnClickListener(this);
            rD.setOnClickListener(this);
            rE.setOnClickListener(this);
        }
        public void onClick (View view) {
            switch (view.getId()) {
                case R.id.radioA:
                    if(rA.isChecked()) {
                        onOptionSelected.onOptionSelected(getAdapterPosition(), 1, true);
                        break;
                    } else {
                        onOptionSelected.onOptionSelected(getAdapterPosition(), 1, false);
                        break;
                    }
                case R.id.radioB:
                    if(rB.isChecked()) {
                        onOptionSelected.onOptionSelected(getAdapterPosition(), 2, true);
                        break;
                    } else {
                        onOptionSelected.onOptionSelected(getAdapterPosition(), 2, false);
                        break;
                    }
                case R.id.radioC:
                    if(rC.isChecked()) {
                        onOptionSelected.onOptionSelected(getAdapterPosition(), 3, true);
                        break;
                    } else {
                        onOptionSelected.onOptionSelected(getAdapterPosition(), 3, false);
                        break;
                    }
                case R.id.radioD:
                    if(rD.isChecked()) {
                        onOptionSelected.onOptionSelected(getAdapterPosition(), 4, true);
                        break;
                    } else {
                        onOptionSelected.onOptionSelected(getAdapterPosition(), 4, false);
                        break;
                    }
                case R.id.radioE:
                    if(rE.isChecked()) {
                        onOptionSelected.onOptionSelected(getAdapterPosition(), 5, true);
                        break;
                    } else {
                        onOptionSelected.onOptionSelected(getAdapterPosition(),5,false);
                        break;
                    }

            }
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_layout_single, parent, false);
        return new ViewHolder(v);
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.question.setText(""+modelArrayList.get(position).getqNumber());
        viewHolder.rA.setChecked(modelArrayList.get(position).isSelA());
        viewHolder.rB.setChecked(modelArrayList.get(position).isSelB());
        viewHolder.rC.setChecked(modelArrayList.get(position).isSelC());
        viewHolder.rD.setChecked(modelArrayList.get(position).isSelD());
        viewHolder.rE.setChecked(modelArrayList.get(position).isSelE());
    }
}
