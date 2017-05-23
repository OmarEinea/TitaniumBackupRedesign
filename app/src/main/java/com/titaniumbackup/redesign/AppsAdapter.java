package com.titaniumbackup.redesign;

import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;

/** Created by Omar Einea on 5/23/17. */

class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.AppHolder> {
    private ArrayList<AppInfo> mApps;
    private AppHolder mPreviousHolder;

    AppsAdapter(ArrayList<AppInfo> apps) {
        mApps = apps;
    }

    @Override
    public AppHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AppHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.app_view, parent, false));
    }

    @Override
    public void onBindViewHolder(final AppHolder holder, int position) {
        final AppInfo app = mApps.get(position);

        holder.mName.setText(app.name);
        holder.mInfo.setText(app.info);
        holder.mIcon.setImageDrawable(app.icon);
        holder.mExpandable.collapse();
        setItemColors(app.enabled, holder);

        holder.mExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPreviousHolder != null && mPreviousHolder != holder)
                    mPreviousHolder.mExpandable.collapse();
                holder.mExpandable.toggle();
                mPreviousHolder = holder;
            }
        });
        holder.mBackup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.snack(v, "Backing Up " + app.name + "...", Snackbar.LENGTH_LONG);
            }
        });
        holder.mRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.snack(v, "Restoring " + app.name + "...", Snackbar.LENGTH_LONG);
            }
        });
        holder.mFreeze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.enabled = setItemColors(!app.enabled, holder);
                MainActivity.snack(v, app.name + " was " + (app.enabled ? "Unf" : "F") + "rozen Successfully", Snackbar.LENGTH_SHORT);
            }
        });
        holder.mUninstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mApps.remove(holder.getAdapterPosition());
                AppsAdapter.this.notifyItemRemoved(holder.getAdapterPosition());
                MainActivity.snack(v, app.name + " was Uninstalled Successfully", Snackbar.LENGTH_SHORT);
            }
        });
    }

    private Boolean setItemColors(Boolean status, AppHolder holder) {
        if(status) {
            holder.mIcon.setColorFilter(null);
            holder.mIcon.setAlpha(255);
            holder.mName.setTextColor(Color.BLACK);
            holder.mInfo.setTextColor(Color.BLACK);
        } else {
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(0);
            holder.mIcon.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            holder.mIcon.setAlpha(200);
            holder.mName.setTextColor(Color.GRAY);
            holder.mInfo.setTextColor(Color.GRAY);
        }
        return status;
    }

    @Override
    public int getItemCount() {
        return mApps.size();
    }

    class AppHolder extends RecyclerView.ViewHolder {
        private ImageView mIcon;
        private TextView mName, mInfo;
        private ExpandableLayout mExpandable;
        private LinearLayout mExpand, mBackup, mRestore, mUninstall, mFreeze;

        AppHolder(View view) {
            super(view);
            mIcon = (ImageView) view.findViewById(R.id.app_icon);
            mName = (TextView) view.findViewById(R.id.app_name);
            mInfo = (TextView) view.findViewById(R.id.app_info);
            mExpandable = (ExpandableLayout) view.findViewById(R.id.expandable);
            mExpand = (LinearLayout) view.findViewById(R.id.expand_view);
            mBackup = (LinearLayout) view.findViewById(R.id.backup_btn);
            mRestore = (LinearLayout) view.findViewById(R.id.restore_btn);
            mUninstall = (LinearLayout) view.findViewById(R.id.uninstall_btn);
            mFreeze = (LinearLayout) view.findViewById(R.id.freeze_btn);
        }
    }
}
