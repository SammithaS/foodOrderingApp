package com.example.foodOrderingApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private SessionManager session;
    private EditText loginEmail,loginPassword;

    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
            return;
        }
        setContentView(R.layout.activity_login);
        session = new SessionManager(getApplicationContext());

        if (session.isLoggedIn()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
            return;
        }


        loginEmail=findViewById(R.id.login_email);
        loginPassword=findViewById(R.id.login_password);
        loginButton=findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {

if(loginEmail.getText().toString().equals("admin@123")&&loginPassword.getText().toString().equals("admin123")){
    Intent intent=new Intent(getApplicationContext(),admin.class);

    startActivity(intent);
}
else{
    Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
}
               }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

}

