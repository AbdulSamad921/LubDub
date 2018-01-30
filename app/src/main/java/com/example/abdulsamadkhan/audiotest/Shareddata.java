package com.example.abdulsamadkhan.audiotest;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Shareddata extends AppCompatActivity {

    private RecyclerView patientdata;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shareddata);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));
        setTitle("Shared Patient Details");




        FloatingActionButton bshared = (FloatingActionButton) findViewById(R.id.myFAB);
        mAuth=FirebaseAuth.getInstance();
        String user_md= mAuth.getCurrentUser().getUid().toString();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Shared/"+user_md+"/Patient");

        patientdata = (RecyclerView) findViewById(R.id.patientdata);

        patientdata.setHasFixedSize(true);
        patientdata.setLayoutManager(new LinearLayoutManager(this));

        bshared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(Shareddata.this, PatientRegister.class);

                startActivity(int2);
            }
        });



    }

    @Override
    public void onStart() {
        super.onStart();
        final FirebaseRecyclerAdapter<PatientRequest1, Shareddata.DataViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PatientRequest1, Shareddata.DataViewHolder>(

                PatientRequest1.class,
                R.layout.sharedtype,
                Shareddata.DataViewHolder.class,
                mDatabase
        ) {

            @Override
            public void populateViewHolder(Shareddata.DataViewHolder viewHolder, PatientRequest1 model, final int position) {

                final String getid = getRef(position).getKey();



                viewHolder.setName(model.getPatientName());
                viewHolder.setDoctorName(model.getDoctorName());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Shareddata.this, getid, Toast.LENGTH_SHORT).show();
                        String user_md= mAuth.getCurrentUser().getUid().toString();
                        Intent int1 = new Intent(Shareddata.this, ShowSharedPatientDetails.class);
                        int1.putExtra("name7", getid);
                        int1.putExtra("user_md", user_md);
                        int1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(int1);
                    }


                });

                viewHolder.mView.setOnDragListener(new View.OnDragListener() {
                    @Override
                    public boolean onDrag(View v, DragEvent event) {
                        return false;
                    }
                });




            }




        };


        patientdata.setAdapter(firebaseRecyclerAdapter);


    }











    public static class DataViewHolder extends RecyclerView.ViewHolder {

        View mView;


        public DataViewHolder(View itemView) {
            super(itemView);

            mView = itemView;


        }

        public void setName(String name) {
            TextView pname = (TextView) mView.findViewById(R.id.pname1);
            pname.setText(name);
        }

        public void setDoctorName(String doctorName) {
            TextView doctor = (TextView) mView.findViewById(R.id.pdoctor);
            doctor.setText(doctorName);
        }


    }


}

