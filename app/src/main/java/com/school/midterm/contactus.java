package com.school.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class contactus extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Button btnphone =  findViewById(R.id.phone_btn);
        Button btnemail =  findViewById(R.id.email_btn);

        btnphone.setOnClickListener(this);
        btnemail.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.phone_btn:
                String no = "202-456-1111";
                Intent tlp = new Intent(Intent.ACTION_DIAL);
                tlp.setData(Uri.fromParts("tel", no, null));
                startActivity(tlp);
                break;
            case R.id.email_btn:
                Intent emailIntent=new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+"midterm.project@mail.com"));

                try{
                    startActivity(Intent.createChooser(emailIntent,"Choose.."));
                }catch(android.content.ActivityNotFoundException ex){
                    Toast.makeText(contactus.this, "There's no client email",Toast.LENGTH_SHORT).show();
                }



        }
    }
}