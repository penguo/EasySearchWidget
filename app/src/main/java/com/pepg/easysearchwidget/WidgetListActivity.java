package com.pepg.easysearchwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pepg.easysearchwidget.Adapter.RcvAdapter;

public class WidgetListActivity extends AppCompatActivity {

    RecyclerView rcv;
    RcvAdapter rcvAdapter;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_list);

        dbManager = new DBManager(this, "SearchLink.db", null, 1);

        rcv = (RecyclerView) findViewById(R.id.widgetList_rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcvAdapter = new RcvAdapter(dbManager, this, "WidgetList");
        rcv.setAdapter(rcvAdapter);
    }
}
