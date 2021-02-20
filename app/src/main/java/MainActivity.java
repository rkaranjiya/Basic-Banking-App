package com.example.thesparkbank;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView logo;
    TextView title,credit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo =  findViewById(R.id.logo);
        title =  findViewById(R.id.title);
        credit =  findViewById(R.id.credit);

        Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.logo);
        logo.setAnimation(animation1);

        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.title);
        title.setAnimation(animation2);

        Animation animation3 = AnimationUtils.loadAnimation(this,R.anim.credit);
        credit.setAnimation(animation3);

        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),CustomersActivity.class));
                finish();
            }
        }, secondsDelayed*4000);

    }

}
