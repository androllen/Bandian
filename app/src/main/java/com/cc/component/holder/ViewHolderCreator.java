package com.cc.component.holder;

public abstract interface ViewHolderCreator<ItemDataType>
{
  public abstract ViewHolderBase<ItemDataType> createViewHolder(int paramInt);
}