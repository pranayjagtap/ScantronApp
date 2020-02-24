package com.example.mobilecomputingproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.content.SharedPreferences;
import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.INTERNET},200);

        Button create_rubric_b    = findViewById(R.id.create_rubric_button);
        Button edit_rubric_b      = findViewById(R.id.edit_rubric_button);
        Button delete_rubric_b    = findViewById(R.id.delete_rubric_button);
        Button grade_test_b       = findViewById(R.id.grade_test_button);
        Button view_edit_grades_b = findViewById(R.id.edit_view_grades_button);
        Button student_grades_b   = findViewById(R.id.student_grades_button);
        Button test_stats   = findViewById(R.id.teststat);

        create_rubric_b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent cRubricIntent = new Intent(MainActivity.this, createRubricActivity.class);
                cRubricIntent.putExtra("mode", "create");
                startActivity(cRubricIntent);
            }
        });


        edit_rubric_b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent eRubricIntent = new Intent(MainActivity.this, TestSelectionActivity.class);
                eRubricIntent.putExtra("mode", "edit");
                startActivity(eRubricIntent);
            }
        });

        delete_rubric_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dRubricIntent = new Intent(MainActivity.this, DeleteRubricActivity.class);
                startActivity(dRubricIntent);
            }
        });

        grade_test_b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gTestIntent = new Intent(MainActivity.this, TestSelectionActivity.class);
                //new Intent(MainActivity.this, edit_gradeTestSelActivity.class);
                gTestIntent.putExtra("mode", "grade");
                startActivity(gTestIntent);
            }
        });

        view_edit_grades_b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent statisticsIntent = new Intent(MainActivity.this, viewEditGradesStatistics.class);
                statisticsIntent.putExtra("mode", "view");
                startActivity(statisticsIntent);
            }
        });

        student_grades_b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent studentIntent = new Intent(MainActivity.this, StudentSelectionActivity.class);
                studentIntent.putExtra("mode", "view");
                startActivity(studentIntent);
            }
        });
        test_stats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent studentIntent = new Intent(MainActivity.this, TestSelectionActivity.class);
                studentIntent.putExtra("mode", "test-stats");
                startActivity(studentIntent);
            }
        });




        SharedPreferences prefs_meta = getSharedPreferences("datastore", MODE_PRIVATE);
        SharedPreferences.Editor editor_meta = getSharedPreferences("datastore", MODE_PRIVATE).edit();
        Map<String, ?> allEntries = prefs_meta.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().toString());
        }

    }
}
