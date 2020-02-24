package com.example.mobilecomputingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class studentStatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_stats);
        Intent oldIntent = getIntent();
        String studentName = oldIntent.getStringExtra("student");
        TextView name = findViewById(R.id.name);
        TextView scoresview=findViewById(R.id.testscores);
        Button returnButton = findViewById(R.id.returnButton);
        name.setText(studentName);

        //Pranay 12/05

        SharedPreferences prefsget3 = getSharedPreferences("datastore", MODE_PRIVATE);
        String scores = prefsget3.getString(studentName, "");//"No name defined" is the default value.
        System.out.println(studentName);
       System.out.println(scores);
        String iscores[]=scores.split(",");
        String printscores="";
        double total=0.0;
        double _min=Double.MAX_VALUE;
        double _max= Double.MIN_VALUE;
        double _sum=0;
        for(int i=1;i<iscores.length;i++){
            String sc[]=iscores[i].split(":");
            if(Double.parseDouble(sc[1])>_max){
                _max=Double.parseDouble(sc[1]);
            }
            if(Double.parseDouble(sc[1])<_min){
                _min=Double.parseDouble(sc[1]);
            }
            total=total+Double.parseDouble(sc[1]);
            printscores=printscores+iscores[i]+"\n";
        }
        double mean=total/(iscores.length-1);
        printscores=printscores+"Mean % is "+mean+"\n";
        printscores=printscores+"Max score is "+_max+"\n";
        printscores=printscores+"Min score is "+_min+"\n";

        scoresview.setText(printscores);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(studentStatsActivity.this, MainActivity.class);
                startActivity(newIntent);
            }
        });
    }
}
