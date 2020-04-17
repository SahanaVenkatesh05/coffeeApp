package com.sas.hebbarscoffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import java.util.Objects;

import static android.widget.Toast.LENGTH_SHORT;

public class HomeActivity extends AppCompatActivity {
 TextView coffeeType,cost;
 private ActionBarDrawerToggle actionBarDrawerToggle;
 private long backPressedTime;


 GoogleSignInClient mGoogleSignInClient;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DrawerLayout drawerLayout = findViewById(R.id.dl);

        actionBarDrawerToggle=new ActionBarDrawerToggle(this, drawerLayout,R.string.Open,R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        mGoogleSignInClient= GoogleSignIn.getClient(this,gso);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        NavigationView navView=findViewById(R.id.nav_view);

        //Display User name and profile photo in navigation bar
        TextView userName = navView.getHeaderView(0).findViewById(R.id.userName);
        ImageView userPhoto = navView.getHeaderView(0).findViewById(R.id.photo);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            Uri personPhoto = acct.getPhotoUrl();

            userName.setText(""+personName);


            if(personPhoto!=null)
            Glide.with(this).load(personPhoto).into(userPhoto);
        }
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch(id)
                {
                    case R.id.orders:
                        Toast.makeText(HomeActivity.this, "Orders", LENGTH_SHORT).show();break;
                    case R.id.tc:
                        Toast.makeText(HomeActivity.this, "T and C", LENGTH_SHORT).show();break;

                    case R.id.logoutFrom:
                      signOut();

                }
                return true;
            }
        });

    }

   public void signOut()
   {
       mGoogleSignInClient.signOut()
               .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task) {
                       Toast.makeText(HomeActivity.this,"Signed out Successfully", Toast.LENGTH_SHORT).show();
                       finish();
                   }
               });


   }
    @Override
    public void onBackPressed() {

        if(backPressedTime +2000> System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }
        else
        {
            Toast.makeText(HomeActivity.this, "Press again to exit", LENGTH_SHORT).show();
        }
        backPressedTime=System.currentTimeMillis();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    public void order(View view)
    {
        switch (view.getId())
        {
            case (R.id.button1):
               coffeeType=findViewById(R.id.name1);
                cost=findViewById(R.id.cost1);
                startIntent(coffeeType,cost);
                break;

            case (R.id.button2):
               coffeeType=findViewById(R.id.name2);
                cost=findViewById(R.id.cost2);
                startIntent(coffeeType,cost);
                break;
            case (R.id.button3):
                coffeeType=findViewById(R.id.name3);
                cost=findViewById(R.id.cost3);
                startIntent(coffeeType,cost);
                break;

            case (R.id.button4):
                coffeeType=findViewById(R.id.name4);
                cost=findViewById(R.id.cost4);
                startIntent(coffeeType,cost);
                break;
        }



    }

    public void startIntent(TextView coffee,TextView cost)
    {

        String coffeeType = coffee.getText().toString();
        String costValue = cost.getText().toString();
        Intent intent=new Intent(HomeActivity.this,OrderActivity.class);
        intent.putExtra("coffee",coffeeType);
        intent.putExtra("cost",costValue);
        startActivity(intent);

    }
}
