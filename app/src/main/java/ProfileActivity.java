package com.example.thesparkbank;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    ImageView profile;
    Button btn_transfer;
    TextView name,email,balance,ac,ph,ifsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile =  findViewById(R.id.profile_dp);
        btn_transfer =  findViewById(R.id.pr_trans_btn);
        name =  findViewById(R.id.profile_customer);
        email = findViewById(R.id.pr_email);
        balance =  findViewById(R.id.pr_bal);
        ifsc =  findViewById(R.id.pr_IFSC);
        ac =  findViewById(R.id.pr_acno);
        ph =  findViewById(R.id.ph);

        final String pr_email = getIntent().getStringExtra("pr_email");

        getProfile(pr_email);

        btn_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),TransferActivity.class);
                i.putExtra("pr_email",pr_email);
                startActivity(i);

            }
        });

    }

    private void getProfile(String pr_email) {

        Cursor cursor = new DbHelper(this).getProfile(pr_email);

        while (cursor.moveToNext()){

            profile.setImageResource(cursor.getInt(1));
            name.setText(cursor.getString(2));
            email.setText(cursor.getString(3));
            balance.setText(cursor.getDouble(6)+" ₹");
            ifsc.setText("BKSPRK025416");
            ph.setText(cursor.getString(4));
            ac.setText(cursor.getString(5));
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),CustomersActivity.class));
        finishAffinity();
    }
}
