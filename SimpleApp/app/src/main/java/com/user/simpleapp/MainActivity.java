package com.user.simpleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //create objects
    Button btnOK;
    Button btnNextPage;
    Button btnEmail;
    TextView textHello;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link java components with xml components
        btnOK = findViewById(R.id.btnHello);
        btnNextPage = findViewById(R.id.btnNextPage);
        btnEmail = findViewById(R.id.btnEmail);

        //set listener to the button
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textHello = findViewById(R.id.txtHello);
                textHello.setText("Hello World");

//                //Toast message
//                Toast toast = Toast.makeText(getApplicationContext(), "OK button clicked", Toast.LENGTH_SHORT);
//                toast.show();
            }
        });

        //create intent(used to redirect to another activity)
        final Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        String name = "Sasindu Nanayakkara";
        int age = 24;

        intent.putExtra("USERNAME",name);
        
        //data passing with bundle
        Bundle extras = new Bundle();
        extras.putString("USERNAME", name);
        extras.putInt("USERAGE", age);

        intent.putExtras(extras);

        //new explicit intent
        Intent intent1 = new Intent(Intent.ACTION_SEND); //define the action
        final Intent chooser; //create chooser
        intent1.setData(Uri.parse("mailto:")); //define data type
        intent1.putExtra(Intent.EXTRA_EMAIL, new String[]{"example@gmail.com"}); //set email address
        intent1.putExtra(Intent.EXTRA_SUBJECT, "the Subject"); //set email subject
        intent1.putExtra(Intent.EXTRA_TEXT, "The email body");// set email body
        intent1.setType("text/plain");
        chooser = Intent.createChooser(intent1, "Send email test app"); //create a list of apps which able to send emails





        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent);
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(chooser);
            }
        });
    }


    public void showToast(View view){

    }
}