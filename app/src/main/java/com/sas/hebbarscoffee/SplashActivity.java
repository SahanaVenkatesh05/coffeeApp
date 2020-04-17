package com.sas.hebbarscoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class SplashActivity extends AppCompatActivity {

    //variables
    Animation topAnim,bottomAnim;
    ImageView image;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide the status or notification bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        //hide the action bar on the spash screen
        getSupportActionBar().hide();

        //Animation
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        image=findViewById(R.id.coffee);
        text=findViewById(R.id.appName);

        image.setAnimation(topAnim);
        text.setAnimation(bottomAnim);

      //to run the activity for 5 seconds
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
                //the finish method destroys the object once it completes.. else the splash screen occurs on press of backbutton from home activty
                finish();
            }
        },5000);
    }
}
