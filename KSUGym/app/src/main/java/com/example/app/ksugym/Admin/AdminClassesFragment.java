package com.example.app.ksugym.Admin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.app.ksugym.R;

public class AdminClassesFragment extends Fragment
{
    View view;
    GridView classesGV;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_admin_classes, container, false);
        classesGV = view.findViewById(R.id.adminClassesGV);
        return view;
    }//End of OnCreate

}//end of class
