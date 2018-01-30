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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Npatient extends AppCompatActivity  {



    private EditText PatientAge ;
    public EditText PatientName ;
    Spinner PatientGender,PatientBlood;
    private EditText PatientAddress ;
    public EditText PatientContact;
    private EditText PatientCity ;
    private EditText PatientWeight;
    private String selectgender,selectbd;
    ArrayAdapter adapter,adapter1,adapter2,adapter3,adapter4,adapter5;
    private DatabaseReference mDatabase;
    Spinner HyperTension,Diabetic,Smoking,HeartProblem;
    private String selectht,selectsk,selecthp,selectdb;

    private static final String LOG_TAG ="hello";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npatient);
        setTitle("New Patient");
        PatientGender = (Spinner) findViewById(R.id.sGender);
        PatientBlood = (Spinner) findViewById(R.id.sblood);
        PatientAge = (EditText) findViewById(R.id.etDoctorEmail);
        PatientName = (EditText) findViewById(R.id.etDoctorName);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));

        adapter =ArrayAdapter.createFromResource(this,R.array.Gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PatientGender.setAdapter(adapter);
        PatientGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectgender=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter1 =ArrayAdapter.createFromResource(this,R.array.BloodGroup, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PatientBlood.setAdapter(adapter1);
        PatientBlood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectbd = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        PatientAddress = (EditText) findViewById(R.id.etPatientaddress);
        PatientContact = (EditText) findViewById(R.id.etPatientcontact);
        PatientCity = (EditText) findViewById(R.id.etPatientCity);

        PatientWeight = (EditText) findViewById(R.id.etPatientWeight);
        FloatingActionButton bPatientRegister = (FloatingActionButton) findViewById(R.id.myFAB);
        //bHistory = (Button)findViewById(R.id.bHistory);

        HyperTension = (Spinner) findViewById(R.id.shypertension);
        Smoking = (Spinner) findViewById(R.id.ssmoking);
        Diabetic = (Spinner) findViewById(R.id.sdiabetic);
        HeartProblem = (Spinner) findViewById(R.id.sheartproblem);






        adapter2 =ArrayAdapter.createFromResource(this,R.array.History, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HyperTension.setAdapter(adapter2);
        HyperTension.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectht = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter3 =ArrayAdapter.createFromResource(this,R.array.History, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Smoking.setAdapter(adapter3);
        Smoking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectsk = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter4 =ArrayAdapter.createFromResource(this,R.array.History, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Diabetic.setAdapter(adapter4);
        Diabetic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectdb = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter5 =ArrayAdapter.createFromResource(this,R.array.History, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HeartProblem.setAdapter(adapter5);
        HeartProblem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selecthp = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final String test =getIntent().getExtras().getString("user_id");
        Log.i(LOG_TAG,test);



        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users/"+test);

        bPatientRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                PatientRequest patientRequest = new PatientRequest(PatientName.getText().toString(), selectbd, PatientAddress.getText().toString(), PatientCity.getText().toString(),PatientAge.getText().toString(),
                        selectgender, PatientWeight.getText().toString(), PatientContact.getText().toString(),selectht,selectdb,selectsk,selecthp);


                String getid =mDatabase.child("Patients").push().getKey();

                mDatabase.child("Patients/"+getid).setValue(patientRequest);

                Intent int2 = new Intent(Npatient.this, Visit1SymVsi.class);
                int2.putExtra("name1", getid);
                int2.putExtra("usernp", test);
                //int2.putExtra("name",PatientName.getText().toString()+PatientContact.getText().toString());


                startActivity(int2);

            }
        });


    }


}

