package com.pepg.easysearchwidget.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.pepg.easysearchwidget.MainActivity;
import com.pepg.easysearchwidget.R;

/**
 * Created by pengu on 2017-11-02.
 */
public class WidgetProvider extends AppWidgetProvider {

    /**
     * 브로드캐스트를 수신할때, Override된 콜백 메소드가 호출되기 직전에 호출됨
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    /**
     * 위젯을 갱신할때 호출됨
     * 주의 : Configure Activity를 정의했을때는 위젯 등록시 처음 한번은 호출이 되지 않습니다
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        int appId;
        for (int i = 0; i < appWidgetIds.length; i++) {
            appId = appWidgetIds[i];
            Intent intent = new Intent(context, MainActivity.class);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            PendingIntent pendingIntent = PendingIntent.getActivity(context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            views.setOnClickPendingIntent(R.id.widget_btn_1, pendingIntent);
            appWidgetManager.updateAppWidget(appId, views);
        }
    }

    /**
     * onEnabled:  위젯이 처음 생성될때 호출됨
     * 동일한 위젯이 생성되도 최초 생성때만 호출됨
     *      
     * onDisabled: 위젯의 마지막 인스턴스가 제거될때 호출됨
     * onEnabled()에서 정의한 리소스 정리할때
     *  
     * onDeleted: 위젯이 사용자에 의해 제거될때 호출됨
     */


    /* TODO ListProvider 만들고, 리스트뷰 적용
    private RemoteViews updateWidgetListView(Context context, int appWidgetId) {

        //which layout to show on widget
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        //RemoteViews Service needed to provide adapter for ListView
        Intent svcIntent = new Intent(context, ListProvider.class);
        //passing app widget id to that RemoteViews Service
        svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        //setting a unique Uri to the intent
        //don't know its purpose to me right now
        svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
        //setting adapter to listview of the widget
        remoteViews.setRemoteAdapter(appWidgetId, R.id.widget_listview, svcIntent);
        //setting an empty view in case of no data
        remoteViews.setEmptyView(R.id.widget_listview, R.id.widget_tv_1);
        return remoteViews;
    }
    */
}