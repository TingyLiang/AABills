package com.shenyong.aabills.room;

import java.util.List;

public interface BillsDataSource {

    interface LoadBillsCallback<T> {

        void onBillsLoaded(List<T> bills);

        void onDataNotAvailable();
    }

    interface DelBillCallback {
        void onDeleteSuccess();
        void onDeleteFailed();
    }

}
