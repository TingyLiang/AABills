package com.shenyong.aabills.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface BillDao {

    @Insert
    void insertBill(BillRecord billRecord);

    @Delete()
    void deleteBill(BillRecord billRecord);

    @Query("select * from bill_record")
    List<BillRecord> getAllBills();

    @Query("select * from bill_record where mTimestamp >= :startTime and mTimestamp < :endTime")
    List<BillRecord> getBills(long startTime, long endTime);

    @Query("select * from bill_record where mId = :billId")
    BillRecord queryBill(int billId);

}
