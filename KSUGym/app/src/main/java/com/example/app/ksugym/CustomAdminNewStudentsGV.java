package com.example.app.ksugym;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdminNewStudentsGV extends BaseAdapter
{
    private Context mContext;
    private final String[] name;
    private final String[] number;

    public CustomAdminNewStudentsGV(Context mContext, String[] name, String[]number) {
        this.mContext = mContext;
        this.name = name;
        this.number = number;
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int x, View convertView, ViewGroup parent) {
        View gridViewAndroid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder viewholder = new ViewHolder();

        gridViewAndroid = new View(mContext);
        gridViewAndroid = inflater.inflate(R.layout.custom_newstudents_gridview, null);

        viewholder.adminStudentName= (TextView) gridViewAndroid.findViewById(R.id.adminStudentName);
        viewholder.adminStudentNumber= (TextView) gridViewAndroid.findViewById(R.id.adminStudentNumber);

        viewholder.adminStudentName.setText(name[x]); //Set the names to all cells in the grid
        viewholder.adminStudentNumber.setText(number[x]);


        return gridViewAndroid;
    }
}

