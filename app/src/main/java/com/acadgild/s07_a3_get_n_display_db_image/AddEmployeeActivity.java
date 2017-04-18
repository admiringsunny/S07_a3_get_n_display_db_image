package com.acadgild.s07_a3_get_n_display_db_image;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddEmployeeActivity extends Activity {

    public static final int IMAGE_REQUEST_CODE = 20;
    EditText etName, etAge;
    ImageView imagePreview;
    Bitmap bitmapImage;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        db = new DB(this);

        etName = (EditText) findViewById(R.id.et_name);
        etAge = (EditText) findViewById(R.id.et_age);
        imagePreview = (ImageView) findViewById(R.id.image_preview);

    }

    public void browsePhoto(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath()), "image/*");
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
    }

    public void saveEmployee(View view) {
        String age = etAge.getText().toString();
        String name = etName.getText().toString();

        byte[] imageBytes = bitmapImage != null ? Util.getBytes(bitmapImage) : Util.getBytes(BitmapFactory.decodeResource(getResources(), R.drawable.no_photo_yet));

        db.insertEmployee(new Employee(name, age, imageBytes));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == IMAGE_REQUEST_CODE) {
            Uri uri = data.getData();

            InputStream inputStream;

            try {
                inputStream = getContentResolver().openInputStream(uri);
                bitmapImage = BitmapFactory.decodeStream(inputStream);
                imagePreview.setImageBitmap(bitmapImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "No Image Selected", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
