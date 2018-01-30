package com.example.abdulsamadkhan.audiotest;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Visit1SymVsi extends AppCompatActivity {

    Spinner ChestPain,Nausea,ShortofBreath,Cholestrol,Sweating,NeckPain;
    ArrayAdapter adapter0,adapter1,adapter2,adapter3,adapter4,adapter5,adapter6;
    private String selectCP,selectN,selectSOB,selectC,selectS,selectNP;
    private DatabaseReference mDatabase;
    private EditText BPsystole,BPdystole,Temperature,Heartbeat ;
    private static final String LOG_TAG ="hello1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit1_sym_vsi);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        setTitle("Patient Details");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));
        ChestPain = (Spinner) findViewById(R.id.sChestPain);
        Nausea = (Spinner) findViewById(R.id.sNausea);
        ShortofBreath = (Spinner) findViewById(R.id.sShortofbreath);
        Cholestrol = (Spinner) findViewById(R.id.sCholesterol);
        Sweating = (Spinner) findViewById(R.id.sSweating);
        NeckPain = (Spinner) findViewById(R.id.sNeckPain);
        FloatingActionButton bNext = (FloatingActionButton) findViewById(R.id.bNext);
        BPdystole = (EditText) findViewById(R.id.etdystole);
        BPsystole = (EditText) findViewById(R.id.etsystole);

        Temperature = (EditText) findViewById(R.id.etTemperature);
        Heartbeat = (EditText) findViewById(R.id.etHeartBeat);
        mDatabase = FirebaseDatabase.getInstance().getReference();



        adapter0 =ArrayAdapter.createFromResource(this,R.array.History, android.R.layout.simple_spinner_item);
        adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ChestPain.setAdapter(adapter0);
        ChestPain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectCP = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter1 =ArrayAdapter.createFromResource(this,R.array.History, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Nausea.setAdapter(adapter1);
        Nausea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectN = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter2 =ArrayAdapter.createFromResource(this,R.array.History, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NeckPain.setAdapter(adapter2);
        NeckPain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectNP = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapter3 =ArrayAdapter.createFromResource(this,R.array.History, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Sweating.setAdapter(adapter3);
        Sweating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectS = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapter4 =ArrayAdapter.createFromResource(this,R.array.History, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ChestPain.setAdapter(adapter0);
        ChestPain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectCP = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapter5 =ArrayAdapter.createFromResource(this,R.array.History, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ShortofBreath.setAdapter(adapter5);
        ShortofBreath.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectSOB = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter6 =ArrayAdapter.createFromResource(this,R.array.Clevel, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Cholestrol.setAdapter(adapter6);
        Cholestrol.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectC = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                visit1request visit1reques = new visit1request(selectCP,selectN,selectSOB,selectC,selectS,selectNP);
                visit1vital visit1vita = new visit1vital(BPdystole.getText().toString(),BPsystole.getText().toString(),Temperature.getText().toString(),Heartbeat.getText().toString());

                String file =getIntent().getExtras().getString("name1");
                String user_vs =getIntent().getExtras().getString("usernp");
                Log.i(LOG_TAG,user_vs);
                mDatabase.child("Users/"+user_vs+"/Visit1data/"+file+"/Symptoms").setValue(visit1reques);

                mDatabase.child("Users/"+user_vs+"/Visit1data/"+file+"/VitalSign").setValue(visit1vita);

                Intent int2 = new Intent(Visit1SymVsi.this, recorder.class);

                int2.putExtra("name2", file);
                int2.putExtra("user_vs", user_vs);

                //int2.putExtra("name",PatientName.getText().toString()+PatientContact.getText().toString());


                startActivity(int2);
            }
        });

    }
}
