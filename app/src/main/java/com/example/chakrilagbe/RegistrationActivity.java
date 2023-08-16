package com.example.chakrilagbe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class RegistrationActivity extends AppCompatActivity {
    private EditText emailReg;
    private EditText passReg;

    private Button btnReg;
    private Button btnLogin;

    //Firebase auth

    private FirebaseAuth mAuth;

    //Progress auth

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth=FirebaseAuth.getInstance();
        mDialog=new ProgressDialog(this);
        Registration();
    }
        private void Registration(){
            emailReg=findViewById(R.id.email_registration);
            passReg=findViewById(R.id.registration_password);

            btnReg=findViewById(R.id.btn_login);
            btnLogin=findViewById(R.id.btn_registration);
            btnReg.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){

                    String email=emailReg.getText().toString().trim();
                    String pass=passReg.getText().toString().trim();  //trim diye space nibe na string er moddhe

                    if(TextUtils.isEmpty(email)){
                        emailReg.setError("Required field...");
                        return;
                    }
                    if(TextUtils.isEmpty(pass)){
                        passReg.setError("Required field...");
                        return;
                    }

                    mDialog.setMessage("Processing...");
                    mDialog.show();

                    mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                            }
                            else{
                                Toast.makeText(getApplicationContext(),task.getException().toString(),Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                }

            });
            btnLogin.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }

            });
    }
}