package com.mad.mizen;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tables")
public class Table {
    @PrimaryKey
    private int tableId;

    public Table(int tableid) {
        this.tableId = tableid;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }
}
