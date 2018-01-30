package com.example.abdulsamadkhan.audiotest;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class report extends AppCompatActivity {

    private DatabaseReference mDatabase,mDatabase1;
    private TextView tname,tresult,tBPs,tBPd,tage,tpulse,tgender;
    private static final String TAG = "client";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));
        setTitle("Report");
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }




        tname=(TextView)findViewById(R.id.tname);
        tresult=(TextView)findViewById(R.id.tresult);
        tBPs=(TextView)findViewById(R.id.tBPs);
        tBPd=(TextView)findViewById(R.id.tBPd);
        tage=(TextView)findViewById(R.id.tage);
        tpulse=(TextView)findViewById(R.id.tpulse);
        tgender=(TextView)findViewById(R.id.tgender);
        String name = getIntent().getExtras().getString("name_re");
        String user_spd = getIntent().getExtras().getString("user_re");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users/"+user_spd+"/Patients/" + name);
        mDatabase1 = FirebaseDatabase.getInstance().getReference().child("Users/"+user_spd+"/Visit1data/" + name+"/VitalSign");


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String Name = (String) dataSnapshot.child("patientName").getValue();
                String Age = (String) dataSnapshot.child("patientAge").getValue();
                String Gender = (String) dataSnapshot.child("patientGender").getValue();
                String result =(String) dataSnapshot.child("/Result").getValue();

                tname.setText(Name);
                tage.setText(Age);
                tgender.setText(Gender);
                tresult.setText(result);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String BPd=(String) dataSnapshot.child("bpdystole").getValue();
                String BPs=(String) dataSnapshot.child("bpsystole").getValue();
                String pulse =(String) dataSnapshot.child("pulse").getValue();

                tBPd.setText(BPd);
                tBPs.setText(BPs);
                tpulse.setText(pulse);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {



            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
