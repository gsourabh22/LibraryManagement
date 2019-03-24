package com.example.megha.librarymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {
    Button button;
    EditText username, password;
    String user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        initialize();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString().trim();
                pass = password.getText().toString().trim();
                if (user.equals("Admin") && pass.equals("Admin")) {
                    Intent intent = new Intent(AdminLoginActivity.this, AdminFirstPage.class);
                    startActivity(intent);
                } else
                    Toast.makeText(AdminLoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initialize() {

        username = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        button = findViewById(R.id.button);


    }
}
