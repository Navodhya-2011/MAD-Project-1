package com.user.mytodo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //initialize objects
    private Button btnAdd;
    private ListView listView;
    private TextView count;
    Context context;
    private DBHandler dbHandler;
    private List<Todo> toDos;

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
        toDos = new ArrayList<>(); //create new arraylist

        toDos = dbHandler.getAllTodos(); //get all records from the database

        TodoAdapter adapter = new TodoAdapter(context,R.layout.single_todo,toDos);

        listView.setAdapter(adapter);
        //get todoFields count from the database table
        int countTodo = dbHandler.countToDo();
        count.setText("You have "+countTodo+ " todos");

        btnAdd.setOnClickListener(new View.OnClickListener() {  //onClickLister
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, AddTodo.class));//create intent class to redirect to AddTodo activity
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //listView click event
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Todo todo = toDos.get(i);//get the relevant record
                AlertDialog.Builder builder = new AlertDialog.Builder(context); //create alter message box
                builder.setTitle(todo.getTitle());//create title
                builder.setMessage(todo.getDescription());//create message
                if(todo.getFinished() > 0){
                    builder.setMessage("Finished at " + todo.getFinished());
                }

                builder.setPositiveButton("Finished", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        todo.setFinished(System.currentTimeMillis());
                        dbHandler.updateSingleTodo(todo);
                        startActivity(new Intent(context, MainActivity.class));
                    }
                });
                builder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //create intent object to redirect to EditTodo class
                        Intent intent = new Intent(context, EditTodo.class);
                        intent.putExtra("id",String.valueOf(todo.getId())); //pass the value of id
                        startActivity(intent);//redirect
                    }
                });
                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.deleteTodo(todo.getId());
                        startActivity(new Intent(context,MainActivity.class));
                    }
                });
                builder.show();//display the alert box
            }
        });
    }
}