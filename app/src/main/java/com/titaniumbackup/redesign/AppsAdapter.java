package com.titaniumbackup.redesign;

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
        AppInfo app = mApps.get(position);

        holder.mName.setText(app.name);
        holder.mInfo.setText(app.info);
        holder.mIcon.setImageDrawable(app.icon);
        holder.mExpandable.collapse();
        holder.mExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPreviousHolder != null && mPreviousHolder != holder)
                    mPreviousHolder.mExpandable.collapse();
                holder.mExpandable.toggle();
                mPreviousHolder = holder;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mApps.size();
    }

    class AppHolder extends RecyclerView.ViewHolder {
        private ImageView mIcon;
        private TextView mName, mInfo;
        private ExpandableLayout mExpandable;
        private LinearLayout mExpand;

        AppHolder(View view) {
            super(view);
            mIcon = (ImageView) view.findViewById(R.id.app_icon);
            mName = (TextView) view.findViewById(R.id.app_name);
            mInfo = (TextView) view.findViewById(R.id.app_info);
            mExpandable = (ExpandableLayout) view.findViewById(R.id.expandable);
            mExpand = (LinearLayout) view.findViewById(R.id.expand_view);
        }
    }
}
