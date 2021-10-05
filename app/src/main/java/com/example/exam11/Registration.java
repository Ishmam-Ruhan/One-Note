package com.example.exam11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    protected int num1;
    protected int num2;
    protected TextView captchaIn, google;
    protected TextInputLayout name, email, password, confirmPassword, capchaOutput;
    protected Button procced;
    String nametxt, emailtxt, passwordtxt, cpasswordtxt, captchatxt;
    EditText a;
    private FirebaseAuth mAuth;
    private BlurView blurView;
    private TextView pleaseWait;
    private ProgressBar progressBar;
    private FirebaseDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);
        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        randCaptcha();

        name = findViewById(R.id.namefield);
        email = findViewById(R.id.emailfield);
        password = findViewById(R.id.passwordfield);
        confirmPassword = findViewById(R.id.confirmpasswordfield);
        capchaOutput = findViewById(R.id.captchaoutput);
        progressBar = findViewById(R.id.progressBar);
        pleaseWait=findViewById(R.id.pleaseWait);
        blurView=findViewById(R.id.blurView);


        google = findViewById(R.id.googlebutton);
        procced = findViewById(R.id.proceed);

        google.setOnClickListener(this);
        procced.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.proceed:
                boolean check = checkParameters();
                if (check) {
                    progressBar.setVisibility(View.VISIBLE);
                    pleaseWait.setVisibility(View.VISIBLE);
                    blurTheWindow();
                    mAuth.createUserWithEmailAndPassword(emailtxt,passwordtxt)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){


                                        mAuth.signInWithEmailAndPassword(emailtxt,passwordtxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if(task.isSuccessful()){

                                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                                    String name = user.getUid();

                                                    UserInfo userInfo=new UserInfo(nametxt,emailtxt,passwordtxt);


                                                    DatabaseReference myRef = database.getReference("Users");
                                                    myRef.child(name).setValue(userInfo);
                                                    finish();
                                                    startActivity(new Intent(Registration.this,MasterActivity.class));
                                                }
                                            }
                                        });




                                    }else{
                                        progressBar.setVisibility(View.GONE);
                                        pleaseWait.setVisibility(View.GONE);
                                        blurView.setBlurEnabled(false);
                                        //Toast.makeText(Registration.this, "Not Successful", Toast.LENGTH_SHORT).show();
                                        AlertDialogService alertDialogService=new AlertDialogService(Registration.this,"Registration Failed!","Account already exists. Use different email. \n Don't forget to check your connection",R.drawable.ic_clear_black);
                                        alertDialogService.showAlert();
                                    }
                                }
                            });
                } else {
                    progressBar.setVisibility(View.GONE);
                    pleaseWait.setVisibility(View.GONE);
                    blurView.setBlurEnabled(false);
                    randCaptcha();
                }
                break;


            case R.id.googlebutton:

                break;

        }

    }

    public void randCaptcha() {
        Random random = new Random();
        num1 = random.nextInt(15) + 1;
        num2 = random.nextInt(15) + 1;
        String number1 = String.valueOf(num1);
        String number2 = String.valueOf(num2);
        String number = number1 + " " + "+" + " " + number2;
        captchaIn = findViewById(R.id.captchainput);
        captchaIn.setText(number);
    }

    public boolean checkParameters() {
        nametxt = name.getEditText().getText().toString().trim();
        emailtxt = email.getEditText().getText().toString().trim();
        passwordtxt = password.getEditText().getText().toString().trim();
        cpasswordtxt = confirmPassword.getEditText().getText().toString().trim();
        captchatxt = capchaOutput.getEditText().getText().toString().trim();

        if (nametxt.isEmpty()) {
            name.setError("");
            name.setErrorEnabled(true);
            name.requestFocus();
            Toast toast= Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,950);
            toast.show();
            return false;
        } else {
            name.setError(null);
            name.setErrorEnabled(false);
        }

        if (emailtxt.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailtxt).matches()) {
            email.setError("");
            email.setErrorEnabled(true);
            email.requestFocus();
            Toast toast=Toast.makeText(this, "Provide correct Email", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,950);
            toast.show();
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
        }

        if (passwordtxt.isEmpty() || passwordtxt.length() < 6) {
            password.setError("");
            password.setErrorEnabled(true);
            password.requestFocus();
            Toast toast=Toast.makeText(this, "Password length must be 6 (Six)", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,950);
            toast.show();
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
        }

        if (!cpasswordtxt.equals(passwordtxt)) {
            confirmPassword.setError("");
            confirmPassword.setErrorEnabled(true);
            confirmPassword.requestFocus();
            Toast toast=Toast.makeText(this, "Check password", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,950);
            toast.show();
            return false;
        } else {
            confirmPassword.setError(null);
            confirmPassword.setErrorEnabled(false);
        }

        if (!captchatxt.equals(String.valueOf((num1 + num2)))) {
            capchaOutput.setError("");
            capchaOutput.setErrorEnabled(true);
            capchaOutput.requestFocus();
            Toast toast=Toast.makeText(this, "Captcha Incorrect", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,950);
            toast.show();
            return false;
        } else {
            capchaOutput.setError(null);
            capchaOutput.setErrorEnabled(false);
        }

        return true;
    }

    private void blurTheWindow() {
        float radius = 10f;

        View decorView = getWindow().getDecorView();

        ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);

        Drawable windowBackground = decorView.getBackground();

        blurView.setupWith(rootView)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new RenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);
    }
}
