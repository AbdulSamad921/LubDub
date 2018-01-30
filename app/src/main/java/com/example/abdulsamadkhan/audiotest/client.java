package com.example.abdulsamadkhan.audiotest;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class client extends AppCompatActivity {

    TextView response;
    EditText editTextAddress,editTextPort;
    Button buttonConnect, buttonClear,bexit;
    private static final String TAG = "client";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));

        editTextAddress = (EditText) findViewById(R.id.addressEditText);
        editTextPort = (EditText) findViewById(R.id.portEditText);
        buttonConnect = (Button) findViewById(R.id.connectButton);
        buttonClear = (Button) findViewById(R.id.clearButton);
        bexit = (Button) findViewById(R.id.bexit);
        String id_doctor =getIntent().getExtras().getString("user_s");
        String id_patient =getIntent().getExtras().getString("name3");
        String name_url =getIntent().getExtras().getString("user_url");

        final String data = id_doctor+"*"+id_patient+"*"+name_url;

        buttonConnect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Client1 myClient = new Client1(editTextAddress.getText()
                        .toString(), Integer.parseInt(editTextPort
                        .getText().toString()), data);
                Log.v(TAG, "URL=" + data);

                myClient.execute();




            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                editTextAddress.setText("");
                editTextPort.setText("");
            }
        });

        bexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent int2 = new Intent(client.this, PatientRegister.class);
                client.this.startActivity(int2);

            }
        });


    }
}
