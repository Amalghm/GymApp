package com.example.app.ksugym.Students;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.app.ksugym.R;
import com.example.app.ksugym.WelcomePage;

public class StudentsNavigationPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_navigation_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setTitle("Gym News");
       loadFragment(new StudentNewsFragment()); //Inititial fragment
    }

    //Initial fragment is the home page
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()

                    .beginTransaction()
                    .replace(R.id.fragment_container2, fragment)
                    .commit();
            return true;
        }
        return false;
    } //End of LoadFragment



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_studentprofile) {
            getSupportActionBar().setTitle("My Profile");
            fragment = new StudentMyProfileFragment();
        } else if (id == R.id.nav_studentnews) {
            getSupportActionBar().setTitle("Gym News");
            fragment = new StudentNewsFragment();
        } else if (id == R.id.nav_studentclasses) {
            getSupportActionBar().setTitle("Gym Classes");
            fragment = new StudentClassesFragment();
        } else if (id == R.id.nav_studentlogout) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Log Out")
                    .setMessage("Are you sure you want to Log Out?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //   CurrentUser.clearUserPrefernece();
                            Intent intent = new Intent(StudentsNavigationPage.this, WelcomePage.class)
                                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            finish();
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return loadFragment(fragment);
    }
}
