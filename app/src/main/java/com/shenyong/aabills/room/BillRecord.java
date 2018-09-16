package com.shenyong.aabills.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "bill_record")
public class BillRecord {

    @PrimaryKey(autoGenerate = true)
    public int mId;
    @ColumnInfo(typeAffinity = ColumnInfo.REAL)
    public double mAmount;
    @ColumnInfo
    public String mType;
    @ColumnInfo
    public long mTimestamp;
    @ColumnInfo
    public int mUserId;
}
