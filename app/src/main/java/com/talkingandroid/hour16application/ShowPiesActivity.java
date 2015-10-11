package com.talkingandroid.hour16application;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class ShowPiesActivity extends Activity {
    ListView listView;
    PieDbAdapter pieDbAdapter;
    Cursor cursor;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pies);
        listView = (ListView) findViewById(R.id.pieListView);
        pieDbAdapter = new PieDbAdapter(this);
        pieDbAdapter.open();
        cursor = pieDbAdapter.getExpensivePies();
        adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                cursor, //Cursor
                new String[] {PieDbAdapter.NAME},
                new int[] { android.R.id.text1 }, 0);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if (cursor!=null){
            cursor.close();
        }
        if (pieDbAdapter!=null){
            pieDbAdapter.close();
        }
    }



}
