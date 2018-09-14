package com.sddy.baseui.recycler;

public interface IItemClickLisntener<T extends BaseHolderData> {

    void onClick(T data, int position);
}
