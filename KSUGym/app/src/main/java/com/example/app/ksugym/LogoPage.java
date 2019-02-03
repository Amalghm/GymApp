package com.example.app.ksugym;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LogoPage extends AppCompatActivity {

    private int TIME_OUT = 3000; //Time to launch the another activity (4 seconds)

    public static SharedPreferences userPreferences; //To save the logged in user at the moment
    public static SharedPreferences.Editor userEditor; //Editor for the user's shared preference

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_page);

        userPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        userEditor = userPreferences.edit();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {

                    Intent i = new Intent(LogoPage.this, WelcomePage.class);
                    startActivity(i);
                    finish();

            }
        }, TIME_OUT);
    }
}
