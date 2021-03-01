package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddCourse extends AppCompatActivity {

    private TextView courseTitle;
    private TextView courseCode;
    private DatabaseHelper dbHelper;
    private Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        dbHelper = new DatabaseHelper(this);
        setupUI();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course course = new Course(0, courseTitle.getText().toString(), courseCode.getText().toString());
                dbHelper.insertCourse(course);
            }
        });
    }

    private void setupUI(){
        courseCode = findViewById(R.id.CourseCordeTextView);
        courseTitle = findViewById(R.id.CourseTitleTextView);
        saveButton = findViewById(R.id.saveBtn);
    }
}