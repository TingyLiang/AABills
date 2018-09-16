package com.shenyong.aabills.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface BillDao {

    @Insert
    void insertBill(BillRecord billRecord);

    @Query("select * from bill_record")
    List<BillRecord> getAllBills();


//    List<BillRecord> getBillsByTime()
}
