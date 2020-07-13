package com.AmmarKchatApp.flashchatnewfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    // TODO: Add member variables here:
    private FirebaseAuth mAuth;
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.login_email);
        mPasswordView = (EditText) findViewById(R.id.login_password);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                    attemptLogin();
                    return true;

            }
        });

        // TODO: Grab an instance of FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

    }

    // Executed when Sign in button pressed
    public void signInExistingUser(View v) {
        // TODO: Call attemptLogin() here
        attemptLogin();
    }

    // Executed when Register button pressed
    public void registerNewUser(View v) {
        Intent intent = new Intent(this, com.AmmarKchatApp.flashchatnewfirebase.RegisterActivity.class);
        finish();
        startActivity(intent);
    }

    // TODO: Complete the attemptLogin() method
    private void attemptLogin() {
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        if (email.length() < 1 || password.length() < 1) {
            return;
        } else {
            Toast.makeText(this, "Login in Progress...", Toast.LENGTH_SHORT).show();

        }

        // TODO: Use FirebaseAuth to sign in with email & password
          mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("whatsapplite", "sign in : " + task.isSuccessful());

                if (!task.isSuccessful()) {
                    Log.d("whatsapplite", "sign in unsuccessful. " + task.getException());
                    showErrorMessage("Unable to log in...");
                } else {
                    Intent intent = new Intent(LoginActivity.this, MainChatActivity.class);
                    finish();
                    startActivity(intent);
                }

            }
        });


    }

    // TODO: Show error on screen with an alert dialog
    private void showErrorMessage(String msg) {
        new AlertDialog.Builder(this)
                .setTitle("oops")
                .setPositiveButton(android.R.string.ok, null)
                .setMessage(msg)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }


}