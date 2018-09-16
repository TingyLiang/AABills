package com.shenyong.aabills;

import com.sddy.utils.ArrayUtils;
import com.sddy.utils.TimeUtils;
import com.shenyong.aabills.listdata.BillRecordData;
import com.shenyong.aabills.room.BillRecord;
import com.shenyong.aabills.room.BillRepository;
import com.shenyong.aabills.room.BillsDataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class BillStatisticsViewModel {

    private BillRepository mBillRepository;

    public BillStatisticsViewModel() {
        mBillRepository = BillRepository.getInstance();
    }

    public void loadBills(final BillsDataSource.LoadBillsCallback<BillRecordData> callback) {
        if (callback == null) {
            return;
        }
        mBillRepository.getBills(new BillsDataSource.LoadBillsCallback<BillRecord>() {
            @Override
            public void onBillsLoaded(List<BillRecord> bills) {
                if (ArrayUtils.isEmpty(bills)) {
                    callback.onDataNotAvailable();
                    return;
                }
                final List<BillRecordData> billsData = new ArrayList<>();
                final HashMap<String, List<BillRecord>> monthStat = new HashMap<>();
                for (BillRecord billRecord : bills) {
                    String month = TimeUtils.getDateString(billRecord.mTimestamp, "yyyy年MM月");
                    List<BillRecord> subList = monthStat.get(month);
                    if (subList == null) {
                        subList = new ArrayList<>();
                        monthStat.put(month, subList);
                    }
                    subList.add(billRecord);
                }
                List<String> listKeys = new ArrayList<>(monthStat.keySet());
                Collections.sort(listKeys);
                for (String key : listKeys) {
                    BillRecordData data = new BillRecordData();
                    data.mTime = key;
                    Set<String> types = new HashSet<>();
                    List<BillRecord> subList = monthStat.get(key);
                    double amount = 0;
                    for (BillRecord bill : subList) {
                        types.add(bill.mType);
                        amount += bill.mAmount;
                    }
                    StringBuffer typeBuffer = new StringBuffer("主要消费：");
                    for (String t : types) {
                        typeBuffer.append(t);
                        typeBuffer.append("、");
                    }
                    data.mType = typeBuffer.toString().substring(0, typeBuffer.length() - 1);
                    data.mAmount = String.format("%.1f元", amount);
                    billsData.add(data);
                }
                callback.onBillsLoaded(billsData);
                /**
                Observable.fromArray(bills.toArray(new BillRecord[bills.size()]))
                        .map(new Function<BillRecord, BillRecordData>() {
                            @Override
                            public BillRecordData apply(BillRecord billRecord) throws Exception {
                                BillRecordData data = new BillRecordData();
                                data.mRecordId = billRecord.mId;
                                data.mTime = TimeUtils.getDateString(billRecord.mTimestamp, "yyyy年MM月");
                                data.mType = "主要消费：" + billRecord.mType;
                                data.mAmount = String.format("%.1f元", billRecord.mAmount);
                                return data;
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<BillRecordData>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(BillRecordData billRecordData) {
                                billsData.add(billRecordData);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                if (billsData.isEmpty()) {
                                    callback.onDataNotAvailable();
                                } else {
                                    callback.onBillsLoaded(billsData);
                                }
                            }
                        });*/
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    public void delBill(BillRecordData billRecordData, final BillsDataSource.DelBillCallback callback) {
        mBillRepository.deleteBill(billRecordData.mRecordId, callback);
    }
}
