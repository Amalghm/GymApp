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
import com.example.app.ksugym.WelcomePage;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class StudentLogin extends AppCompatActivity {

    Button StudentLogin, StudentSubscribe, forgetPassBtn;
    EditText number,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        number = findViewById(R.id.StudentNumLogin);
        password = findViewById(R.id.studentPasswordLogin);
        StudentSubscribe = findViewById(R.id.StudentSubscribeBtn);
        StudentLogin = findViewById(R.id.StudentLoginBtn);

        StudentSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), com.example.app.ksugym.Students.StudentSubscribe.class);
                startActivity(i);
            }
        });

        forgetPassBtn = findViewById(R.id.studentForgetPasswordBtn);
        forgetPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), StudentForgetPassword.class);
                startActivity(i);
            }
        });
        StudentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfAllMandatoryFieldsEntered())
                {
                    DatabaseReference mDatabaseReference =
                            FirebaseDatabase.getInstance().getReference().child("Students");
                    final Query query = mDatabaseReference;
                    query.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            if (dataSnapshot.getValue() != null) {
                                Students student = dataSnapshot.getValue(Students.class);
                                //Check login in
                                if(student.getNumber().equals(number.getText().toString())
                                        && student.getPassword().equals(password.getText().toString())
                                        &&student.getSubsicibed().equals("yes"))
                                {//For main page

                                    Toast.makeText(getApplicationContext(), "Logged in Successfully", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(StudentLogin.this,StudentsNavigationPage.class);
                                    startActivity(i);

                                }

                            }//End of loop
                            //if i didn't log in
                            Crouton.makeText(StudentLogin.this, "Invalid Login", Style.INFO).show();

                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }//End of if check
            }
        });

    }

    //Check if all fields are filled in
    private boolean checkIfAllMandatoryFieldsEntered() {
        // Reset errors.

        number.setError(null);
        password.setError(null);

        // Store values at the time of the login attempt.
        String userpassword = password.getText().toString();
        String usernum = number.getText().toString();

        boolean cancel = false;
        View focusView = null;


        if (TextUtils.isEmpty(userpassword)) {
            password.setError(getString(R.string.error_field_required));
            focusView = password;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(usernum)) {
            number.setError(getString(R.string.error_field_required));
            focusView = number;
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
        Intent i = new Intent(StudentLogin.this, WelcomePage.class);
        startActivity(i);
    }
}//end of class
