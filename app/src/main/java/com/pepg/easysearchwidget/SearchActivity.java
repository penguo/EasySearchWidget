package com.pepg.easysearchwidget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.app.AlertDialog;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.pepg.easysearchwidget.NewActivity.appWidgetId;

public class SearchActivity extends AppCompatActivity {

    private View decorView;
    private int uiOption;
    public String inputString, url;
    private Context context;
    DBManager dbManager;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreen();
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        context = SearchActivity.this;
        dbManager = new DBManager(this, "SearchLink.db", null, 1);
        dbManager.getWidgetValue(appWidgetId);
        viewDialog();
    }

    private void viewDialog() {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout dialogLayout = (LinearLayout) li.inflate(R.layout.dialog_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        final TextView tv = (TextView) dialogLayout.findViewById(R.id.dialog_tv);
        final EditText et = (EditText) dialogLayout.findViewById(R.id.dialog_et);
        final ImageButton btnSearch = (ImageButton) dialogLayout.findViewById(R.id.dialog_btn_search);

        tv.setText(dbManager.DATA_NAME);
        url = dbManager.DATA_LINK;
        builder.setView(dialogLayout);
        dialog = builder.create();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputString = et.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url + inputString));
                startActivity(intent);
                dialog.dismiss();
                finish();
            }
        });

        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.show();
    }

    public void fullScreen() {
        decorView = getWindow().getDecorView();
        uiOption = getWindow().getDecorView().getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOption);
    }

    @Override
    public void onBackPressed() {
        dialog.dismiss();
        finish();
    }
}
