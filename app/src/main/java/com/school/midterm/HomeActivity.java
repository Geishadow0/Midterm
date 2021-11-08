package com.school.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.BreakIterator;

public class HomeActivity extends AppCompatActivity {


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        TextView textview = findViewById(R.id.Namaview);
        Button button = (Button) findViewById(R.id.buttonlogout);

        Intent get = getIntent();
        Bundle bundle = get.getExtras();


            textview.setText("Hello, " + bundle.getString("name"));




        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }



        public void button_komi(View view){
        Intent intent= new Intent(HomeActivity.this, descbook1.class);
        startActivity(intent);
        }

    public void button_onii(View view){
        Intent intent= new Intent(HomeActivity.this, descbook2.class);
        startActivity(intent);
    }

    public void button_shield(View view){
        Intent intent= new Intent(HomeActivity.this, descbook3.class);
        startActivity(intent);
    }

    public void button_tower(View view){
        Intent intent= new Intent(HomeActivity.this, descbook4.class);
        startActivity(intent);
    }

    public void button_mahouka(View view){
        Intent intent= new Intent(HomeActivity.this, descbook5.class);
        startActivity(intent);
    }

    public void button_contact(View view){
        Intent intent=new Intent(HomeActivity.this,contactus.class);
        startActivity(intent);
    }





}
