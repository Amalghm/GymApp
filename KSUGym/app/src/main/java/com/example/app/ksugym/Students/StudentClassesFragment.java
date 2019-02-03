package com.example.app.ksugym.Students;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.app.ksugym.R;

public class StudentClassesFragment extends Fragment
{
    View view;
    GridView newsGV;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_student_classes, container, false);
        newsGV= view.findViewById(R.id.adminNewsGV);
        return view;
    }//End of OnCreate

}//end of class