package com.example.thesparkbank;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomersActivity extends AppCompatActivity implements RecyclerViewClick{

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<Model> data;
    Adapter adapter;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);

        initData();

        initRecyclerView();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.history_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.history){
            Intent i  = new Intent(getApplicationContext(),HistoryActivity.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {

        data = new ArrayList<>();

        db = new DbHelper(this);

        Cursor cursor = db.getData();

        while(cursor.moveToNext()){

            Model model = new Model(cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getDouble(6));
            data.add(model);
        }

    }

    private void initRecyclerView() {

        recyclerView =  findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(data,this);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    private void layoutAnimation(RecyclerView recyclerView){

        Context context = recyclerView.getContext();
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_fall_down);
        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();

    }

    @Override
    public void onItemClick(int position) {

        String pr_email = data.get(position).getTx2();

        Intent i = new Intent(getApplicationContext(),ProfileActivity.class);
        i.putExtra("pr_email",pr_email);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
