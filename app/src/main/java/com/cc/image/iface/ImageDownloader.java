package com.cc.image.iface;

import com.cc.image.ImageTask;

import java.io.OutputStream;

public interface ImageDownloader {

    public boolean downloadToStream(ImageTask imageTask,
                                    String url,
                                    OutputStream outputStream,
                                    ProgressUpdateHandler progressUpdateHandler);
}