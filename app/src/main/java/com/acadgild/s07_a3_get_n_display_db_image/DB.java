package com.acadgild.s07_a3_get_n_display_db_image;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class DB extends SQLiteOpenHelper {


    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "employee_db";
    private static final String TABLE = "employee";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String AGE = "age";
    private static final String IMAGE = "image";
    private static final String[] COLUMNS = {ID, NAME, AGE};


    public DB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + " ( "
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT, "
                + AGE + " TEXT, "
                + IMAGE + " BLOB);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        this.onCreate(db);
    }

    public void insertEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, employee.getName());
        values.put(AGE, employee.getAge());
        values.put(IMAGE,  employee.getImage());
        db.insert(TABLE, null, values);
    }

    public Employee readEmployee(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE, COLUMNS, ID + " = ? ", new String[]{String.valueOf(id)}, null, null, null, null);

        Employee employee = new Employee();
        employee.setId(Integer.parseInt(cursor.getString(0)));

        return employee;
    }

    public List<Employee> readAllEmployees() {
        List<Employee> allEmployees = new LinkedList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.setId(Integer.parseInt(cursor.getString(0)));
                employee.setName(cursor.getString(1));
                employee.setAge(cursor.getString(2));
                employee.setImage(cursor.getBlob(3));

                allEmployees.add(employee);
            } while (cursor.moveToNext());
        }
        return allEmployees;
    }
}
