package com.example.sqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private List<Course> coursesList = new ArrayList<>();

    public CustomAdapter(List<Course> courses) {
        coursesList = courses;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_recyclerview,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getDisplayID().setText("ID: " + String.valueOf(coursesList.get(position).getId()));
        holder.getDisplayCourseTitle().setText( "Title: " + coursesList.get(position).getTitle());
        holder.getDisplayCourseCode().setText("Code: " + coursesList.get(position).getCode());
    }

    @Override
    public int getItemCount() {
        return coursesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView displayID;
        private TextView displayCourseTitle;
        private TextView displayCourseCode;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            displayID = itemView.findViewById(R.id.displayidTextView);
            displayCourseTitle = itemView.findViewById(R.id.displayTitleTextView);
            displayCourseCode = itemView.findViewById(R.id.displayCodeTextView);
        }

        public TextView getDisplayID() {
            return displayID;
        }

        public TextView getDisplayCourseTitle() {
            return displayCourseTitle;
        }

        public TextView getDisplayCourseCode() {
            return displayCourseCode;
        }
    }
}


