package com.harshdeep.android.studentmarksheet.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by harshdeepsingh on 10/01/18.
 */

public class StudentContract {

    public static final String CONTENT_AUTHORITY = "com.harsh.android.students";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String MARKS_PATH = "Marks";


    private StudentContract(){}

    public static class MarksEntry implements BaseColumns {

        public static final String TABLE_NAME="Marks";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,TABLE_NAME);

         public static final String _ID=BaseColumns._ID;
         public static final String STUDENT_NAME="name";
         public static final String STUDENT_ROLLNO="roll_no";
         public static final String MARKS_MATHS="maths";
         public static final String MARKS_CHEM="chem";
         public static final String MARKS_PHY="phy";
         public static final String MARKS_ENG="english";

    }
}
