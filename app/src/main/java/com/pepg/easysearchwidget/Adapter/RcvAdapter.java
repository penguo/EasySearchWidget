package com.pepg.easysearchwidget.Adapter;

import android.app.Activity;
import android.content.SharedPreferences;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pepg.easysearchwidget.DBManager;
import com.pepg.easysearchwidget.NewActivity;
import com.pepg.easysearchwidget.R;

import static com.pepg.easysearchwidget.DBManager.DATA_LINK;
import static com.pepg.easysearchwidget.DBManager.DATA_NAME;

/**
 * Created by pengu on 2017-08-10.
 */

public class RcvAdapter extends RecyclerView.Adapter<RcvAdapter.ViewHolder> {
    private Activity activity;
    private DBManager dbManager;
    private int type;
    public int id;

    public RcvAdapter(DBManager dbManager, Activity activity, int type) {
        this.dbManager = dbManager;
        this.activity = activity;
        this.type = type;
    }

    @Override
    public int getItemCount() {
        return dbManager.getSize();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvLink;
        LinearLayout layoutItem;
        /**************************************************/
        /** TODO initialize view components in item view **/
        /**************************************************/
        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.item_tv_title);
            tvLink = (TextView) itemView.findViewById(R.id.item_tv_link);
            layoutItem = (LinearLayout) itemView.findViewById(R.id.item_linearlayout);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        id = position + 1;
        dbManager.getValue(id);

        holder.tvTitle.setText(DATA_NAME);
        holder.tvLink.setText(DATA_LINK);
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = position + 1;
                dbManager.getValue(id);
                switch (type) {
                    case (0): // MainActivity

                        break;
                    case (1): // NewActivity
                        newActivity(id);
                        Toast.makeText(activity, id+"", Toast.LENGTH_SHORT).show();
                        ((NewActivity) activity).getWidget();
                        break;
                }
            }
        });
    }

    private void removeItemView(int position) {
        id = position + 1;
        dbManager.delete(id);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, dbManager.getSize()); // 지워진 만큼 다시 채워넣기.
    }

    private void newActivity(int selectId) {
        dbManager.insertWidget(NewActivity.appWidgetId, selectId);
    }
}