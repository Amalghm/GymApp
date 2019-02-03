package com.example.app.ksugym.Students;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.ksugym.GMailSender;
import com.example.app.ksugym.R;
import com.example.app.ksugym.WelcomePage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class StudentForgetPassword extends AppCompatActivity {

    EditText id;
    Button save;

    String name, email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_forget_password);

        id = findViewById(R.id.forgetID);

        save = findViewById(R.id.forgetSaveBtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference leadersRef = FirebaseDatabase.getInstance().getReference("Students");
                Query query = leadersRef.orderByChild("number").equalTo(id.getText().toString());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot child : snapshot.getChildren()) {
                            if (child.child("number").getValue(String.class).equals(id.getText().toString())) {
                                name = child.child("name").getValue(String.class);
                                email = child.child("email").getValue(String.class);
                                password = child.child("password").getValue(String.class);
                                sendEmail(name, password, email);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }




    public void sendEmail(String name,  String password, final String email)
    {
        final String text = "Hello, "
                + name+"\nYour KSU Gym Application's Password is: " +password;

        final ProgressDialog dialog2 = new ProgressDialog(StudentForgetPassword.this);
        dialog2.setTitle("Sending Email");
        dialog2.setMessage("Please wait");
        dialog2.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender("ksugym2019@gmail.com", "KsuGym*123");
                    sender.sendMail("KSU Gym Subscription!",
                            text,
                            "ksugym2019@gmail.com",
                            email); //Uploader of the book recieves the email
                    dialog2.dismiss();
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
        dialog2.dismiss();

        Toast.makeText(getApplicationContext(),"Password successfully sent to student",Toast.LENGTH_LONG).show();
        Intent i = new Intent(getApplicationContext(), StudentLogin.class);
        startActivity(i);
    }

}//end of class
