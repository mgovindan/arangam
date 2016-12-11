package com.example.lenovo.available;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import java.util.concurrent.Callable;

import io.fabric.sdk.android.Fabric;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
    private CallbackManager mFacebookCallbackManager;

    private LoginButton btnFacebookSignIn;
    private TwitterLoginButton btnTwitterSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        mFacebookCallbackManager = CallbackManager.Factory.create();

        // The private key that follows should never be public
        // (consider this when deploying the application)
        TwitterAuthConfig authConfig = new TwitterAuthConfig(getString(R.string.fabric_app_id).toString(),
                getString(R.string.fabric_secrete_key).toString());
        Fabric.with(this, new TwitterCore(authConfig));
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        btnFacebookSignIn = (LoginButton) findViewById(R.id.btnFacebookSignIn);
        btnTwitterSignIn = (TwitterLoginButton) findViewById(R.id.btnTwitterSignIn);

        setFacebookAuthentication(btnFacebookSignIn);
        setTwitterAuthentication(btnTwitterSignIn);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

        } else if (TwitterAuthConfig.DEFAULT_AUTH_REQUEST_CODE == requestCode) {
            btnTwitterSignIn.onActivityResult(requestCode, resultCode, data);
        } else {
            mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
        }

    }

    private void setFacebookAuthentication(LoginButton button) {

        button.registerCallback(mFacebookCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleSignInResult(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        LoginManager.getInstance().logOut();
                        return null;
                    }
                });
            }

            @Override
            public void onCancel() {
                handleSignInResult(null);
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(LoginActivity.class.getCanonicalName(), error.getMessage());
                handleSignInResult(null);
            }
        });
    }

    private void setTwitterAuthentication(TwitterLoginButton button) {

        button.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                handleSignInResult(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        TwitterCore.getInstance().logOut();
                        return null;
                    }
                });
            }

            @Override
            public void failure(TwitterException e) {
                Log.d(LoginActivity.class.getCanonicalName(), e.getMessage());
                handleSignInResult(null);
            }
        });
    }

    private void handleSignInResult(Callable<Void> logout) {

        if (logout == null) {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
        } else {
            try {
                logout.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            startActivity(getParentActivityIntent());
        }

    }
}
