package com.example.itemdbtest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tV;
    public static final String CONTENT_AUTHORITY = "content://fit2081.app.yanming";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tV = findViewById(R.id.num_item);
        Uri uri = Uri.parse("content://fit2081.app.yanming");
        Cursor result = getContentResolver().query(uri,null,null,null);
        tV.setText(Integer.toString(result.getCount()));
    }
}
