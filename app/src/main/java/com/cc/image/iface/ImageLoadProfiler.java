package com.cc.image.iface;

import com.cc.image.ImageTask;
import com.cc.image.ImageTaskStatistics;

public interface ImageLoadProfiler {
    public void onImageLoaded(ImageTask imageTask, ImageTaskStatistics stat);
}
