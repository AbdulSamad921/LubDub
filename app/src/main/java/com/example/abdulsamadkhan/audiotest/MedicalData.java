package com.example.abdulsamadkhan.audiotest;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class MedicalData extends AppCompatActivity {

    private RecyclerView patientdata;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_data);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));
        setTitle("Patient Directory");
        mAuth=FirebaseAuth.getInstance();
        String user_md= mAuth.getCurrentUser().getUid().toString();
        FloatingActionButton bmedical = (FloatingActionButton) findViewById(R.id.myFAB);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users/"+user_md+"/Patients");

        patientdata = (RecyclerView) findViewById(R.id.patientdata);

        patientdata.setHasFixedSize(true);
        patientdata.setLayoutManager(new LinearLayoutManager(this));


        bmedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(MedicalData.this, PatientRegister.class);

                startActivity(int2);
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        final FirebaseRecyclerAdapter<PatientRequest, DataViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PatientRequest, DataViewHolder>(

                PatientRequest.class,
                R.layout.datatype,
                DataViewHolder.class,
                mDatabase
        ) {

            @Override
            public void populateViewHolder(DataViewHolder viewHolder, PatientRequest model, final int position) {

                final String getid = getRef(position).getKey();



                viewHolder.setName(model.getPatientName());
                viewHolder.setAge(model.getPatientAge());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MedicalData.this, getid, Toast.LENGTH_SHORT).show();
                        String user_md= mAuth.getCurrentUser().getUid().toString();
                        Intent int1 = new Intent(MedicalData.this, ShowPatientDetails.class);
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







    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

        public void setAge(String age) {
            TextView page = (TextView) mView.findViewById(R.id.page1);
            page.setText(age);
        }


}
}












