package com.acadgild.s07_a3_get_n_display_db_image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ImageView empImage;
    TextView empName, empAge;
    String[] empNames, empAges;
    Bitmap[] empImages;

    public ListAdapter(Context context, String[] empNames, String[] empAges, Bitmap[] empImages) {
        this.context = context;
        this.empNames = empNames;
        this.empAges = empAges;
        this.empImages = empImages;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() { return empNames.length; }

    @Override
    public Object getItem(int position) { return empNames[position]; }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.list_adapter, null);

        empName = (TextView) convertView.findViewById(R.id.emp_name);
        empAge = (TextView) convertView.findViewById(R.id.emp_age);
        empImage = (ImageView) convertView.findViewById(R.id.emp_image);

        empName.setText(empNames[position]);
        empAge.setText(empAges[position]);
//        empImage.setImageBitmap(BitmapFactory.decodeResource(convertView.getResources(), R.drawable.acadgild));
        empImage.setImageBitmap(empImages[position]);

        return convertView;
    }
}