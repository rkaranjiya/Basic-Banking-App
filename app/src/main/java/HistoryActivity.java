package com.example.thesparkbank;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView hrecyclerView;
    LinearLayoutManager layoutManager;
    List<HistoryModel> historyData;
    HistoryAdapter historyAdapter;
    TextView history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        history = findViewById(R.id.empty);

        initData();

        initRecyclerView();

    }

    private void initData() {

        historyData = new ArrayList<>();

        historyData.clear();

        Cursor cursor = new DbHelper(this).getHistory();

        while(cursor.moveToNext()){

            HistoryModel historyModel = new HistoryModel(cursor.getString(1),cursor.getString(2),cursor.getString(4),cursor.getDouble(3));
            historyData.add(historyModel);
        }

    }

    private void initRecyclerView() {

        if(historyData.size() == 0){
            history.setVisibility(View.VISIBLE);
        }

        hrecyclerView =  findViewById(R.id.hrecycler);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        hrecyclerView.setLayoutManager(layoutManager);
        hrecyclerView.setItemAnimator(new DefaultItemAnimator());
        historyAdapter = new HistoryAdapter(historyData);
        hrecyclerView.setAdapter(historyAdapter);


        historyAdapter.notifyDataSetChanged();

    }

    private void layoutAnimation(RecyclerView recyclerView){

        Context context = recyclerView.getContext();
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_fall_down);
        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),CustomersActivity.class));
        finish();
    }

}
