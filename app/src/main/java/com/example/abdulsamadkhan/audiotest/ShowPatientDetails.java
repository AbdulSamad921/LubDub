package com.example.abdulsamadkhan.audiotest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
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
import java.util.Map;

import static com.example.abdulsamadkhan.audiotest.R.id.pname;
import static com.example.abdulsamadkhan.audiotest.R.id.user;

public class ShowPatientDetails extends AppCompatActivity implements MediaPlayer.OnPreparedListener {


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
    private Button Audio1,genrate,Exit,Delete,Share;
    private DatabaseReference mDatabase,mDatabase1,mDatabase2,mDatabase0,mDatabaseshared,mDatabasephone,mDatabaseAccount;
    private DatabaseReference mDatabase01,mDatabase02,mDatabase03,mDatabase04;
    private MediaPlayer mediaPlayer;
    private TextView ChestPain,Nausea,ShortofBreath,Cholestrol,Sweating,NeckPain;
    private TextView BPsystole,BPdystole,Pulse,Temperature,Heartbeat;
    private ProgressDialog mProgress;
    private FirebaseAuth mAuth;
    private String DoctorName;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_patient_details);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        setTitle("Detail");
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

        //Return =(Button) findViewById(R.id.breturn);
        Exit = (Button) findViewById(R.id.Exit);
        Delete = (Button) findViewById(R.id.Delete);
        Share = (Button) findViewById(R.id.bShare);
        genrate = (Button) findViewById(R.id.genrate);
        mAuth=FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users/"+user_spd+"/Patients/" + name);
        mDatabase0 = FirebaseDatabase.getInstance().getReference().child("Users/"+user_spd+"/Visit1/" + name);
        mDatabase1 = FirebaseDatabase.getInstance().getReference().child("Users/"+user_spd+"/Visit1data/" + name + "/Symptoms");
        mDatabase2 = FirebaseDatabase.getInstance().getReference().child("Users/"+user_spd+"/Visit1data/" + name + "/VitalSign");


        mDatabase01 = FirebaseDatabase.getInstance().getReference();
        mDatabase02 = FirebaseDatabase.getInstance().getReference();
        mDatabase03 = FirebaseDatabase.getInstance().getReference();


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
        mDatabaseshared = FirebaseDatabase.getInstance().getReference().child("Shared");
        mDatabaseAccount =FirebaseDatabase.getInstance().getReference().child("Account");
        mDatabasephone =FirebaseDatabase.getInstance().getReference().child("Username");
        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mBuilder =new AlertDialog.Builder(ShowPatientDetails.this);
                View mView = getLayoutInflater().inflate(R.layout.contact_dialog,null);
                final EditText phone = (EditText) mView.findViewById(R.id.etphonenum);
                final Button check =(Button) mView.findViewById(R.id.benter);

                check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!phone.getText().toString().isEmpty()){

                            final String user_id=mAuth.getCurrentUser().getUid().toString();
                            final String sharephone =phone.getText().toString();



                            mDatabasephone.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.hasChild(sharephone)){

                                        uid= dataSnapshot.child(sharephone+"/uid").getValue().toString();
                                        String name = getIntent().getExtras().getString("name7");
                                        mDatabaseshared.child(uid+"/"+name);
                                        mDatabaseshared.child(uid+"/"+name+"/currentuid").setValue(user_id);
                                        Intent int1 = new Intent(ShowPatientDetails.this, MedicalData.class);
                                        int1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        Toast.makeText(ShowPatientDetails.this, "Valid Number...", Toast.LENGTH_SHORT).show();

                                        mDatabase1.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {


                                                String chestpain = (String) dataSnapshot.child("chestPain").getValue();
                                                String nausea = (String) dataSnapshot.child("nausea").getValue();
                                                String shortofbreath = (String) dataSnapshot.child("shortofBreath").getValue();
                                                String sweating = (String) dataSnapshot.child("sweating").getValue();
                                                String cholestrol = (String) dataSnapshot.child("cholestrol").getValue();
                                                String neckpain = (String) dataSnapshot.child("neckPain").getValue();

                                                String name = getIntent().getExtras().getString("name7");
                                                visit1request visit1reques = new visit1request(chestpain,nausea,shortofbreath,cholestrol,sweating,neckpain);
                                                mDatabase03.child("Shared/"+uid+"/Visit1data/"+name+"/Symptoms").setValue(visit1reques);


                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });

                                        mDatabaseAccount.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                DoctorName = (String) dataSnapshot.child(user_id+"/name").getValue();
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
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

                                                String name = getIntent().getExtras().getString("name7");
                                                PatientRequest1 patientRequest1 = new PatientRequest1(Name, Blood, Address, City,Age,Gender,Weight,Contact,Tension,diabetic,smoking,Problem,DoctorName,result);
                                                mDatabase01.child("Shared/"+uid+"/Patient/"+name).setValue(patientRequest1);


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


                                                String name = getIntent().getExtras().getString("name7");
                                                visit1vital visit1vita = new visit1vital(dystole,systole,Temp,HBeat);
                                                mDatabase02.child("Shared/"+uid+"/Visit1data/"+name+"/VitalSign").setValue(visit1vita);


                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });

                                            ShowPatientDetails.this.startActivity(int1);


                                    }
                                    else{
                                        Toast.makeText(ShowPatientDetails.this, "Invalid Number..", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog=mBuilder.create();
                dialog.show();
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

                Intent int3 = new Intent(ShowPatientDetails.this, PatientRegister.class);
                ShowPatientDetails.this.startActivity(int3);
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseStorage storage1 = FirebaseStorage.getInstance();
                String id = getIntent().getExtras().getString("name7");

                StorageReference storageRef = storage1.getReference();
                Intent int3 = new Intent(ShowPatientDetails.this, MedicalData.class);
                ShowPatientDetails.this.startActivity(int3);
                mDatabase.removeValue();
                mDatabase0.removeValue();
                mDatabase1.removeValue();
                mDatabase2.removeValue();
                storageRef.child("Audio/storage/emulated/0/" + id + ".mp3").delete();



            }
        });
        genrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int5 = new Intent(ShowPatientDetails.this, report.class);
                String name = getIntent().getExtras().getString("name7");
                String user_spd = getIntent().getExtras().getString("user_md");
                int5.putExtra("name_re", name);
                int5.putExtra("user_re", user_spd);
                ShowPatientDetails.this.startActivity(int5);

            }
        });


    }

    public void fetchAudioUrlFromFirebase() {
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        String name =getIntent().getExtras().getString("name7");


        StorageReference storageRef = storage.getReference();

        storageRef.child("Audio/storage/emulated/0/"+name+".mp3").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                try {
                    // Download url of file
                    final String url = uri.toString();
                    mediaPlayer.setDataSource(url);
                    // wait for media player to get prepare
                    mediaPlayer.setOnPreparedListener(ShowPatientDetails.this);
                    mediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(mediaPlayer.isPlaying()){
                    mProgress.dismiss();
                    Exit.setEnabled(false);
                    Delete.setEnabled(false);
                }
                else
                {
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