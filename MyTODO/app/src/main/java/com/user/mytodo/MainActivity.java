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
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        dbHandler = new DBHandler(context);

        //connect with xml using ids
        btnAdd = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.todoList);
        count = findViewById(R.id.count);


        //get todoFields count from the database table
        int countTodo = dbHandler.countToDo();
        count.setText("You have "+countTodo+ " todos");

        btnAdd.setOnClickListener(new View.OnClickListener() {  //onClickLister
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, AddTodo.class));//create intent class to redirect to AddTodo activity
            }
        });
    }
}