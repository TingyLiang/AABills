package com.shenyong.aabills.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int mId;

    @ColumnInfo
    public String mName;

    @ColumnInfo
    @ColorInt
    public int mIconBg;

    public User(String mName) {
        this.mName = mName;
    }

    @Override
    public String toString() {
        return "User{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mIconBg=" + mIconBg +
                '}';
    }
}
