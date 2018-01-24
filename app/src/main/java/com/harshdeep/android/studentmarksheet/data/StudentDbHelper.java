package com.harshdeep.android.studentmarksheet.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by harshdeepsingh on 10/01/18.
 */

public class StudentDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "students.db";

    private static final int DATABASE_VERSION = 1;

    private final String SQL_CREATE_TABLE = "CREATE TABLE "+ StudentContract.MarksEntry.TABLE_NAME+" ( "
            + StudentContract.MarksEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "
            + StudentContract.MarksEntry.STUDENT_NAME+" TEXT NOT NULL, "
            + StudentContract.MarksEntry.STUDENT_ROLLNO+" INTEGER UNIQUE NOT NULL, "
            + StudentContract.MarksEntry.MARKS_CHEM+" INTEGER NOT NULL, "
            + StudentContract.MarksEntry.MARKS_MATHS+" INTEGER NOT NULL, "
            + StudentContract.MarksEntry.MARKS_PHY+" INTEGER NOT NULL, "
            + StudentContract.MarksEntry.MARKS_ENG+" INTEGER NOT NULL );";


    public StudentDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
