package com.example.sqlite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCourseFragment extends DialogFragment {
    private TextView courseTitle;
    private TextView courseCode;
    private DatabaseHelper dbHelper;
    private Button saveButton;

    public AddCourseFragment() {
        //Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_add_course, container, false);
        dbHelper = new DatabaseHelper(view.getContext());
        setupUI(view);
        return view;
    }

    private void setupUI(View view){
        courseCode = view.findViewById(R.id.CourseCordeTextView);
        courseTitle = view.findViewById(R.id.CourseTitleTextView);
        saveButton = view.findViewById(R.id.saveBtn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course course = new Course(0, courseTitle.getText().toString(), courseCode.getText().toString());
                dbHelper.insertCourse(course);
                ((MainActivity)getActivity()).updateList();
                getDialog().dismiss();
            }
        });
    }
}
