package com.user.complex_listview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    //create string resources
    String title[] = {"Tony Stark", "Peter Parker", "Wanda Maximoff", "Dr. Strange", "Thor"};
    String phone[] = {"0783759307", "0712648597", "0776483726", "0777984562", "0705672341"};
    int image[] ={R.drawable.tony, R.drawable.peter, R.drawable.wanda, R.drawable.strange, R.drawable.thor};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView); //define listView
        //create customAdapter object and pass the data stored in the arrays
        CustomAdapter adapter = new CustomAdapter(this,title,image,phone);
        listView.setAdapter(adapter); //set adapter
    }
}

class CustomAdapter extends ArrayAdapter<String>{ //adapter class
    Context context; //context object
    String[] title; //title string
    int[] images;
    String[] phone;

    CustomAdapter(Context context, String[] title, int[] images, String[] phone){ //define value passing parameters
     super(context, R.layout.single_row,R.id.title,title); //assign values to the single layout
     this.context = context; //set the context
     this.images = images;//assign values to the image array
     this.title = title;//assign values to the title array
     this.phone = phone;//assign values to the title array

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) { //view method
        //define inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View row = inflater.inflate(R.layout.single_row,parent,false); //assign the constraint layout in single row

        ImageView imageView = row.findViewById(R.id.imageView); //create objects and define ids
        TextView titleView = row.findViewById(R.id.title);//create objects and define ids
        TextView phoneView = row.findViewById(R.id.phone);//create objects and define ids

        imageView.setImageResource(images[position]);//set the image to imageView
        titleView.setText(title[position]);//set the title to titleView
        phoneView.setText(phone[position]);//set the phone number to phoneView

        return row;
    }
}