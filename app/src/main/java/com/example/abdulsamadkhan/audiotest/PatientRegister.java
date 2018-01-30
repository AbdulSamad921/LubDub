package com.example.abdulsamadkhan.audiotest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientRegister extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ImageButton butt1;
    private ImageButton butt2;
    private ImageButton butt3;

    //private DatabaseReference mDatabaseUsers;
    private DatabaseReference mDatabaseAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);

        //mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users");
        //mDatabaseUsers.keepSynced(true);
        mDatabaseAccount = FirebaseDatabase.getInstance().getReference().child("Account");
        mDatabaseAccount.keepSynced(true);
        mAuth= FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser()==null){
                    Intent loginIntent =new Intent(PatientRegister.this,Login.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(loginIntent);
                }
            }
        };

        butt1=(ImageButton) findViewById(R.id.bnpatient);
        butt2=(ImageButton) findViewById(R.id.bopatient);
        butt3=(ImageButton) findViewById(R.id.bshared);
        setTitle("Home");




    }

    @Override
    protected void onStart(){
        super.onStart();


        mAuth.addAuthStateListener(mAuthListener);
        checkUserExist();
        butt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(PatientRegister.this, Npatient.class);
                final String user_id = mAuth.getCurrentUser().getUid();
                int1.putExtra("user_id", user_id);
                PatientRegister.this.startActivity(int1);
            }
        });
        butt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(PatientRegister.this, MedicalData.class);

                PatientRegister.this.startActivity(int2);
            }
        });
        butt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int3 = new Intent(PatientRegister.this, Shareddata.class);

                PatientRegister.this.startActivity(int3);
            }
        });

    };

    public  boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){


            logout();


        return super.onOptionsItemSelected(item);
    }

    private void checkUserExist() {
        final String user_id = mAuth.getCurrentUser().getUid();
        mDatabaseAccount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(!dataSnapshot.hasChild(user_id)){


                    Intent patientregisterIntent =new Intent(PatientRegister.this,Login.class);
                    patientregisterIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(patientregisterIntent);


                }else{


                    //Toast.makeText(PatientRegister.this,"You need to setup your account",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    private void logout() {

        mAuth.signOut();
    }

}
