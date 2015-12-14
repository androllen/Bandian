package com.cc.cache.image.iface;

import com.cc.cache.image.ImageTask;
import com.cc.cache.image.ImageTaskStatistics;

public interface ImageLoadProfiler {
    public void onImageLoaded(ImageTask imageTask, ImageTaskStatistics stat);
}
