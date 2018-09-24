package com.shenyong.aabills.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);

    @Insert
    void insertUsers(List<User> users);

    @Query("select * from user where mName = :name")
    User queryUser(String name);

    @Query("select * from user where mId = :id")
    User queryUser(int id);

    @Query("select * from user")
    List<User> queryAllUsers();
}

