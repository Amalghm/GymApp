package com.example.app.ksugym.Students;

import android.content.Intent;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.ksugym.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;



public class StudentSubscribe extends AppCompatActivity implements View.OnClickListener {

    //defining view objects
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextPassword1;
    private EditText Name, ID , Hight , Weight;
    private Button buttonSignup;
    private ProgressDialog progressDialog;
    Students student;

    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_subscribe);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //initializing views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPassword1 = (EditText) findViewById(R.id.editTextPassword1);
        Name  =  (EditText) findViewById(R.id.StudentName);
        ID = (EditText) findViewById(R.id.StudentNum);

        /*
        Hight = (EditText) findViewById(R.id.StudentHeight);
        Weight = (EditText) findViewById(R.id.StudentWeight);
        */
        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        buttonSignup.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        //User object to write into my database
        student = new Students( editTextEmail.getText().toString(), editTextPassword.getText().toString(),
        Name.getText().toString(), ID.getText().toString() , "no");

        //calling register method on click
        registerUser();

    }

    private void registerUser() {

        //getting email and password from edit texts
        String email = editTextEmail.getText().toString().trim();
        String name = Name.getText().toString();
        String num = ID.getText().toString();
        final String password = editTextPassword.getText().toString().trim();
        final String password1 = editTextPassword1.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;

        //checking if email and passwords are empty
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please enter email");
            focusView = editTextPassword;
            cancel = true;
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter your Password");
            focusView = editTextPassword;
            cancel = true;
            return;
        }

        if (!(editTextPassword.getText().toString().length() >= 6 )) {
            editTextPassword.setError("Please make sure that your password is more than 6 Digits");
            focusView = editTextPassword;
            cancel = true;
            return;
        }

        if (TextUtils.isEmpty(password1)) {
            editTextPassword1.setError("Please enter your Password again");
            focusView = editTextPassword1;
            cancel = true;
            return;
        }

        if (!(editTextPassword1.getText().toString().length() >= 6 )) {
            editTextPassword1.setError("Please make sure that your password is more than 6 Digits");
            focusView = editTextPassword1;
            cancel = true;
            return;
        }

        if (TextUtils.isEmpty(name)) {
            Name.setError("Please enter your Name");
            focusView = Name;
            cancel = true;
            return;
        }

        if (TextUtils.isEmpty(num)) {
            ID.setError("Please enter your University ID ");
            focusView = ID;
            cancel = true;
            return;
        }

        if (!(ID.getText().toString().length() == 9)) {
            ID.setError("Please enter the right ID");
            focusView = ID;
            cancel = true;
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();


        //creating a new user
        if ( Valid(password, password1) ) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //checking if success
                            if (task.isSuccessful()) {
                                //display some message here
                                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                final DatabaseReference ref = firebaseDatabase.getReference();
                                ref.child("Students").orderByChild("number").equalTo(ID.getText().toString())
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
                                                            Toast.makeText(getApplicationContext(), "Request Successfully Send to Admin", Toast.LENGTH_LONG).show();
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
                            }

                            else {
                                //display some message here
                                Toast.makeText(StudentSubscribe.this, "Invalid Email or Password Error", Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }

                            progressDialog.dismiss();
                        }
                    });

        }

        else {
            //display some message here
            Toast.makeText(StudentSubscribe.this, "Wrong Password", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }
    }

    private boolean Valid(String Pass1, String Pass2)
    {
        Pass1 = editTextPassword.getText().toString().trim();
        Pass2 = editTextPassword1.getText().toString().trim();

        if (Pass1.equals(Pass2))
            return true;
        else
            return false;
    }



}