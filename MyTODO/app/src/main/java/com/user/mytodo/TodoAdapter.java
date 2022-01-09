package com.user.mytodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TodoAdapter extends ArrayAdapter<Todo> {

    private Context context;
    private int resource;
    List<Todo> todos;

    TodoAdapter(Context context, int resource, List<Todo> todos){
        super(context, resource, todos);
        this.context = context;
        this.resource = resource;
        this.todos = todos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context); //create new inflater
        View row = inflater.inflate(resource,parent, false); //convert the xml file

        //connect with xml components
        TextView title = row.findViewById(R.id.todoTitle);
        TextView description = row.findViewById(R.id.description);
        ImageView imageView = row.findViewById(R.id.finished);

        Todo toDo = todos.get(position); //get the relevant object
        title.setText(toDo.getTitle());// set the title
        description.setText(toDo.getDescription());// set the description
        imageView.setVisibility(row.INVISIBLE);//set the image visibility

        if(toDo.getFinished() > 0){ //if todoTask finished
            imageView.setVisibility(View.VISIBLE);
        }
        return row;
    }
}
