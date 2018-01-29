package com.mgd.mgd;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.ShareApi;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.mgd.mgd.Common.Player;
import com.mgd.mgd.Components.ScoreSystem;

import java.util.Arrays;
import java.util.List;

/**
 * Created by MarcusTan on 28/1/2018.
 */

public class PostGameScreen extends Activity implements View.OnClickListener{

    private Button btn_skip;
    private Button btn_fbLogin;
    private Button btn_shareScore;

    ScoreSystem ss = (ScoreSystem) Player.Instance.GetComponent("score");
    int score = 0;

    boolean loggedIn = false;
    private CallbackManager callbackManager;
    private LoginManager loginManager;

    ProfilePictureView profilePic;

    List<String> PERMISSIONS = Arrays.asList("publish_actions");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String appId = "893864077461983";
        //init for fb use
        FacebookSdk.setApplicationId(appId);
        FacebookSdk.sdkInitialize(this.getApplicationContext());

        setContentView(R.layout.activity_post_game);

        // get final score
        score = ss.GetScore();

        TextView finalScore;
        finalScore = (TextView) findViewById(R.id.finalscore);
        finalScore.setText(String.valueOf(score));

        btn_skip = (Button) findViewById(R.id.skip_button);
        btn_skip.setOnClickListener(this);
        btn_fbLogin = (LoginButton) findViewById(R.id.fblogin_button);
        btn_fbLogin.setOnClickListener(this);
        btn_shareScore = (Button) findViewById(R.id.sharescore_button);
        btn_shareScore.setOnClickListener(this);

        profilePic = (ProfilePictureView) findViewById(R.id.profile_pic);
        callbackManager = CallbackManager.Factory.create();

        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if (currentAccessToken == null) {
                    //user logged out
                    profilePic.setProfileId("");
                } else {
                    profilePic.setProfileId(Profile.getCurrentProfile().getId());
                }
            }
        };

        accessTokenTracker.startTracking();

        loginManager = LoginManager.getInstance();
        loginManager.logInWithPublishPermissions(this, PERMISSIONS);
        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                profilePic.setProfileId(Profile.getCurrentProfile().getId());
            }

            @Override
            public void onCancel() {
                System.out.println("Login attempt canceled");
            }

            @Override
            public void onError(FacebookException e) {
                System.out.println("Login attempt failed");
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        if(v == btn_skip) {
            intent.setClass(this,MainMenu.class);
            startActivity(intent);
            PostGameScreen.this.finish();
        }

        if(v == btn_shareScore) {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(PostGameScreen.this);
            alertBuilder.setTitle("Share score on facebook")
                    .setMessage("Do you want to share your score of " + String.valueOf(score))
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            shareScore();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            alertBuilder.show();
        }
    }

    // share info on FB
    public void shareScore() {
        Bitmap image = BitmapFactory.decodeResource(getResources(),R.drawable.botulism);
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .setCaption("Thank you for playing Bacteria Slug. Your final score is " + score).build();
        SharePhotoContent content = new SharePhotoContent.Builder().addPhoto(photo).build();
        ShareApi.share(content,null);
    }

    // fb to use callback manager to manage login
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
