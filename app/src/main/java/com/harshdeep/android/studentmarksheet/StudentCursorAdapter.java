package com.harshdeep.android.studentmarksheet;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.harshdeep.android.studentmarksheet.data.StudentContract;

/**
 * Created by harshdeepsingh on 12/01/18.
 */

public class StudentCursorAdapter extends CursorAdapter {

    private static int IdColumnId, NameColumnId, RollNoColumnId, phyMarksColumnId, ChemMarksColumnId, EngMarksColumnId, MathsMarksColumnId;

    public StudentCursorAdapter(Context context, Cursor c) {
        super(context, c,0);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.listitem_view,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        IdColumnId=cursor.getColumnIndex(StudentContract.MarksEntry._ID);
        NameColumnId=cursor.getColumnIndex(StudentContract.MarksEntry.STUDENT_NAME);
        RollNoColumnId=cursor.getColumnIndex(StudentContract.MarksEntry.STUDENT_ROLLNO);
        phyMarksColumnId=cursor.getColumnIndex(StudentContract.MarksEntry.MARKS_PHY);
        ChemMarksColumnId=cursor.getColumnIndex(StudentContract.MarksEntry.MARKS_CHEM);
        EngMarksColumnId=cursor.getColumnIndex(StudentContract.MarksEntry.MARKS_ENG);
        MathsMarksColumnId=cursor.getColumnIndex(StudentContract.MarksEntry.MARKS_MATHS);

        TextView textView = (TextView)view.findViewById(R.id.name);
        textView.setText(cursor.getString(NameColumnId));

        textView = view.findViewById(R.id.roll);
        textView.setText(String.valueOf(cursor.getInt(RollNoColumnId)));

        textView = view.findViewById(R.id.maths);
        textView.setText(String.valueOf(cursor.getInt(MathsMarksColumnId)));

        textView = view.findViewById(R.id.phy);
        textView.setText(String.valueOf(cursor.getInt(phyMarksColumnId)));

        textView = view.findViewById(R.id.chem);
        textView.setText(String.valueOf(cursor.getInt(ChemMarksColumnId)));

        textView = view.findViewById(R.id.eng);
        textView.setText(String.valueOf(cursor.getInt(EngMarksColumnId)));

    }
}
