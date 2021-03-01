package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
//    private TextView id;
//    private TextView title;
//    private TextView code;
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    private TextView addButton;
    private DatabaseHelper dbHelper;
    List<Course> courseList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this);
        setupUI();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToAddCourse();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }

    private void setupUI(){
//        id = findViewById(R.id.displayidTextView);
//        title = findViewById(R.id.displayTitleTextView);
//        code = findViewById(R.id.displayCodeTextView);
        addButton = findViewById(R.id.addCourseBtn);
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void goToAddCourse(){
//        Intent intent = new Intent(this, AddCourse.class);
//        startActivity(intent);
        AddCourseFragment fragment = new AddCourseFragment();
        getSupportFragmentManager().beginTransaction().add(fragment, null).commit();
    }

    public void updateList(){
        courseList = dbHelper.readCourse();
        adapter = new CustomAdapter(courseList);
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        updateList();
        if (courseList != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new CustomAdapter(courseList);
            recyclerView.setAdapter(adapter);

//            id.setText(String.valueOf(course.getId()));
//            title.setText(course.getTitle());
//            code.setText(course.getCode());
        }
    }

}