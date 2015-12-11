package com.cc.image.iface;

import com.cc.image.CubeImageView;
import com.cc.image.ImageTask;

/**
 * Define load progress
 */
public interface ImageLoadProgressHandler {

    void onProgressChange(ImageTask imageTask, CubeImageView cubeImageView, int loaded, int total);
}
