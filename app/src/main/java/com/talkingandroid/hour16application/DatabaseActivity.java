package com.talkingandroid.hour16application;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;

public class DatabaseActivity extends Activity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        textView = (TextView) findViewById(R.id.textView);

        ArrayList<Pie> pies = Pie.makePies();
        PieDbAdapter pieDbAdapter = new PieDbAdapter(this);
        pieDbAdapter.open();
        for (Pie pie: pies){
            ContentValues newValues = new ContentValues();
            newValues.put(PieDbAdapter.NAME, pie.mName);
            newValues.put(PieDbAdapter.DESCRIPTION, pie.mDescription);
            newValues.put(PieDbAdapter.PRICE, pie.mPrice);
            newValues.put(PieDbAdapter.FAVORITE, pie.mIsFavorite);
            pieDbAdapter.insertPie(newValues);
        }

        Cursor pieCursor = pieDbAdapter.getPies();
        StringBuilder results = new StringBuilder();
        if (pieCursor.moveToFirst()){
            do{
                Pie pie =  pieDbAdapter.getPieFromCursor(pieCursor);
                results.append(pie.mId + " " + pie.mName + " " + pie.mPrice + " "
                        + pie.mDescription + " " + pie.mIsFavorite +"\n");
            } while (pieCursor.moveToNext());
        }
        pieCursor.close();
        pieDbAdapter.close();
        textView.setText(results.toString());
    }
}
