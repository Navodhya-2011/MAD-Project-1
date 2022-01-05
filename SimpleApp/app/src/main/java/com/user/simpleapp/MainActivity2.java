package com.user.simpleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity {
    //create objects
    Button btn;
    TextView txtUsername;
    TextView txtAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //link java components with xml components
        btn = findViewById(R.id.btn2);
        txtUsername = findViewById(R.id.txt2);
        txtAge = findViewById(R.id.txtAge);

        Intent intent = getIntent();
        //String name = intent.getStringExtra("USERNAME");

        //get the passed data
        Bundle extras = intent.getExtras();
        //assign data to variables
        String name = extras.getString("USERNAME");
        int age = extras.getInt("USERAGE");

        //display data
        txtUsername.setText(name);
        txtAge.setText(String.valueOf(age)) ;

        //create intent(again redirect to the MainActivity)
        btn.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        });
    }
}