package com.example.app.ksugym.Students;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.ksugym.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentSubscribe extends AppCompatActivity {

    Button subscribeBtn;
    EditText name,num,height,weight,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_subscribe);

        name  = findViewById(R.id.StudentName);
        num = findViewById(R.id.StudentNum);
        height = findViewById(R.id.StudentHeight);
        weight = findViewById(R.id.StudentWeight);
        email = findViewById(R.id.StudentEmail);
        password = findViewById(R.id.StudentPassword);

        subscribeBtn = findViewById(R.id.StudentSubscribeBtn);
        subscribeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfAllMandatoryFieldsEntered())
                {
                    //User object to write into my database
                    final Students student = new Students(name.getText().toString(), num.getText().toString(),
                    email.getText().toString(), weight.getText().toString(),height.getText().toString(),
                    password.getText().toString(), "no");

                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    final DatabaseReference ref = firebaseDatabase.getReference();

                    ref.child("Students").orderByChild("number").equalTo(num.getText().toString())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot)
                                {

                                    if (dataSnapshot.exists()) {
                                        Toast.makeText(getApplicationContext(), "This Student ID already exists!", Toast.LENGTH_LONG).show();

                                    } else {

                                        FirebaseDatabase firebaseDatabase2 = FirebaseDatabase.getInstance();
                                        final DatabaseReference ref2 = firebaseDatabase2.getReference();
                                        ref2.child("Students").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                ref2.child("Students").push().setValue(student);
                                                Toast.makeText(getApplicationContext(), "User successfully registered", Toast.LENGTH_LONG).show();
                                                Intent i = new Intent(StudentSubscribe.this,  StudentLogin.class);
                                                startActivity(i);
                                            }
                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {
                                            }
                                        });
                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });

                } //end of check of all mando

            }
        });
    }

    //Check if all fields are filled in
    private boolean checkIfAllMandatoryFieldsEntered() {
        // Reset errors.

        name.setError(null);
        num.setError(null);
        email.setError(null);
        height.setError(null);
        password.setError(null);
        weight.setError(null);

        // Store values at the time of the login attempt.
        String userName = name.getText().toString();
        String useremail = email.getText().toString();
        String userpassword = password.getText().toString();
        String userweight = weight.getText().toString();
        String userheight = height.getText().toString();
        String usernum = num.getText().toString();

        boolean cancel = false;
        View focusView = null;


        if (TextUtils.isEmpty(userpassword)) {
            password.setError(getString(R.string.error_field_required));
            focusView = password;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(useremail)) {
            email.setError(getString(R.string.error_field_required));
            focusView = email;
            cancel = true;
        }
        if (TextUtils.isEmpty(userName)) {
            name.setError(getString(R.string.error_field_required));
            focusView = name;
            cancel = true;
        }
        if (TextUtils.isEmpty(usernum)) {
            num.setError(getString(R.string.error_field_required));
            focusView = num;
            cancel = true;
        }  if (TextUtils.isEmpty(userweight)) {
            weight.setError(getString(R.string.error_field_required));
            focusView = weight;
            cancel = true;
        }if (TextUtils.isEmpty(userheight)) {
           height.setError(getString(R.string.error_field_required));
            focusView = height;
            cancel = true;
        }

        if (cancel) {

            focusView.requestFocus();
        } else {

            return true;
        }
        return false;
    }//end of check text



    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(StudentSubscribe.this,  StudentLogin.class);
        startActivity(i);
    }
}//End of class
