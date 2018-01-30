package com.example.abdulsamadkhan.audiotest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

import static com.example.abdulsamadkhan.audiotest.R.id.pname;

public class ShowSharedPatientDetails extends AppCompatActivity implements MediaPlayer.OnPreparedListener {

    private TextView PatientAge;
    public TextView PatientName;
    public TextView PatientGender, PatientBlood;
    private TextView PatientAddress;
    public TextView PatientContact;
    private TextView PatientCity;
    private TextView PatientWeight;
    private TextView HyperTension;
    private TextView Smoking;
    private TextView Diabetic;
    private TextView HeartProblem;
    private Button Audio1,Exit,Delete;
    private DatabaseReference mDatabase,mDatabase1,mDatabase2,mDatabase0,mDatabaseshared,mDatabasephone,mDatabaseAccount;

    private MediaPlayer mediaPlayer;
    private TextView ChestPain,Nausea,ShortofBreath,Cholestrol,Sweating,NeckPain;
    private TextView BPsystole,BPdystole,Result,Temperature,Heartbeat;
    private ProgressDialog mProgress;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_shared_patient_details);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        setTitle("Shared Patient Details");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

        mProgress = new ProgressDialog(this);

        String name = getIntent().getExtras().getString("name7");
        String user_spd = getIntent().getExtras().getString("user_md");


        PatientName = (TextView) findViewById(R.id.pname);
        PatientAge = (TextView) findViewById(R.id.page);
        PatientGender = (TextView) findViewById(R.id.pGender);
        PatientBlood = (TextView) findViewById(R.id.pBlood);
        PatientCity = (TextView) findViewById(R.id.pCity);
        PatientContact = (TextView) findViewById(R.id.pcontact);
        PatientWeight = (TextView) findViewById(R.id.pweight);
        PatientAddress = (TextView) findViewById(R.id.pAddress);
        HyperTension = (TextView) findViewById(R.id.pht);
        HeartProblem = (TextView) findViewById(R.id.php);
        Smoking = (TextView) findViewById(R.id.psmoking);
        Diabetic = (TextView) findViewById(R.id.pdia);
        Audio1 = (Button) findViewById(R.id.baudio1);
        ChestPain = (TextView) findViewById(R.id.pcp);
        Nausea = (TextView) findViewById(R.id.pNau);
        NeckPain = (TextView) findViewById(R.id.pNp);
        Sweating = (TextView) findViewById(R.id.pS);
        ShortofBreath = (TextView) findViewById(R.id.psob);
        Cholestrol = (TextView) findViewById(R.id.pC);
        PatientName = (TextView) findViewById(pname);
        BPdystole = (TextView) findViewById(R.id.pdiastolic);
        BPsystole = (TextView) findViewById(R.id.psystolic);
        Temperature = (TextView) findViewById(R.id.pTemperature);
        Heartbeat = (TextView) findViewById(R.id.pHeartbeat);
        Result = (TextView) findViewById(R.id.pResult);
        //Return =(Button) findViewById(R.id.breturn);
        Exit = (Button) findViewById(R.id.Exit);
        Delete = (Button) findViewById(R.id.Delete);


        mAuth=FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Shared/"+user_spd+"/Patient/" + name);
        //mDatabase0 = FirebaseDatabase.getInstance().getReference().child("Shared/"+user_spd+"/Visit1/" + name);
        mDatabase1 = FirebaseDatabase.getInstance().getReference().child("Shared/"+user_spd+"/Visit1data/" + name + "/Symptoms");
        mDatabase2 = FirebaseDatabase.getInstance().getReference().child("Shared/"+user_spd+"/Visit1data/" + name + "/VitalSign");





        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                String Name = (String) dataSnapshot.child("patientName").getValue();
                String Age = (String) dataSnapshot.child("patientAge").getValue();
                String Contact = (String) dataSnapshot.child("patientContact").getValue();
                String Gender = (String) dataSnapshot.child("patientGender").getValue();
                String Address = (String) dataSnapshot.child("patientAddress").getValue();
                String Blood = (String) dataSnapshot.child("patientBlood").getValue();
                String City = (String) dataSnapshot.child("patientCity").getValue();
                String Weight = (String) dataSnapshot.child("patientWeight").getValue();
                String Tension = (String) dataSnapshot.child("hyperTension").getValue();
                String Problem = (String) dataSnapshot.child("heartProblem").getValue();
                String smoking = (String) dataSnapshot.child("smoking").getValue();
                String diabetic = (String) dataSnapshot.child("diabetic").getValue();
                String result = (String) dataSnapshot.child("result").getValue();

                PatientName.setText(Name);
                PatientAge.setText(Age);
                PatientAddress.setText(Address);
                PatientCity.setText(City);
                PatientContact.setText(Contact);
                PatientWeight.setText(Weight);
                PatientBlood.setText(Blood);
                PatientGender.setText(Gender);
                HyperTension.setText(Tension);
                HeartProblem.setText(Problem);
                Smoking.setText(smoking);
                Diabetic.setText(diabetic);
                Result.setText(result);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mDatabase2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                String dystole = (String) dataSnapshot.child("bpdystole").getValue();
                String systole = (String) dataSnapshot.child("bpsystole").getValue();
                String Temp = (String) dataSnapshot.child("temperature").getValue();
                String HBeat = (String) dataSnapshot.child("heartbeat").getValue();



                BPdystole.setText(dystole);
                BPsystole.setText(systole);
                Temperature.setText(Temp);
                Heartbeat.setText(HBeat);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                String chestpain = (String) dataSnapshot.child("chestPain").getValue();
                String nausea = (String) dataSnapshot.child("nausea").getValue();
                String shortofbreath = (String) dataSnapshot.child("shortofBreath").getValue();
                String sweating = (String) dataSnapshot.child("sweating").getValue();
                String cholestrol = (String) dataSnapshot.child("cholestrol").getValue();
                String neckpain = (String) dataSnapshot.child("neckPain").getValue();

                ChestPain.setText(chestpain);
                Nausea.setText(nausea);
                ShortofBreath.setText(shortofbreath);
                Sweating.setText(sweating);
                Cholestrol.setText(cholestrol);
                NeckPain.setText(neckpain);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Audio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) throws IllegalArgumentException, SecurityException, IllegalStateException {

                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                fetchAudioUrlFromFirebase();
            }
        });
