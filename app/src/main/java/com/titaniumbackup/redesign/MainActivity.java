package com.titaniumbackup.redesign;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageManager pm = getPackageManager();
        ArrayList<AppInfo> apps = new ArrayList<>();

        for(ApplicationInfo app : pm.getInstalledApplications(0))
            apps.add(new AppInfo(app.loadLabel(pm).toString(), app.packageName, app.loadIcon(pm)));

        mRecyclerView = (RecyclerView) findViewById(R.id.apps_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), 1));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new AppsAdapter(apps));
    }
}
