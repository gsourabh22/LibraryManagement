package com.example.megha.librarymanagement;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText edtusername, edtpassword, edtname, edtidentity, edtphone;
    Button regButton;
    FirebaseAuth auth;
    ProgressBar progressBar;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initialize();
        progressBar.setVisibility(View.GONE);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionPerform();
            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();

        if (auth.getCurrentUser() != null) {
            //handle the already login user
        }
    }

    private void actionPerform() {

        final String username = edtusername.getText().toString().trim();
        final String upass = edtpassword.getText().toString().trim();
        final String uname = edtname.getText().toString().trim();
        final String uidentity = edtidentity.getText().toString().trim();
        final String uphone = edtphone.getText().toString().trim();
        textview.setText("Registering . . . PLease Wait !");
        progressBar.setVisibility(View.VISIBLE);


        auth.createUserWithEmailAndPassword(username, upass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    textview.setText(" ");

                   Toast.makeText(RegisterActivity.this,"Registered !",Toast.LENGTH_LONG).show();

                    UserRegister user = new UserRegister(uname, username, uphone, uidentity);
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(auth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Registeration Success", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                                finish();
                            } else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(RegisterActivity.this, "Error in realtime database", Toast.LENGTH_LONG).show();

                            }

                        }
                    });
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(RegisterActivity.this, "Error ! Try again", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void initialize() {

        regButton = findViewById(R.id.register_button_id);
        edtusername = findViewById(R.id.username_id);
        edtpassword = findViewById(R.id.password_id);
        edtname = findViewById(R.id.name_id);
        edtidentity = findViewById(R.id.identity_id);
        edtphone = findViewById(R.id.phone_id);
        auth = FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar2);
        textview=findViewById(R.id.textView2);

    }


}
