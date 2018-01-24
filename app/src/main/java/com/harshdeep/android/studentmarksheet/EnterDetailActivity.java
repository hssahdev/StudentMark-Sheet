package com.harshdeep.android.studentmarksheet;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.harshdeep.android.studentmarksheet.data.StudentContract;

public class EnterDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_enter_detail);

        Button button=(Button)findViewById(R.id.doneEnteringDetails);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                    getContentResolver().insert(StudentContract.MarksEntry.CONTENT_URI,values);
                    Toast.makeText(EnterDetailActivity.this, "Details Added Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }

                else if(chemMarks<0 || mathsMarks<0 || physicsMarks<0 || engMarks<0){
                    Toast.makeText(EnterDetailActivity.this, "Marks can't be negative!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(EnterDetailActivity.this, "Marks can't be greater than 100!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
