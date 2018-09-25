package com.mad.mizen;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Staff {

    @PrimaryKey
    private int staffId;

    private String name;
    private int[] tableIds;

    public Staff(int staffId, String name, int[] tableIds) {
        this.staffId = staffId;
        this.name = name;
        this.tableIds = tableIds;
    }

    public int getStaffId() {
        return staffId;
    }

    public String getName() {
        return name;
    }

    public int[] getTableIds() {
        return tableIds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void setTableIds(int[] tableIds) {
        this.tableIds = tableIds;
    }
}
