package com.cc.adapter.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.cc.bandian.R;
import com.cc.component.holder.ViewHolderBase;
import com.cc.cache.image.CubeImageView;
import com.cc.cache.image.ImageLoader;
import com.cc.tool.help.ImageSize;


public class StringSmallImageViewHolder extends ViewHolderBase<String> {

    private CubeImageView mImageView;

    private ImageLoader mImageLoader;

    public StringSmallImageViewHolder(ImageLoader imageLoader) {
        mImageLoader = imageLoader;
    }

    @Override
    public View createView(LayoutInflater inflater) {
        View v = inflater.inflate(R.layout.load_small_image_list_item, null);
        mImageView = (CubeImageView) v.findViewById(R.id.load_small_image_list_item_image_view);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        return v;
    }

    @Override
    public void showData(int position, String itemData) {
        mImageView.loadImage(mImageLoader, itemData, ImageSize.sSmallImageReuseInfo);
    }
}
