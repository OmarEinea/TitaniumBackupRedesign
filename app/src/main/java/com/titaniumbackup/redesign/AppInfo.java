package com.titaniumbackup.redesign;

import android.graphics.drawable.Drawable;

/** Created by Omar Einea on 5/23/17. */

class AppInfo {
    String name, info;
    Drawable icon;
    Boolean enabled = true;

    AppInfo(String name, String info, Drawable icon) {
        this.name = name;
        this.info = info;
        this.icon = icon;
    }
}
