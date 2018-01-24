package com.harshdeep.android.studentmarksheet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    public static boolean loginDone=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login=(Button)findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                checkloginDetails();
            }
        });

        }

        private void checkloginDetails(){

            EditText username=(EditText)findViewById(R.id.userName);
            EditText password=(EditText)findViewById(R.id.password);

//            username.getText().toString().equals("harsh") && password.getText().toString().equals("hssahdev")
            if(true){
                Toast.makeText(LoginActivity.this, "Welcome!!", Toast.LENGTH_SHORT).show();
                loginDone=true;
                Intent enter=new Intent(this,StudentViewActivity.class);
                startActivity(enter);
            }
            else{
                Random rand=new Random();
                int no=rand.nextInt(3)+1;
                if(no==1)
                Toast.makeText(this, "Are you kidding me!?", Toast.LENGTH_SHORT).show();
                else if(no==3)
                    Toast.makeText(this, "You wanna go to jail??", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Wrong password or username..", Toast.LENGTH_SHORT).show();
            }
        }

    }

