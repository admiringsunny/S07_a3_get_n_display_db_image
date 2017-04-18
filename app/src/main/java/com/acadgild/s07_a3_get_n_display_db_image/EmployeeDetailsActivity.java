package com.acadgild.s07_a3_get_n_display_db_image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class EmployeeDetailsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        DB db = new DB(getApplicationContext());

        List<Employee> employees = db.readAllEmployees();

        String[] empNames = new String[employees.size()];
        String[] empAges = new String[employees.size()];
        Bitmap[] empImg = new Bitmap[employees.size()];

        for (int i = 0; i < employees.size(); i++) {
            empNames[i] = employees.get(i).getName();
            empAges[i] = employees.get(i).getAge();
            empImg[i] = Util.getImage(employees.get(i).getImage());
        }

        int position = getIntent().getIntExtra("position", 0);

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(empNames[position]);

        TextView age = (TextView) findViewById(R.id.age);
        age.setText(empAges[position]);

        ImageView img_1 = (ImageView) findViewById(R.id.img_1);
        img_1.setImageBitmap(empImg[position]);


    }
}
