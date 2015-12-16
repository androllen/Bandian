package com.cc.component.holder;

public interface ViewHolderCreator<ItemDataType> {
    ViewHolderBase<ItemDataType> createViewHolder(int paramInt);
}