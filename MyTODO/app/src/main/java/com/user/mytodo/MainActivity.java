package com.user.mytodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //initialize objects
    private Button btnAdd;
    private ListView listView;
    private TextView count;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connect with xml using ids
        btnAdd = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.todoList);
        count = findViewById(R.id.count);
        context = this;

        btnAdd.setOnClickListener(new View.OnClickListener() {  //onClickLister
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, AddTodo.class));//create intent class to redirect to AddTodo activity
            }
        });
    }
}