package com.example.ecommerce_project1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register_page extends AppCompatActivity {
    TextView linkSignin;
    EditText eusername, eemail, epassword;
    String username, email, password;
    Button Register;
    private DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnSignup), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbHandler = new DBHandler(register_page.this);
        eusername = findViewById(R.id.eUsernameLogin);
        eemail = findViewById(R.id.eEmailRegister);
        epassword = findViewById(R.id.ePasswordLogin);
        username = "default";
        email = "default";
        password = "default";
        Register = findViewById(R.id.btnSignup);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (eusername.getText().toString().isEmpty()) {
                    Toast.makeText(register_page.this, "Enter username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (eemail.getText().toString().isEmpty()) {
                    Toast.makeText(register_page.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (epassword.getText().toString().length() < 6) {
                    Toast.makeText(register_page.this, "Password length too short", Toast.LENGTH_SHORT).show();
                    return;
                }

                username    = eusername.getText().toString();
                email = eemail.getText().toString();
                password = epassword.getText().toString();
                dbHandler.addNewUser(username, email, password);
                Toast.makeText(register_page.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                eusername.setText("");
                eemail.setText("");
                epassword.setText("");
            }
        });


        linkSignin = (TextView) findViewById(R.id.txtSignin);
        linkSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(
                        register_page.this, MainActivity.class
                );
                startActivity(intent1);
            }
        });
    }
}