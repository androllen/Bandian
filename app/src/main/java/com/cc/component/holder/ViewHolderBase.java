package com.cc.component.holder;

import android.view.LayoutInflater;
import android.view.View;

public abstract class ViewHolderBase<ItemDataType>
{
  protected int mLastPosition;
  protected int mPosition;
  protected View mCurrentView;

  public ViewHolderBase()
  {
    this.mPosition = -1;
  }

  public abstract View createView(LayoutInflater paramLayoutInflater);

  public abstract void showData(int paramInt, ItemDataType paramItemDataType);

  public void setItemData(int position, View view)
  {
    this.mLastPosition = this.mPosition;
    this.mPosition = position;
    this.mCurrentView = view;
  }

  public boolean stillHoldLastItemData()
  {
    return (this.mLastPosition == this.mPosition);
  }
}