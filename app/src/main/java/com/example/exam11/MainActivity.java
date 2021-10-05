package com.example.exam11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;


public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 101;
    private NotificationManagerCompat managerCompat;
    private FirebaseAuth mAuth;
    private TextInputLayout user, pass;
    private ProgressBar progressBar;
    private BlurView blurView;
    private TextView pleaseWait;
    private GoogleSignInClient mGoogleSignInClient;
    private Button googleSignin;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            finish();
            startActivity(new Intent(MainActivity.this, MasterActivity.class));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        managerCompat = NotificationManagerCompat.from(this);
        mAuth = FirebaseAuth.getInstance();

        user = findViewById(R.id.emailfield);
        pass = findViewById(R.id.passwordfield);
        blurView = findViewById(R.id.blurView);
        progressBar = findViewById(R.id.progressBar);
        pleaseWait = findViewById(R.id.pleaseWait);
        googleSignin = findViewById(R.id.signingooglebutton);


        //google codes
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        //end google codws

        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Registration.class));
            }
        });

        findViewById(R.id.signinbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInEmailPass();

            }
        });

        googleSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }

    public void signInEmailPass() {

        String name = user.getEditText().getText().toString().trim();
        String key = pass.getEditText().getText().toString().trim();

        if (name.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(name).matches()) {
            user.setError("");
            user.setErrorEnabled(true);
            user.requestFocus();
            Toast.makeText(this, "Provide Correct Email", Toast.LENGTH_SHORT).show();

            return;
        } else {
            user.setErrorEnabled(false);
        }

        if (key.isEmpty()) {
            pass.setError("");
            pass.setErrorEnabled(true);
            pass.requestFocus();
            Toast.makeText(this, "Provide Correct Password", Toast.LENGTH_SHORT).show();

            return;
        } else {
            pass.setErrorEnabled(false);
        }

        blurTheWindow();
        progressBar.setVisibility(View.VISIBLE);
        pleaseWait.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(name, key)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(MainActivity.this, MasterActivity.class));
                        } else {
                            AlertDialogService alertDialogService = new AlertDialogService(MainActivity.this, "Sign In Error", "Email or Password incorrect. \n\n ** Don't forget to check your connection**", R.drawable.ic_clear_black);
                            alertDialogService.showAlert();

                            blurView.setBlurEnabled(false);
                            progressBar.setVisibility(View.GONE);
                            pleaseWait.setVisibility(View.GONE);

                        }
                    }
                });

    }


    //Google sign in codes

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        blurTheWindow();
        progressBar.setVisibility(View.VISIBLE);
        pleaseWait.setVisibility(View.VISIBLE);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately

                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            finish();
                            startActivity(new Intent(MainActivity.this, MasterActivity.class));


                        } else {
                            // If sign in fails, display a message to the user.
                            blurView.setBlurEnabled(false);
                            progressBar.setVisibility(View.GONE);
                            pleaseWait.setVisibility(View.GONE);
                            AlertDialogService alertDialogService = new AlertDialogService(MainActivity.this, "Sign In Error", "Email or Password incorrect. \n\n ** Don't forget to check your connection**", R.drawable.ic_clear_black);
                            alertDialogService.showAlert();
                        }

                        // ...
                    }
                });
    }

    //google code end

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
