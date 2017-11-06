package com.pepg.easysearchwidget;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pepg.easysearchwidget.Adapter.RcvAdapter;

public class MainActivity extends Activity implements View.OnClickListener {

    Context context;
    DBManager dbManager;
    TextView tv;
    Button btnReset;
    RecyclerView rcv;
    RcvAdapter rcvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        dbManager = new DBManager(this, "SearchLink.db", null, 1);

        rcv = (RecyclerView) findViewById(R.id.main_rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcvAdapter = new RcvAdapter(dbManager, this, 0);
        rcv.setAdapter(rcvAdapter);

        tv = (TextView) findViewById(R.id.main_tv_title);
        btnReset = (Button) findViewById(R.id.main_btn_reset);

        btnReset.setOnClickListener(this);
    }

    public void setTv(String st) {
        tv.setText(st);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case(R.id.main_btn_reset):
                dbManager.reset();
                break;
        }
    }
}
