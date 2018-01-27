package com.harshdeep.android.studentmarksheet;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.NavUtils;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.harshdeep.android.studentmarksheet.data.StudentContract;

public class EnterDetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    Uri currentStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=getIntent();
        currentStudent=intent.getData();

        if(currentStudent!=null){
            setTitle("Edit Student");
            getSupportLoaderManager().initLoader(0,null,this);
        }

        setContentView(R.layout.activity_enter_detail);
    }

    private void getDetailsAndSaveToDatabase(){
        EditText editText=(EditText)findViewById(R.id.studentName);
        String name=editText.getText().toString();

        editText=(EditText)findViewById(R.id.rollNo);
        int rollNo=Integer.parseInt(editText.getText().toString());

        editText=(EditText)findViewById(R.id.physicsMarks);
        int physicsMarks=Integer.parseInt(editText.getText().toString());

        editText=(EditText)findViewById(R.id.mathsMarks);
        int  mathsMarks=Integer.parseInt(editText.getText().toString());

        editText=(EditText)findViewById(R.id.chemMarks);
        int  chemMarks=Integer.parseInt(editText.getText().toString());

        editText=(EditText)findViewById(R.id.engMarks);
        int  engMarks=Integer.parseInt(editText.getText().toString());

        if(chemMarks>=0 && chemMarks<=100 && mathsMarks>=0 && mathsMarks<=100 && physicsMarks>=0 && physicsMarks<=100 && engMarks>=0 && engMarks<=100)
        {Log.v("bggf",name+" "+rollNo+" "+ mathsMarks);

            ContentValues values = new ContentValues();
            values.put(StudentContract.MarksEntry.STUDENT_NAME,name);
            values.put(StudentContract.MarksEntry.STUDENT_ROLLNO,rollNo);
            values.put(StudentContract.MarksEntry.MARKS_PHY,physicsMarks);
            values.put(StudentContract.MarksEntry.MARKS_MATHS,mathsMarks);
            values.put(StudentContract.MarksEntry.MARKS_CHEM,chemMarks);
            values.put(StudentContract.MarksEntry.MARKS_ENG,engMarks);

            if(currentStudent==null)
            {
                getContentResolver().insert(StudentContract.MarksEntry.CONTENT_URI,values);
                Toast.makeText(EnterDetailActivity.this, "Details Added Successfully", Toast.LENGTH_SHORT).show();
            }
            else {
                if(getContentResolver().update(currentStudent,values,null,null)!=0)
                Toast.makeText(EnterDetailActivity.this, "Details Updated Successfully", Toast.LENGTH_SHORT).show();
            }
            finish();
        }

        else if(chemMarks<0 || mathsMarks<0 || physicsMarks<0 || engMarks<0){
            Toast.makeText(EnterDetailActivity.this, "Marks can't be negative!", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(EnterDetailActivity.this, "Marks can't be greater than 100!", Toast.LENGTH_SHORT).show();
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,currentStudent,null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        int  NameColumnId, RollNoColumnId, phyMarksColumnId, ChemMarksColumnId, EngMarksColumnId, MathsMarksColumnId;

        NameColumnId=data.getColumnIndex(StudentContract.MarksEntry.STUDENT_NAME);
        RollNoColumnId=data.getColumnIndex(StudentContract.MarksEntry.STUDENT_ROLLNO);
        phyMarksColumnId=data.getColumnIndex(StudentContract.MarksEntry.MARKS_PHY);
        ChemMarksColumnId=data.getColumnIndex(StudentContract.MarksEntry.MARKS_CHEM);
        EngMarksColumnId=data.getColumnIndex(StudentContract.MarksEntry.MARKS_ENG);
        MathsMarksColumnId=data.getColumnIndex(StudentContract.MarksEntry.MARKS_MATHS);
        data.moveToNext();
        EditText name=(EditText) findViewById(R.id.studentName);
        name.setText(data.getString(NameColumnId));

        EditText roll = findViewById(R.id.rollNo);
        roll.setText(data.getString(RollNoColumnId));

        EditText marks = findViewById(R.id.physicsMarks);
        marks.setText(data.getString(phyMarksColumnId));

        marks = findViewById(R.id.mathsMarks);
        marks.setText(data.getString(MathsMarksColumnId));

        marks = findViewById(R.id.chemMarks);
        marks.setText(data.getString(ChemMarksColumnId));

        marks = findViewById(R.id.engMarks);
        marks.setText(data.getString(EngMarksColumnId));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_enter_detail,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int optionId = item.getItemId();

        switch (optionId){
            case R.id.saveButton:
                getDetailsAndSaveToDatabase();
                break;

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
        }
        return true;
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
