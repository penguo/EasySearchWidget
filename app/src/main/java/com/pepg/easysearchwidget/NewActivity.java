package com.pepg.easysearchwidget;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pepg.easysearchwidget.Adapter.RcvAdapter;

public class NewActivity extends AppCompatActivity {

    RecyclerView rcv;
    RcvAdapter rcvAdapter;
    DBManager dbManager;
    public static int appWidgetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            appWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        dbManager = new DBManager(this, "SearchLink.db", null, 1);

        rcv = (RecyclerView) findViewById(R.id.new_rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcvAdapter = new RcvAdapter(dbManager, this, 1);
        rcv.setAdapter(rcvAdapter);
    }

    public void getWidget(){
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();
    }
}
