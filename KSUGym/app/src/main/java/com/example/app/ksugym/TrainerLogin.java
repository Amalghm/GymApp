package com.example.app.ksugym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TrainerLogin extends AppCompatActivity {
    Button LoginBtn;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_login);

        LoginBtn = findViewById(R.id.trainerLoginBtn);
        username = findViewById(R.id.trainerUsername);
        password = findViewById(R.id.editTtrainerPassword);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().toLowerCase().equals("trainer123")
                        && password.getText().toString().equals("123456"))
                {
                    Toast.makeText(getApplicationContext(),"Trainer logged in Successfully!",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), TrainerNavigation.class);
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
