package com.pepg.easysearchwidget;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by pengu on 2017-08-10.
 */

public class RcvAdapter extends RecyclerView.Adapter<RcvAdapter.ViewHolder> {
    private Activity activity;

    private DBManager dbManager;

    public RcvAdapter(DBManager dbManager, Activity activity) {
        this.dbManager = dbManager;
        this.activity = activity;
    }

    @Override
    public int getItemCount() {
        return dbManager.getSize();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        /**************************************************/
        /** TODO initialize view components in item view **/
        /**************************************************/
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

    }

    private void removeItemView(int position) {
        dbManager.delete(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, dbManager.getSize()); // 지워진 만큼 다시 채워넣기.
    }
}