package com.cc.cache.image.iface;

import com.cc.cache.image.CubeImageView;
import com.cc.cache.image.ImageTask;

/**
 * Define load progress
 */
public interface ImageLoadProgressHandler {

    void onProgressChange(ImageTask imageTask, CubeImageView cubeImageView, int loaded, int total);
}
