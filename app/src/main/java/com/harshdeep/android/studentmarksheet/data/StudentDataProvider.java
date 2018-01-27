package com.harshdeep.android.studentmarksheet.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by harshdeepsingh on 10/01/18.
 */

public class StudentDataProvider extends ContentProvider {

    private StudentDbHelper studentDbHelper;
    private SQLiteDatabase database;

    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    static final int STUDENTS = 100;
    static final int INDIVID_STUDENT = 101;

    static {
        matcher.addURI(StudentContract.CONTENT_AUTHORITY, StudentContract.MARKS_PATH,STUDENTS);
        matcher.addURI(StudentContract.CONTENT_AUTHORITY, StudentContract.MARKS_PATH+"/#",INDIVID_STUDENT);
    }

    @Override
    public boolean onCreate() {
        studentDbHelper=new StudentDbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        database = studentDbHelper.getReadableDatabase();
        Cursor cursor=null;
        switch(matcher.match(uri)){
            case STUDENTS:
                cursor=database.query(StudentContract.MarksEntry.TABLE_NAME,strings,s,strings1,s1,null,null);
                break;
            case INDIVID_STUDENT:
                long rowID=ContentUris.parseId(uri);
                s= StudentContract.MarksEntry._ID+" =?";
                strings1=new String[]{String.valueOf(rowID)};
                cursor=database.query(StudentContract.MarksEntry.TABLE_NAME,strings,s,strings1,s1,null,null);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), StudentContract.MarksEntry.CONTENT_URI);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        database = studentDbHelper.getWritableDatabase();

        long rowId=database.insert(StudentContract.MarksEntry.TABLE_NAME,null,contentValues);
        getContext().getContentResolver().notifyChange(StudentContract.MarksEntry.CONTENT_URI,null);
        return ContentUris.withAppendedId(uri,rowId);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {

        database=studentDbHelper.getWritableDatabase();
        getContext().getContentResolver().notifyChange(StudentContract.MarksEntry.CONTENT_URI,null);

        switch (matcher.match(uri)){

            case STUDENTS:
                return database.delete(StudentContract.MarksEntry.TABLE_NAME,null,null);

            case INDIVID_STUDENT:
                long rowId = ContentUris.parseId(uri);
                s= StudentContract.MarksEntry._ID+"=?";
                strings=new String[]{String.valueOf(rowId)};
                return database.delete(StudentContract.MarksEntry.TABLE_NAME,s,strings);
        }

        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {

        database=studentDbHelper.getWritableDatabase();

        switch (matcher.match(uri)){

            case INDIVID_STUDENT :
                long rowId = ContentUris.parseId(uri);
                s= StudentContract.MarksEntry._ID+"=?";
                strings=new String[]{String.valueOf(rowId)};
                getContext().getContentResolver().notifyChange(uri,null);
                return database.update(StudentContract.MarksEntry.TABLE_NAME,contentValues,s,strings);

        }
        return 0;
    }
}
