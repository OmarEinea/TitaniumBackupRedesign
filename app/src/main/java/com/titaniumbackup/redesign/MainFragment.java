package com.titaniumbackup.redesign;


import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private RecyclerView mRecyclerView;

    public MainFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        PackageManager pm = view.getContext().getPackageManager();
        ArrayList<AppInfo> apps = new ArrayList<>();

        for(ApplicationInfo app : pm.getInstalledApplications(0))
            apps.add(new AppInfo(app.loadLabel(pm).toString(), app.packageName, app.loadIcon(pm)));

        mRecyclerView = (RecyclerView) view.findViewById(R.id.apps_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), 1));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setAdapter(new AppsAdapter(apps));

        return view;
    }
}
