package com.acadgild.s07_a3_get_n_display_db_image;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener{

    public static DB db;
    List<Employee> employees;
    ListAdapter adapter;
    ListView list1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DB(this);
        db.onUpgrade(db.getWritableDatabase(), 1, 2);
        db.insertEmployee(new Employee("Admin", "22", Util.getBytes(BitmapFactory.decodeResource(getResources(), R.drawable.admin_1))));

        employees = db.readAllEmployees();

        final String[] empNames = new String[employees.size()];
        final String[] empAges = new String[employees.size()];
        final Bitmap[] empImg = new Bitmap[employees.size()];
        for (int i = 0; i < employees.size(); i++) {
            empNames[i] = employees.get(i).getName();
            empAges[i] = employees.get(i).getAge();
            empImg[i] = Util.getImage(employees.get(i).getImage());
        }

        list1 = (ListView) findViewById(R.id.list_1);
        adapter = new ListAdapter(this, empNames, empAges, empImg);
        list1.setAdapter(adapter);
        list1.setOnItemClickListener(this);

        getSupportActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), AddEmployeeActivity.class);
        startActivityForResult(intent, 1);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        employees = db.readAllEmployees();

        final String[] empNames = new String[employees.size()];
        final String[] empAges = new String[employees.size()];
        final Bitmap[] empImg = new Bitmap[employees.size()];
        for (int i = 0; i < employees.size(); i++) {
            empNames[i] = employees.get(i).getName();
            empAges[i] = employees.get(i).getAge();
            empImg[i] = Util.getImage(employees.get(i).getImage());
        }

        list1 = (ListView) findViewById(R.id.list_1);
        adapter = new ListAdapter(this, empNames, empAges, empImg);
        list1.setAdapter(adapter);
        list1.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getApplicationContext(), EmployeeDetailsActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

}
