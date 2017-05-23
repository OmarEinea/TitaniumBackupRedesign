package com.titaniumbackup.redesign;

import java.util.Date;

/** Created by Omar Einea on 5/23/17. */

class Schedule {
    String label;
    Date date;
    boolean enabled;

    Schedule(String label, Date date, boolean enabled) {
        this.label = label;
        this.date = date;
        this.enabled = enabled;
    }
}
