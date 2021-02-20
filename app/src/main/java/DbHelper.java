package com.example.thesparkbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.example.thesparkbank.R.drawable.*;
import static com.example.thesparkbank.R.drawable.bill;
import static com.example.thesparkbank.R.drawable.elon;
import static com.example.thesparkbank.R.drawable.jeff;
import static com.example.thesparkbank.R.drawable.kingkhan;
import static com.example.thesparkbank.R.drawable.lakshmi;
import static com.example.thesparkbank.R.drawable.modi;
import static com.example.thesparkbank.R.drawable.mukesh;
import static com.example.thesparkbank.R.drawable.ratan;
import static com.example.thesparkbank.R.drawable.steve;
import static com.example.thesparkbank.R.drawable.vin;

public class DbHelper extends SQLiteOpenHelper {



    public DbHelper(@Nullable Context context) {

        super(context, "customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create Table CustomersDetails(id INTEGER primary key autoincrement, dp INTEGER, name TEXT, email TEXT, acno TEXT, ph TEXT, bal number(10,2))");

        db.execSQL("create Table TransferHistory(trans_id INTEGER primary key autoincrement, name1 TEXT, name2 TEXT,transfer number(10,10),date text)");

        db.execSQL("Insert into CustomersDetails(dp,name,email,acno,ph,bal) values ("+ ratan+",\"Ratan\",\"ratan@gmail.com\",\"SPKF0001\",\"+912536474269\",10000.00)," +
                "("+modi+",\"Modi\",\"pm@gmail.com\",\"SPKF0002\",\"+915648723649\",50000.00)," +
                "("+mukesh+",\"Mukesh\",\"antila@gmail.com\",\"SPKF0003\",\"+914523674589\",10000.00)," +
                "("+bill+",\"Bill\",\"ms@gmail.com\",\"SPKF0004\",\"+910234024042\",50000.00)," +
                "("+steve+",\"Steve\",\"apple@gmail.com\",\"SPKF0005\",\"+916699669987\",82020.00)," +
                "("+elon+",\"Elon\",\"tesla@gmail.com\",\"SPKF0006\",\"+912545265245\",98989.00)," +
                "("+lakshmi+",\"Lakshmi\",\"mittal@gmail.com\",\"SPKF0007\",\"+912563256325\",55656.00)," +
                "("+kingkhan+",\"Kingkhan\",\"srk@gmail.com\",\"SPKF0008\",\"+914563256452\",84562.00)," +
                "("+jeff+",\"Jeff\",\"amazone@gmail.com\",\"SPKF0009\",\"+918523698523\",64592.00)," +
                "("+vin+",\"Vin\",\"ff@gmail.com\",\"SPKF00010\",\"+914562365489\",45123.00);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS CustomersDetails");
        db.execSQL("DROP TABLE IF EXISTS TransferHistory");
        onCreate(db);

    }

    public boolean addTransfer(String to,String from,Double money,String date){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name1",to);
        contentValues.put("name2",from);
        contentValues.put("transfer",money);
        contentValues.put("date",date);

        long result = db.insert("TransferHistory", null, contentValues);

        if( result == -1){
            return false;
        }
        else{
            return true;
        }

    }

    public Cursor getHistory() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from TransferHistory",null);
        return cursor;
    }

    public Cursor getProfile(String email) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from CustomersDetails where email = ?", new String[]{email});

        return cursor;

    }

    public Cursor getExceptProfile(String email){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from CustomersDetails where email != ?",new String[]{email});
        return cursor;

    }

    public Boolean updateAmount(String email, Double bal){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("bal",bal);

        Cursor cursor = db.rawQuery("Select * from CustomersDetails where email = ?", new String[]{email});

        if (cursor.getCount() > 0) {
            long result = db.update("CustomersDetails", contentValues, "email=?", new String[]{email});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else{
            return true;
        }

    }

    public Cursor getData() {

       SQLiteDatabase db = this.getWritableDatabase();
       Cursor cursor = db.rawQuery("Select * from CustomersDetails",null);
       return cursor;
    }

}