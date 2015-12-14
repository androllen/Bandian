package com.cc.cache.image.iface;

import com.cc.cache.image.ImageTask;

import java.io.OutputStream;

public interface ImageDownloader {

    public boolean downloadToStream(ImageTask imageTask,
                                    String url,
                                    OutputStream outputStream,
                                    ProgressUpdateHandler progressUpdateHandler);
}