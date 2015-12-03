package com.cc.tool.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;

import com.cc.tool.CLog;
import com.cc.tool.util.Version;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.InputStream;

/**
 * Created by androllen on 2015/8/27.
 */

public class DiskFileUtils {
    public DiskFileUtils() {
    }

    public static DiskFileUtils.CacheDirInfo getDiskCacheDir(Context context, String absolutePath, int sizeInKB, String fallbackRelativePath) {
        long size = (long)sizeInKB * 1024L;
        boolean done = false;
        DiskFileUtils.CacheDirInfo dirInfo = new DiskFileUtils.CacheDirInfo();
        dirInfo.requireSize = size;
        if(!TextUtils.isEmpty(absolutePath)) {
            File cachePath = new File(absolutePath);
            if(cachePath.exists() || cachePath.mkdirs()) {
                long free = getUsableSpace(cachePath);
                size = Math.min(size, free);
                dirInfo.realSize = size;
                dirInfo.path = cachePath;
                done = true;
            }
        }

        if(!done) {
            if(TextUtils.isEmpty(fallbackRelativePath)) {
                fallbackRelativePath = absolutePath;
            }

            dirInfo = getDiskCacheDir(context, fallbackRelativePath, size);
        }

        return dirInfo;
    }

    public static DiskFileUtils.CacheDirInfo getDiskCacheDir(Context context, String uniqueName, long requireSpace) {
        File sdPath = null;
        File internalPath = null;
        Long sdCardFree = Long.valueOf(0L);
        boolean usingInternal = false;
        if(hasSDCardMounted()) {
            sdPath = getExternalCacheDir(context);
            if(!sdPath.exists()) {
                sdPath.mkdirs();
            }

            sdCardFree = Long.valueOf(getUsableSpace(sdPath));
        }

        DiskFileUtils.CacheDirInfo cacheDirInfo = new DiskFileUtils.CacheDirInfo();
        cacheDirInfo.requireSize = requireSpace;
        if(sdPath != null && sdCardFree.longValue() >= requireSpace) {
            usingInternal = false;
            cacheDirInfo.realSize = requireSpace;
        } else {
            internalPath = context.getCacheDir();
            long internalFree = getUsableSpace(internalPath);
            if(internalFree < requireSpace) {
                if(internalFree > sdCardFree.longValue()) {
                    usingInternal = true;
                    cacheDirInfo.realSize = internalFree;
                } else {
                    usingInternal = false;
                    cacheDirInfo.realSize = sdCardFree.longValue();
                }

                cacheDirInfo.isNotEnough = true;
            } else {
                usingInternal = true;
                cacheDirInfo.realSize = requireSpace;
            }
        }

        cacheDirInfo.isInternal = usingInternal;
        if(usingInternal) {
            cacheDirInfo.path = new File(internalPath.getPath() + File.separator + uniqueName);
        } else {
            cacheDirInfo.path = new File(sdPath.getPath() + File.separator + uniqueName);
        }

        if(!cacheDirInfo.path.exists() && !cacheDirInfo.path.mkdirs()) {
            CLog.e("cube-cache", "can not create directory for: %s", new Object[]{cacheDirInfo.path});
        }

        return cacheDirInfo;
    }

    @TargetApi(8)
    public static File getExternalCacheDir(Context context) {
        if(Version.hasFroyo()) {
            File cacheDir = context.getExternalCacheDir();
            if(cacheDir != null) {
                return cacheDir;
            }
        }

        String cacheDir1 = "/Android/data/" + context.getPackageName() + "/cache/";
        return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir1);
    }

    @TargetApi(9)
    public static long getUsableSpace(File path) {
        if(path == null) {
            return -1L;
        } else if(Version.hasGingerbread()) {
            return path.getUsableSpace();
        } else if(!path.exists()) {
            return 0L;
        } else {
            StatFs stats = new StatFs(path.getPath());
            return (long)stats.getBlockSize() * (long)stats.getAvailableBlocks();
        }
    }

    @TargetApi(9)
    public static long getUsedSpace(File path) {
        if(path == null) {
            return -1L;
        } else if(Version.hasGingerbread()) {
            return path.getTotalSpace() - path.getUsableSpace();
        } else if(!path.exists()) {
            return -1L;
        } else {
            StatFs stats = new StatFs(path.getPath());
            return (long)stats.getBlockSize() * (long)(stats.getBlockCount() - stats.getAvailableBlocks());
        }
    }

    @TargetApi(9)
    public static long getTotalSpace(File path) {
        if(path == null) {
            return -1L;
        } else if(Version.hasGingerbread()) {
            return path.getTotalSpace();
        } else if(!path.exists()) {
            return 0L;
        } else {
            StatFs stats = new StatFs(path.getPath());
            return (long)stats.getBlockSize() * (long)stats.getBlockCount();
        }
    }

    public static boolean hasSDCardMounted() {
        String state = Environment.getExternalStorageState();
        return state != null && state.equals("mounted");
    }

    public static String wantFilesPath(Context context, boolean externalStorageFirst) {
        String path = null;
        File f = null;
        if(externalStorageFirst && hasSDCardMounted() && (f = context.getExternalFilesDir("xxx")) != null) {
            path = f.getAbsolutePath();
        } else {
            path = context.getFilesDir().getAbsolutePath();
        }

        return path;
    }

    public static String readAssert(Context context, String filePath) {
        try {
            if(filePath.startsWith(File.separator)) {
                filePath = filePath.substring(File.separator.length());
            }

            AssetManager e = context.getAssets();
            InputStream inputStream = e.open(filePath);
            DataInputStream stream = new DataInputStream(inputStream);
            int length = stream.available();
            byte[] buffer = new byte[length];
            stream.readFully(buffer);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(buffer);
            stream.close();
            return byteArrayOutputStream.toString();
        } catch (Exception var8) {
            var8.printStackTrace();
            return null;
        }
    }

    public static class CacheDirInfo {
        public File path;
        public boolean isInternal = false;
        public boolean isNotEnough = false;
        public long realSize;
        public long requireSize;

        public CacheDirInfo() {
        }
    }
}
