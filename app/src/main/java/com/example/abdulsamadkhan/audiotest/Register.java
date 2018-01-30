package com.example.abdulsamadkhan.audiotest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Register extends AppCompatActivity {

    private EditText etEmail;
    private EditText etName;
    private EditText etContact;
    private EditText etPassword,etconfirmPassword;
    private Button bRegister;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private ProgressDialog mProgress;
    private DatabaseReference mDatabase1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mDatabase=FirebaseDatabase.getInstance().getReference().child("Account");
        mDatabase1=FirebaseDatabase.getInstance().getReference().child("Username");
        mAuth=FirebaseAuth.getInstance();
        mProgress = new ProgressDialog(this);

        etEmail = (EditText) findViewById(R.id.etDoctorEmail);
        etName = (EditText) findViewById(R.id.etDoctorName);
        etContact = (EditText) findViewById(R.id.etcontactphone);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etconfirmPassword = (EditText) findViewById(R.id.etconfirmPassword);
        bRegister = (Button) findViewById(R.id.bRegister);


        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegister();
            }
        });
    }

    private void startRegister() {
        final String name =etName.getText().toString().trim();
        final String email =etEmail.getText().toString().trim();
        final String contact =etContact.getText().toString().trim();
        String password =etPassword.getText().toString().trim();
        String confirmpassword =etconfirmPassword.getText().toString().trim();

        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(contact)&&!TextUtils.isEmpty(password)&&!TextUtils.isEmpty(confirmpassword)){

            if(Objects.equals(password, confirmpassword)) {
                mProgress.setMessage("Signing Up...");
                mProgress.show();
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            String user_id = mAuth.getCurrentUser().getUid();

                            DatabaseReference current_user_db = mDatabase.child(user_id);
                            DatabaseReference current_user_name = mDatabase1;
                            current_user_name.child(contact + "/uid").setValue(user_id);
                            current_user_db.child("name").setValue(name);
                            current_user_db.child("email").setValue(email);
                            current_user_db.child("phone").setValue(contact);

                            mProgress.dismiss();

                            Intent mainIntent = new Intent(Register.this, Login.class);
                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(mainIntent);


                        }

                    }
                });
            }
            else
            {

                Toast.makeText(Register.this, "Password doesn't match", Toast.LENGTH_LONG).show();
                etPassword.setText("");
                etconfirmPassword.setText("");

            }

        }
    }
}
