package com.example.mobilecomputingproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterMulti extends RecyclerView.Adapter<CustomAdapterMulti.ViewHolder> {

    public static ArrayList<ModelMulti> modelArrayList;

    public void setOnOptionSelected(OnOptionSelected onOptionSelected) {
        this.onOptionSelected = onOptionSelected;
    }

    private OnOptionSelected onOptionSelected;

    public ArrayList<ModelMulti> getModel() {
        return modelArrayList;
    }

    public void setModel(ArrayList<ModelMulti> modelArrayList) {
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        protected CheckBox cA;
        protected CheckBox cB;
        protected CheckBox cC;
        protected CheckBox cD;
        protected CheckBox cE;
        private TextView question;

        ViewHolder(View view) {
            super(view);
            question = (TextView) view.findViewById(R.id.QuestionNum);
            cA       = (CheckBox) view.findViewById(R.id.checkA);
            cB       = (CheckBox) view.findViewById(R.id.checkB);
            cC       = (CheckBox) view.findViewById(R.id.checkC);
            cD       = (CheckBox) view.findViewById(R.id.checkD);
            cE       = (CheckBox) view.findViewById(R.id.checkE);
            cA.setOnClickListener(this);
            cB.setOnClickListener(this);
            cC.setOnClickListener(this);
            cD.setOnClickListener(this);
            cE.setOnClickListener(this);
        }

        public void onClick (View view) {
            switch(view.getId()) {
                case R.id.checkA:
                    if(cA.isChecked()) {
                        onOptionSelected.onOptionSelected(getAdapterPosition(), 1, true);
                        break;
                    } else {
                        onOptionSelected.onOptionSelected(getAdapterPosition(), 1, false);
                        break;
                    }
                case R.id.checkB:
                    if(cB.isChecked()) {
                        onOptionSelected.onOptionSelected(getAdapterPosition(), 2, true);
                        break;
                    } else {
                        onOptionSelected.onOptionSelected(getAdapterPosition(), 2,false);
                        break;
                    }
                case R.id.checkC:
                    if(cC.isChecked()) {
                        onOptionSelected.onOptionSelected(getAdapterPosition(), 3, true);
                        break;
                    } else {
                        onOptionSelected.onOptionSelected(getAdapterPosition(), 3, false);
                        break;
                    }
                case R.id.checkD:
                    if(cD.isChecked()) {
                        onOptionSelected.onOptionSelected(getAdapterPosition(), 4, true);
                        break;
                    } else {
                        onOptionSelected.onOptionSelected(getAdapterPosition(), 4, false);
                        break;
                    }
                case R.id.checkE:
                    if(cE.isChecked()) {
                        onOptionSelected.onOptionSelected(getAdapterPosition(), 5, true);
                        break;
                    } else {
                        onOptionSelected.onOptionSelected(getAdapterPosition(), 5, false);
                        break;
                    }
            }
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_layout_multi, parent, false);
        return new ViewHolder(v);
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.question.setText("" + modelArrayList.get(position).getqNumber());
        viewHolder.cA.setChecked(modelArrayList.get(position).isSelA());
        viewHolder.cB.setChecked(modelArrayList.get(position).isSelB());
        viewHolder.cC.setChecked(modelArrayList.get(position).isSelC());
        viewHolder.cD.setChecked(modelArrayList.get(position).isSelD());
        viewHolder.cE.setChecked(modelArrayList.get(position).isSelE());

    }
}
