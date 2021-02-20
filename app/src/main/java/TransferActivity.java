package com.example.thesparkbank;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.muddzdev.styleabletoast.StyleableToast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TransferActivity extends AppCompatActivity {


    Button btn;
    ImageView dp1,dp2;
    EditText trans_amnt;
    TextView from;
    Spinner to;
    String name2;
    List<String> list1,list2;
    List<Double> list3;
    List<Integer> list4;
    String date,toEmail;
    Double fromBal,toBal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        btn = findViewById(R.id.trans_btn);

        dp1 = findViewById(R.id.tr_dp);
        dp2 = findViewById(R.id.tr_dp2);

        from = findViewById(R.id.tr_customer);

        to = findViewById(R.id.tr_customer2);

        to.setDropDownVerticalOffset(500);

        final String pr_email = getIntent().getStringExtra("pr_email");


        getFromuser(pr_email);

        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        list4 = new ArrayList<>();



        setNameList(pr_email);

        trans_amnt =  findViewById(R.id.trans_money);
        trans_amnt.setText("0.00");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertHistory(pr_email);

            }
        });

    }

    private void getFromuser(String pr_email) {

        Cursor cursor = new DbHelper(this).getProfile(pr_email);

        while (cursor.moveToNext()){

            dp1.setImageResource(cursor.getInt(1));
            from.setText(cursor.getString(2));
            fromBal = cursor.getDouble(6);
        }

    }

    private void insertHistory(String pr_name) {

        Double bal = Double.parseDouble(trans_amnt.getText().toString());

        if(bal == 0){

            trans_amnt.setError("Please enter amount !");
        }
        else if(fromBal < bal){

            dialog();

        }

        else{

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
            date = sdf.format(calendar.getTime());

            new DbHelper(this).updateAmount(pr_name,fromBal-bal);
            new DbHelper(this).updateAmount(toEmail,toBal+bal);

            boolean chek = new DbHelper(this).addTransfer(from.getText().toString(),name2,bal,date);

            if(chek == true)
            {

                StyleableToast.makeText(getApplicationContext(),"Transaction Successful",Toast.LENGTH_LONG,R.style.sucessToast).show();

                Intent i = new Intent(getApplicationContext(),CustomersActivity.class);
                startActivity(i);
                finish();
            }

        }

    }

    private void dialog() {

        StyleableToast.makeText(getApplicationContext(),"Low Balance",Toast.LENGTH_LONG,R.style.lowBalanceToast).show();

    }

    private void setNameList(String pr_email) {

        Cursor cursor = new DbHelper(this).getExceptProfile(pr_email);

        while (cursor.moveToNext()){

            list1.add(cursor.getString(3));
            list4.add(cursor.getInt(1));
            list2.add(cursor.getString(2));
            list3.add(cursor.getDouble(6));

        }


        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,list2);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        to.setAdapter(arrayAdapter1);

        to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                dp2.setImageResource(list4.get(position));
                name2 = parent.getItemAtPosition(position).toString();
                toEmail = list1.get(position);
                toBal = list3.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                dp2.setImageResource(R.drawable.logo);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
