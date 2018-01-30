package com.example.abdulsamadkhan.audiotest;



import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import static android.R.id.message;


public class recorder extends AppCompatActivity {

    private ImageButton play,record,stop,pause;
    private MediaRecorder audioRecord;
    private String outputFile=null;

    public String outputFile1=null;
    private StorageReference mStorage;
    private ProgressDialog mProgress;
    private DatabaseReference mDatabase;
    MediaPlayer mediaPlayer = new MediaPlayer();
    private String Url12;
    private String link;

    private static final String TAG = "recorder";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));
        setTitle("Audio Input");
        String user_r =getIntent().getExtras().getString("user_vs");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users/"+user_r);


        String name2 =getIntent().getExtras().getString("name2");

        mStorage = FirebaseStorage.getInstance().getReference();

        play = (ImageButton) findViewById(R.id.play);
        record = (ImageButton) findViewById(R.id.record);
        stop = (ImageButton) findViewById(R.id.stop);
        pause = (ImageButton) findViewById(R.id.bpause);

        mProgress = new ProgressDialog(this);

        play.setEnabled(false);
        stop.setEnabled(false);
        pause.setEnabled(false);

        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + name2 + ".mp3";


        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    record.setEnabled(true);
                    play.setEnabled(false);
                    audioRecord = new MediaRecorder();
                    audioRecord.setAudioSource(MediaRecorder.AudioSource.MIC);
                    audioRecord.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                    audioRecord.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                    audioRecord.setOutputFile(outputFile);


                    try {

                        audioRecord.prepare();
                        audioRecord.start();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    record.setEnabled(false);
                    stop.setEnabled(true);

                    Toast.makeText(recorder.this, "Recording Starting...", Toast.LENGTH_SHORT).show();

            }
        });



        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                audioRecord.stop();
                audioRecord.release();
                audioRecord = null;


                Toast.makeText(recorder.this, "Recording Stopped...", Toast.LENGTH_SHORT).show();
                play.setEnabled(true);
                record.setEnabled(true);
                pause.setEnabled(false);



            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {

                final MediaPlayer mediaPlayer = new MediaPlayer();
                play.setEnabled(false);
                pause.setEnabled(true);
                stop.setEnabled(false);
                record.setEnabled(false);
                try {
                    mediaPlayer.setDataSource(outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
                    @Override
                    public void onPrepared(MediaPlayer mp) {

                        mp.start();
                        pause.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                if(mediaPlayer.isPlaying())
                                {
                                    mediaPlayer.pause();
                                }
                                else
                                    mediaPlayer.start();

                            }
                        });

                        record.setEnabled(true);

                    }

                });


                mediaPlayer.prepareAsync();
                play.setEnabled(true);


            }
        });

        FloatingActionButton exit = (FloatingActionButton) findViewById(R.id.exit);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                mProgress.setMessage("Uploading Audio.....");
                mProgress.show();
                final String name2 =getIntent().getExtras().getString("name2");
                outputFile1= name2 + ".mp3";
                StorageReference filepath = mStorage.child("Audio/storage/emulated/0/").child(outputFile1);

                Uri uri = Uri.fromFile(new File(outputFile));
                filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Uri downloadUrl = taskSnapshot.getDownloadUrl();

                        final Intent int2 = new Intent(recorder.this, client.class);

                        String name2 =getIntent().getExtras().getString("name2");
                        Url12=downloadUrl.toString();




                        mDatabase.child("Visit1/"+name2+"/link").setValue(Url12);
                        mDatabase.child("Visit1/"+name2+"/refpatient").setValue(name2);




                        mProgress.dismiss();
                        String user_s =getIntent().getExtras().getString("user_vs");
                        String name3 =getIntent().getExtras().getString("name2");



                        int2.putExtra("name3", name3);
                        int2.putExtra("user_s", user_s);
                        int2.putExtra("user_url", Url12);

                        int2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        recorder.this.startActivity(int2);


                    }
                });

            }
        });


    }








}
