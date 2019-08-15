package com.example.firebase_practice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase_practice.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private FirebaseAuth mAuth;

    private static final String TAG = "Google SignIn Firebase";
    private static final int RC_GOOGLE_SIGN_IN = 1000;

    //private GoogleApiClient mGoogleApiClient;

    private GoogleSignInClient mGoogleSignInClient;

    //private FirebaseUser user;
    //private CollectionReference userColRef = FirebaseFirestore.getInstance().collection("user");

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            //goMainActivity();
        }

        //Google Login
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        /*
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this , this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
         */

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        dialog = new ProgressDialog(LoginActivity.this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);

        findViewById(R.id.btnGoogle).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                dialog.show();
                googleSignIn();
            }
        });
    }
    @Override
    public void onBackPressed(){
        finish();
    }

    //Google Login
    private void googleSignIn(){
        Log.d("LoginAct", "googleSignIn s ");
        // Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d("LoginAct", "onActivityResult s " + requestCode);

        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        /*
        if (requestCode == RC_GOOGLE_SIGN_IN) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            Log.d("LoginAct", "onActivityResult 0 "+result.isSuccess());

            if (result.isSuccess()) {
                Log.d("LoginAct", "onActivityResult 1 "+result.getSignInAccount());
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
                Log.d("LoginAct", "onActivityResult 2 "+result.getStatus());
                Toast.makeText(LoginActivity.this, "Google Sign In failed.",
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }
        */
        //Result returned from launching the Intent from GoogleSigninApi.getSignIntent(...);
        if (requestCode == RC_GOOGLE_SIGN_IN){
            Log.d("LoginAct", "onActivityResult 0");
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                Log.d("LoginAct", "onActivityResult 1");
                //Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            }catch (ApiException e){
                Log.w(TAG, "Google sign in failed", e);
                dialog.dismiss();
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        dialog.dismiss();
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d("LoginAct", "firebaseAuthWithGoogle s ");

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("LoginAct", "firebaseAuthWithGoogle 1 " + task.isSuccessful());

                if (task.isSuccessful()){
                    Log.d("LoginAct", "firebaseAuthWithGoogle 1 ");
                    loadUserInfo();
                }else{
                    Log.w(TAG, "signInWithCredential:failure", task.getException());
                    Toast.makeText(LoginActivity.this, "Authentication failure.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void loadUserInfo(){
        Log.d("LoginAct", "loadUserInfo s");
        dialog.dismiss();
        goMainActivity();
    }
    private void goMainActivity(){
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}

// 출처 : studysemina