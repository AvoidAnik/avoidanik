package com.example.chakrilagbe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    private Button btnAllJob;
    private Button btnPostJob;

    //Toolbar
    private FirebaseAuth mAuth;
    private Toolbar toolbar;

    DatabaseReference test = FirebaseDatabase.getInstance().getReference("");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //Clarify
        //  toolbar=findViewById(R.id.toolbar_home);
        getSupportActionBar().setCustomView(R.layout.toolbar);

        //  setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Job Portal App");

        mAuth=FirebaseAuth.getInstance();



        btnAllJob=findViewById(R.id.btn_allJob);
        btnPostJob=findViewById(R.id.btn_PostJob);

        btnAllJob.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),AllJobActivity.class));

            }});

        btnPostJob.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),PostJobActivity.class));
                // test.child("demo").setValue("Hello");

            }});




            }

            @Override
            public boolean onCreateOptionsMenu(Menu menu){
                getMenuInflater().inflate(R.menu.mainmenu,menu);
                return super.onCreateOptionsMenu(menu);
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item){
                switch(item.getItemId()){
                    case R.id.logout:
                        mAuth.signOut();

                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        break;
                }
                return super.onOptionsItemSelected(item);
            }




}