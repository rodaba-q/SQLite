package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "DatabaseHelper";

    public static final int DATABASE_VERSION =1;
    public static String DATABASE_NAME = Config.DATABASE_NAME;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //COURSE TABLE
        String CREATE_COURSE_TABLE = "CREATE TABLE " + Config.TABLE_COURSE + " ("
                + Config.COLUMN_COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_COURSE_TITLE + " TEXT NOT NULL, "
                + Config.COLUMN_COURSE_CODE + " TEXT NOT NULL "
                + ") ";
        // CREATE TAbLEcourse -> is we do not add the space after table

        Log.d(TAG, "onCreate: Table creating: " +CREATE_COURSE_TABLE);
        db.execSQL(CREATE_COURSE_TABLE);

        Log.d(TAG, "onCreate: Database Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_COURSE);

        //Create table again
        onCreate(db);
    }

    public long insertCourse(Course course) {
        long id = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Config.COLUMN_COURSE_TITLE, course.getTitle());
        values.put(Config.COLUMN_COURSE_CODE, course.getCode());
        try{
            id = db.insert(Config.TABLE_COURSE, null, values);
        } catch (SQLiteException e) {
            Log.d(TAG, "insertCourse: " + e.getMessage());
        } finally {
            db.close();
        }

        return id;
    }

    public List<Course> readCourse(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Course> coursesList = new ArrayList<>();
        Course course = null;
        Cursor cursor = null;
        try {
            cursor = db.query(Config.TABLE_COURSE, null, null,
                    null, null, null, null);
            if (cursor.moveToFirst()){
                List<Course> courseList = new ArrayList<>();
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_COURSE_ID));
                    String title = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COURSE_TITLE));
                    String code = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COURSE_CODE));
                    course = new Course(id, title, code);
                    courseList.add(course);
                }
                while(cursor.moveToNext());
                return courseList;
            }

        } catch ( SQLiteException e){
            Log.d(TAG, "readCourse: Error: " + e.getMessage());

        } finally {
            if (cursor != null){
                cursor.close();
            }
            db.close();
        }
        return Collections.EMPTY_LIST;
    }

}
