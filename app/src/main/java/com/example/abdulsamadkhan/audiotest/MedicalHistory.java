package com.example.abdulsamadkhan.audiotest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MedicalHistory extends Npatient {

    private DatabaseReference mDatabase;
    Spinner HyperTension,Diabetic,Smoking,HeartProblem;
    private String selectht,selectsk,selecthp,selectdb;
    ArrayAdapter adapter1,adapter2,adapter3,adapter4;

    private Button bHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);

        HyperTension = (Spinner) findViewById(R.id.shypertension);
        Smoking = (Spinner) findViewById(R.id.ssmoking);
        Diabetic = (Spinner) findViewById(R.id.sdiabetic);
        HeartProblem = (Spinner) findViewById(R.id.sheartproblem);

        bHistory = (Button) findViewById(R.id.bHistoryRegister);

        final Bundle bundle= getIntent().getExtras();


        adapter1 =ArrayAdapter.createFromResource(this,R.array.History, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HyperTension.setAdapter(adapter1);
        HyperTension.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectht = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter2 =ArrayAdapter.createFromResource(this,R.array.History, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Smoking.setAdapter(adapter2);
        Smoking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectsk = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter3 =ArrayAdapter.createFromResource(this,R.array.History, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Diabetic.setAdapter(adapter3);
        Diabetic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectdb = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter4 =ArrayAdapter.createFromResource(this,R.array.History, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HeartProblem.setAdapter(adapter4);
        HeartProblem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               selecthp = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        mDatabase = FirebaseDatabase.getInstance().getReference();

        bHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent int2 = new Intent(MedicalHistory.this, PatientRegister.class);

                history post = new history(selectht, selectsk, selectdb, selecthp);

                Map<String, Object> postValues = post.toMap();
                Map<String, Object> childUpdates = new HashMap<>();


                childUpdates.put(bundle.getString("name"), postValues);

                mDatabase.updateChildren(childUpdates);

                /*mDatabase.child("Patients").child(bundle.getString("name")).setValue(post);
                firebasechild=mDatabase.child("Patients").toString();
                */

                MedicalHistory.this.startActivity(int2);
                finish();
            }
        });



    }

    /*@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView SpinnerDialogText1 = (TextView) view;
        selectht = SpinnerDialogText1.getText().toString();
        //Toast.makeText(this, "you selected " + SpinnerDialogText1.getText(), Toast.LENGTH_SHORT  ).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }*/


}