/*        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) throws IllegalArgumentException, SecurityException, IllegalStateException {

                Intent int3 = new Intent(ShowPatientDetails.this, MedicalData.class);
                ShowPatientDetails.this.startActivity(int3);
            }
        });
*/
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) throws IllegalArgumentException, SecurityException, IllegalStateException {

                Intent int3 = new Intent(ShowSharedPatientDetails.this, PatientRegister.class);
                ShowSharedPatientDetails.this.startActivity(int3);
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent int3 = new Intent(ShowSharedPatientDetails.this, Shareddata.class);

                mDatabase.removeValue();

                mDatabase1.removeValue();
                mDatabase2.removeValue();
                ShowSharedPatientDetails.this.startActivity(int3);



            }
        });

    }

    public void fetchAudioUrlFromFirebase() {
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        String name = getIntent().getExtras().getString("name7");


        StorageReference storageRef = storage.getReference();

        storageRef.child("Audio/storage/emulated/0/" + name + ".mp3").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                try {
                    // Download url of file
                    final String url = uri.toString();
                    mediaPlayer.setDataSource(url);
                    // wait for media player to get prepare
                    mediaPlayer.setOnPreparedListener(ShowSharedPatientDetails.this);
                    mediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (mediaPlayer.isPlaying()) {
                    mProgress.dismiss();
                    Exit.setEnabled(false);
                    Delete.setEnabled(false);
                } else {
                    mProgress.setMessage("Getting Audio.....");
                    mProgress.show();
                    Exit.setEnabled(true);
                    Delete.setEnabled(true);
                }

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("TAG", e.getMessage());
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

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }


}
