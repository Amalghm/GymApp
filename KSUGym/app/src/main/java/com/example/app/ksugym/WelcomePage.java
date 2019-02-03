package com.example.app.ksugym;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.app.ksugym.Admin.AdminLogin;
import com.example.app.ksugym.Students.StudentLogin;
import com.example.app.ksugym.Students.Students;

public class WelcomePage extends AppCompatActivity {

    Button StudentBtn, AdminBtn,trainerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        StudentBtn = findViewById(R.id.StudentBtn);
       StudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomePage.this, StudentLogin.class);
                startActivity(i);
            }
        });

      AdminBtn = findViewById(R.id.AdminBtn);
      AdminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomePage.this,AdminLogin.class);
                startActivity(i);
            }
        });

      trainerBtn = findViewById(R.id.TrainerBtn);
      trainerBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent i = new Intent(WelcomePage.this,TrainerLogin.class);
              startActivity(i);

          }
      });

    }
}
