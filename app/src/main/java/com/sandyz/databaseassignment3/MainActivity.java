package com.sandyz.databaseassignment3;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

   TextView name;
    TextView age;
    ImageView emp_pic;
    private Dbhelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        db = new Dbhelper(MainActivity.this);
        beans emp_one = new beans(BitmapFactory.decodeResource(getResources(),R.drawable.one),"Santosh","25");
        db.openDB();
        db.insertData(emp_one);

        emp_one=db.getAllEmployee();

        name= (TextView)findViewById(R.id.ACTVempname);
        name.setText(emp_one.getName());
        emp_pic= (ImageView)findViewById(R.id.em_pic);
        emp_pic.setImageBitmap(emp_one.getBitmap());
        age= (TextView)findViewById(R.id.ACTVempage);
        age.setText(emp_one.getAge());
    }

    public void onDestroy()
    {
        super.onDestroy();
        db.close();
    }
}
