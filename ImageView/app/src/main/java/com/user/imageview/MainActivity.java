package com.user.imageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String week[] = {"Monday", "Tuesday","Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,R.layout.single_row,R.id.textView,week);
        listView = findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);
    }
}