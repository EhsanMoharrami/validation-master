package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class AuthActivity extends AppCompatActivity {

       private EditText mPhoneText;
       private EditText mCodeText;
       private Button mSendBtn;

       private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mPhoneText = (EditText) findViewById(R.id.phoneEditText);
    mCodeText = (EditText) findViewById(R.id.codeEditText);
    mSendBtn = (Button) findViewById(R.id.sendBtn);

mSendBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        mPhoneText.setEnabled(false); //so the user can't change the number
        mSendBtn.setEnabled(false); //so the user can't push the send a couple of times
        String phoneNumber = mPhoneText.getText().toString();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60, //user has 60 seconds
                TimeUnit.SECONDS,
                AuthActivity.this,
                mCallbacks
        );

    }
});

mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
    @Override
    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

    }

    @Override
    public void onVerificationFailed(FirebaseException e) {

    }
};

    }
}
