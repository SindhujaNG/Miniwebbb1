package com.example.miniweb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    public static final int RESULT_HistoryActivity = 1;
    History_helper db;
    RelativeLayout historylayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle("History");
        historylayout = findViewById(R.id.history_layout);

        db = new History_helper(this);
        Log.d("Reading: ", "Reading all Websites..");
        List<Website> websites = db.getAllWebsite();

        for (Website wn : websites) {
            String log = "Id: " + wn.getId() + " ,Name: " + wn.getUrl() + " ,Title: " +
                    wn.getTitle() + ",Date:" + wn.getDate();
            // Writing websites to log
            Log.d("Result: ", log);


        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history_menu, menu);
        return true;
    }

}