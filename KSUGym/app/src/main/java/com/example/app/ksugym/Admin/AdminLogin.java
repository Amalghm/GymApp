package com.example.app.ksugym.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.ksugym.R;

public class AdminLogin extends AppCompatActivity {

    Button LoginBtn;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        LoginBtn = findViewById(R.id.AdminSignIn);
        username = findViewById(R.id.AdminUsername);
        password = findViewById(R.id.AdminPassword);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().toLowerCase().equals("admin123")
                        && password.getText().toString().equals("123456"))
                {
                    Toast.makeText(getApplicationContext(),"Admin logged in Successfully!",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(),AdminNavigationPage.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Wrong Username or Password!",Toast.LENGTH_LONG).show();

                }
            }
        });


    }
}
