package com.cc.tool.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;


import com.cc.tool.CLog;
import com.cc.tool.Const_def;
import com.cc.tool.help.SharedHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;



public class PicUtil {
    private static final String TAG = "PicUtil";
    private static final int CACHE_PATH = 0;
    private static final int EMOJI_PATH = CACHE_PATH + 1;

    public static Bitmap getNoScaledLocalBmpByUrl(String url, int pathType) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }

        String filename = null;
        if (pathType == CACHE_PATH) {
            filename = SharedHelper.getInstance().getCachePath() + getPicFileName(url);
        } else if (pathType == EMOJI_PATH) {
            filename = Const_def.DOWNLOAD_ZIPPath + getPicFileName(url);
        }

        CLog.v(TAG, "filename" + filename);

        File file = new File(filename);
        if (!file.exists() && !file.isFile()) {
            CLog.v(TAG, "cannot find file" + filename);
            return null;
        }

        CLog.v(TAG, "getNoScaledLocalBmpByUrl...");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        Bitmap b = null;
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(filename);
            b = BitmapFactory.decodeStream(fileInputStream, null, options);
        } catch (Exception e) {
            CLog.d(TAG, "getNoScaledLocalBmpByUrl", e);
        } catch (OutOfMemoryError e) {
            CLog.d(TAG, "getNoScaledLocalBmpByUrl", e);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    CLog.d(TAG, "Exception:" + e);
                }
            }
        }

        if (b == null) {
            return null;
        }

        int src_w = b.getWidth();
        int src_h = b.getHeight();

        int dest_w = src_w > src_h ? src_h : src_w;

        Bitmap sb = null;
        try {
            sb = Bitmap.createBitmap(b, 0, 0, dest_w, dest_w);
        } catch (Exception e) {
            CLog.d(TAG, "getNoScaledLocalBmpByUrl", e);
        } catch (OutOfMemoryError e) {
            CLog.d(TAG, "getNoScaledLocalBmpByUrl", e);
        }

        if (sb == null) {
            return b;
        } else {
            b.recycle();
            return sb;
        }
    }

    public static Bitmap getScaledLocalBmpByUrl(String url, int w, int h) {
        if (w == 0 || h == 0 || TextUtils.isEmpty(url)) {
            return null;
        }

        // String filename = Const_def.sCachePath + getPicFileName(url);
        String filename = SharedHelper.getInstance().getCachePath() + getPicFileName(url);
        return getScaledLocalBmpByFilename(filename, w, h);
    }

    public static Bitmap getScaledLocalBmpByUrl(String dstPath, String url, int w, int h) {
        if (w == 0 || h == 0 || TextUtils.isEmpty(url)) {
            return null;
        }

        String filename = dstPath + getPicFileName(url);
        return getScaledLocalBmpByFilename(filename, w, h);
    }

    public static Bitmap getScaledLocalBmpByUid(String dstPath, int uid, int w, int h) {
        if (w == 0 || h == 0) {
            return null;
        }

        String filename = dstPath + uid + ".jpg";
        return getScaledLocalBmpByFilename(filename, w, h);
    }

    public static Bitmap getScaledLocalBmpByFilename(String filename, int w, int h) {
        CLog.v(TAG, "filename" + filename);

        File file = new File(filename);
        if (!file.exists() && !file.isFile()) {
            CLog.v(TAG, "cannot find file" + filename);
            return null;
        }

        CLog.v(TAG, "createScaledBitmap...");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        Bitmap b = null;
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(filename);
            b = BitmapFactory.decodeStream(fileInputStream, null, options);
        } catch (Exception e) {
            CLog.d(TAG, "getScaledLocalBmpByUrl", e);
        } catch (OutOfMemoryError e) {
            CLog.d(TAG, "getScaledLocalBmpByUrl", e);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    CLog.d(TAG, "Exception:" + e);
                }
            }
        }

        if (b == null) {
            return null;
        }

        int src_w = b.getWidth();
        int src_h = b.getHeight();

        if (src_w == 0 || src_h == 0) {
            b.recycle();
            return null;
        } else if (src_w <= w && src_h <= h) {
            return b;
        }

        float src_ratio = ((float) src_w) / src_h;
        float dst_ratio = ((float) w) / h;

        float dstScale = 1.0f;
        if (dst_ratio > src_ratio) {
            /* refer to height */
            dstScale = ((float) src_h) / h;
        } else {
            /* refer to width */
            dstScale = ((float) src_w) / w;
        }

        int dstWidth = (int) (src_w / dstScale);
        int dstHeight = (int) (src_h / dstScale);

        Bitmap sb = null;
        try {
            sb = Bitmap.createScaledBitmap(b, dstWidth, dstHeight, false);
        } catch (Exception e) {
            CLog.d(TAG, "getScaledLocalBmpByUrl", e);
        } catch (OutOfMemoryError e) {
            CLog.d(TAG, "getScaledLocalBmpByUrl", e);
        }

        if (sb == null) {
            return b;
        } else {
            b.recycle();
            return sb;
        }
    }

    public static Bitmap getStarScaledLocalBmpByUrl(String url, int w) {
        if (w == 0 || TextUtils.isEmpty(url)) {
            return null;
        }

        // String filename = Const_def.sCachePath + getPicFileName(url);
        String filename = SharedHelper.getInstance().getCachePath() + getPicFileName(url);
        return getStarScaledLocalBmpByFilename(filename, w);
    }

    private static Bitmap getStarScaledLocalBmpByFilename(String filename, int w) {
        CLog.v(TAG, "filename" + filename);

        File file = new File(filename);
        if (!file.exists() && !file.isFile()) {
            CLog.v(TAG, "cannot find file" + filename);
            return null;
        }

        CLog.v(TAG, "createScaledBitmap...");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        Bitmap b = null;
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(filename);
            b = BitmapFactory.decodeStream(fileInputStream, null, options);
        } catch (Exception e) {
            CLog.d(TAG, "getScaledLocalBmpByUrl", e);
        } catch (OutOfMemoryError e) {
            CLog.d(TAG, "getScaledLocalBmpByUrl", e);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    CLog.d(TAG, "Exception:" + e);
                }
            }
        }

        if (b == null) {
            return null;
        }

        int src_w = b.getWidth();
        int src_h = b.getHeight();

        if (src_w == 0 || src_h == 0) {
            b.recycle();
            return null;
        } else if (src_w <= w && src_h <= w) {
            return b;
        }

        float src_ratio = ((float) src_w) / src_h;
        float dst_ratio = ((float) w) / w;

        float dstScale = 1.0f;
        if (dst_ratio > src_ratio) {
            /* refer to height */
            dstScale = ((float) src_h) / w;
        } else {
            /* refer to width */
            dstScale = ((float) src_w) / w;
        }

        int dstWidth = (int) (src_w / dstScale);
        int dstHeight = (int) (src_h / dstScale);

        Bitmap sb = null;
        try {
            sb = Bitmap.createScaledBitmap(b, dstWidth, dstHeight, false);
        } catch (Exception e) {
            CLog.d(TAG, "getScaledLocalBmpByUrl", e);
        } catch (OutOfMemoryError e) {
            CLog.d(TAG, "getScaledLocalBmpByUrl", e);
        }

        if (sb == null) {
            return b;
        } else {
            b.recycle();
            return sb;
        }
    }

    public static String storePic(String srcPath, int max_width, int max_height) {
        if (TextUtils.isEmpty(srcPath)) {
            return null;
        }

        File f = new File(srcPath);
        if (f == null || !f.exists()) {
            CLog.d(TAG, "src bmp file not exist, exit:" + srcPath);
            return null;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(srcPath, options);
        options.inDither = true;
        options.inPreferredConfig = Config.ARGB_8888;
        options.inSampleSize = 1;
        options.inPreferQualityOverSpeed = true;

        final int PHOTO_WIDTH = max_width;
        final int PHOTO_HEIGHT = max_height;
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        CLog.d(TAG, "outWidth=" + outWidth);
        CLog.d(TAG, "outHeight=" + outHeight);
        if (outWidth != 0 && outHeight != 0) {
            int sw = outWidth / PHOTO_WIDTH;
            int sh = outHeight / PHOTO_HEIGHT;
            int s = Math.max(sh, sw);

            if (s <= 1) {
                options.inSampleSize = 1;
            } else if (s >= 2 && s < 4) {
                options.inSampleSize = 2;
            } else if (s >= 4 && s < 7) {
                options.inSampleSize = 4;
            } else if (s >= 7 && s < 12) {
                options.inSampleSize = 8;
            } else if (s >= 12 && s < 24) {
                options.inSampleSize = 16;
            } else {
                options.inSampleSize = 32;
            }
        }

        options.inJustDecodeBounds = false;
        Bitmap scaledBmp = (BitmapFactory.decodeFile(srcPath, options));
        if (scaledBmp == null) {
            return null;
        }

        String dstFileName = getPhotoFileName();
        // File dstFile = new File(Const_def.sCachePath, dstFileName);
        File dstFile = new File(SharedHelper.getInstance().getCachePath(), dstFileName);

        try {
            CLog.v(TAG, "storeInSD " + dstFile.getAbsolutePath());
            dstFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(dstFile);
            scaledBmp.compress(CompressFormat.JPEG, 90, fos);
            fos.flush();
            fos.close();
            CLog.v(TAG, "storeInSD  ok");
        } catch (FileNotFoundException e) {
            CLog.v(TAG, "storeInSD FileNotFoundException");
            CLog.d(TAG, "Exception:" + e);
            dstFileName = null;
        } catch (IOException e) {
            CLog.v(TAG, "storeInSD  IOException");
            CLog.d(TAG, "Exception:" + e);
            dstFileName = null;
        }

        return dstFileName;
    }

    public static String getPhotoFileName() {
        long now = System.currentTimeMillis();
        String strNow = String.valueOf(now);
        int len = strNow.length();
        return "p" + strNow.substring(len - 5) + ".jpg";
    }

    // 仅基于宽拉伸
    public static String storePic(Bitmap src, int dst_w, int dst_h) {
        // float dst_ratio = ((float) dst_w) / dst_h;
        // CLog.d(TAG, "dst_w:"+dst_w);
        // CLog.d(TAG, "dst_h:"+dst_h);
        // CLog.d(TAG, "dst_ratio:"+dst_ratio);
        //
        // if (src == null || dst_w == 0 || dst_h == 0) {
        // return null;
        // }
        //
        // int src_w = src.getWidth();
        // int src_h = src.getHeight();
        // float src_ratio = ((float) src_w) / src_h;
        // CLog.d(TAG, "src_w:"+src_w);
        // CLog.d(TAG, "src_h:"+src_h);
        // CLog.d(TAG, "src_ratio:"+src_ratio);
        //
        // boolean needScale = true;
        // if (src_w == 0 || src_h == 0) {
        // return null;
        // } else if (src_w <= dst_w && src_h <= dst_h) {
        // needScale = false;
        // }
        //
        // Bitmap dstBmp;
        // if (needScale) {
        // float dstScale = 1.0f;
        // if (dst_ratio > src_ratio) {
        // /* refer to height */
        // dstScale = ((float) src_h) / dst_h;
        // } else {
        // /* refer to width */
        // dstScale = ((float) src_w) / dst_w;
        // }
        //
        // int dstWidth = (int) (src_w / dstScale);
        // int dstHeight = (int) (src_h / dstScale);
        //
        // CLog.d(TAG, "dstScale:" + dstScale);
        // CLog.d(TAG, "dstWidth:" + dstWidth);
        // CLog.d(TAG, "dstHeight:" + dstHeight);

        int src_w = src.getWidth();
        int src_h = src.getHeight();

        if (src_w == 0 || src_h == 0) {
            return null;
        }

        float src_ratio = ((float) src_w) / src_h;
        CLog.d(TAG, "src_w:" + src_w);
        CLog.d(TAG, "src_h:" + src_h);
        CLog.d(TAG, "src_ratio:" + src_ratio);

        float dstScale = 1.0f;
        dstScale = ((float) src_w) / dst_w;

        int dstHeight = (int) (src_h / dstScale);

        CLog.d(TAG, "dstScale:" + dstScale);
        CLog.d(TAG, "dstHeight:" + dstHeight);

        Bitmap dstBmp = Bitmap.createScaledBitmap(src, dst_w, dstHeight, false);
        // }else{
        // dstBmp = src;
        // }

        if (dstBmp == null) {
            return null;
        }

        String dstFileName = getPhotoFileName();
        // File dstFile = new File(Const_def.sCachePath, dstFileName);
        File dstFile = new File(SharedHelper.getInstance().getCachePath(), dstFileName);

        try {
            CLog.v(TAG, "storeInSD " + dstFile.getAbsolutePath());
            dstFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(dstFile);
            dstBmp.compress(CompressFormat.JPEG, 90, fos);
            fos.flush();
            fos.close();
            CLog.v(TAG, "storeInSD  ok");
        } catch (FileNotFoundException e) {
            CLog.v(TAG, "storeInSD FileNotFoundException");
            CLog.d(TAG, "Exception:" + e);
            dstFileName = null;
        } catch (IOException e) {
            CLog.v(TAG, "storeInSD  IOException");
            CLog.d(TAG, "Exception:" + e);
            dstFileName = null;
        }

        return dstFileName;
    }

    // 仅基于宽拉伸
    public static String storePicScaleBase480(String src) {
        CLog.d(TAG, "storePicScaleBase480:");

        if (src == null) {
            return null;
        }

        // First decode with inJustDecodeBounds=true to check dimensions
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(src, options);
        int src_h = options.outHeight;
        int src_w = options.outWidth;
        // String imageType = options.outMimeType;

        // Calculate inSampleSize
        if (src_w == 0 || src_h == 0) {
            return null;
        }

        float src_ratio = ((float) src_w) / src_h;
        CLog.d(TAG, "src_w:" + src_w);
        CLog.d(TAG, "src_h:" + src_h);
        CLog.d(TAG, "src_ratio:" + src_ratio);

        int dstScale = 1;
        dstScale = (int) (src_w / 480);

        dstScale = dstScale < 1 ? 1 : dstScale;

        int dstHeight = (int) (src_h / dstScale);

        CLog.d(TAG, "dstScale:" + dstScale);
        CLog.d(TAG, "dstHeight:" + dstHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        options.inSampleSize = dstScale;
        Bitmap dstBmp = BitmapFactory.decodeFile(src, options);

        if (dstBmp == null) {
            return null;
        }

        String dstFileName = getPhotoFileName();
        // File dstFile = new File(Const_def.sCachePath, dstFileName);
        File dstFile = new File(SharedHelper.getInstance().getCachePath(), dstFileName);

        try {
            CLog.v(TAG, "storePicWithOutScale storeInSD " + dstFile.getAbsolutePath());
            dstFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(dstFile);
            CLog.d(TAG, "size:" + dstBmp.getWidth() * dstBmp.getHeight() * 4);
            dstBmp.compress(CompressFormat.JPEG, 90, fos);

            fos.flush();
            fos.close();
            CLog.v(TAG, "storePicWithOutScale storeInSD  ok");
        } catch (FileNotFoundException e) {
            CLog.v(TAG, "storePicWithOutScale storeInSD FileNotFoundException");
            CLog.d(TAG, "Exception:" + e);
            dstFileName = null;
        } catch (IOException e) {
            CLog.v(TAG, "storePicWithOutScale storeInSD  IOException");
            CLog.d(TAG, "Exception:" + e);
            dstFileName = null;
        }

        return dstFileName;
    }

    public static String storeRawAvatar(Bitmap src) {
        if (src == null) {
            return null;
        }

        String dstFileName = getPhotoFileName();
        // File dstFile = new File(Const_def.sCachePath, dstFileName);
        File dstFile = new File(SharedHelper.getInstance().getCachePath(), dstFileName);

        try {
            CLog.v(TAG, "storeInSD " + dstFile.getAbsolutePath());
            dstFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(dstFile);
            src.compress(CompressFormat.JPEG, 90, fos);
            fos.flush();
            fos.close();
            CLog.v(TAG, "storeInSD  ok");
        } catch (FileNotFoundException e) {
            CLog.v(TAG, "storeInSD FileNotFoundException");
            CLog.d(TAG, "Exception:" + e);
            dstFileName = null;
        } catch (IOException e) {
            CLog.v(TAG, "storeInSD  IOException");
            CLog.d(TAG, "Exception:" + e);
            dstFileName = null;
        }

        return dstFileName;
    }

    public static Bitmap createSquareBmp(Bitmap src) {
        if (src == null) {
            return null;
        }
        Bitmap dstBmp = null;

        int src_w = src.getWidth();
        int src_h = src.getHeight();

        if (src_h > src_w) {
            int top = (src_h - src_w) / 4;
            dstBmp = Bitmap.createBitmap(src, 0, top, src_w, src_w);
        } else {
            int left = (src_w - src_h) / 2;
            dstBmp = Bitmap.createBitmap(src, left, 0, src_h, src_h);
        }

        return dstBmp;
    }

    public static Bitmap getLocalBmpByUrl(String url) {
        // String filename = Const_def.sCachePath + getPicFileName(url);
        String filename = SharedHelper.getInstance().getCachePath() + getPicFileName(url);
        CLog.v(TAG, "filename" + filename);

        File file = new File(filename);
        if (!file.exists() && !file.isFile()) {
            CLog.v(TAG, "cannot find file" + filename);
            return null;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferQualityOverSpeed = true;
        options.inDither = true;
        Bitmap b = null;

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filename);
            b = BitmapFactory.decodeStream(fileInputStream, null, options);
            /* TODO */
            // b.recycle();

        } catch (Exception e) {
            CLog.d(TAG, "getLocalBmp", e);
        } catch (OutOfMemoryError e) {
            CLog.d(TAG, "getLocalBmp OOM ", e);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    CLog.d(TAG, "getLocalBmp", e);
                }
            }
        }
        return b;
    }
    
    /**
     * 随机获取本地缓存的一张头像
     * 
     * @return
     */
    public static Bitmap getLocalRandomBmp() {
        String filename = null;
        String dir = SharedHelper.getInstance().getCachePath();
        File file = new File(dir);
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                for (File file2 : listFiles) {
                    CLog.d(TAG, "file name = " + file2.getName());
                    if (file2.getName().length() == 17) {
                        filename = file2.getAbsolutePath();
                        break;
                    }
                }
            }

        }

        if (TextUtils.isEmpty(filename)) {
            return null;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferQualityOverSpeed = true;
        options.inDither = true;
        Bitmap b = null;

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filename);
            b = BitmapFactory.decodeStream(fileInputStream, null, options);

        } catch (Exception e) {
            CLog.d(TAG, "getLocalBmp", e);
        } catch (OutOfMemoryError e) {
            CLog.d(TAG, "getLocalBmp OOM ", e);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    CLog.d(TAG, "getLocalBmp", e);
                }
            }
        }
        return b;
    }
    
    public static ArrayList<String> getLocalRandomBmpUrls(int account) {
        ArrayList<String> urls = new ArrayList<String>();

        String filename = null;
        String dir = SharedHelper.getInstance().getCachePath();
        File file = new File(dir);
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                for (File file2 : listFiles) {
                    CLog.d(TAG, "file name = " + file2.getName());
                    if (file2.getName().length() == 17 && file2.length() > 1024) {
                        filename = file2.getAbsolutePath();
                        urls.add(filename);
                        if (urls.size() >= account)
                            break;
                    }
                }
            }

        }

        return urls;
    }
    
    public static String getPicFileName(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }

        int slashPos = url.lastIndexOf("/");
        if (slashPos == -1) {
            CLog.v(TAG, "ignore invalid url, failed");
            return null;
        }

        String tmpFileName = url.substring(slashPos + 1);
        int pos = tmpFileName.lastIndexOf(".jpg");

        if (pos <= 0) {
            pos = tmpFileName.lastIndexOf(".JPG");
        }

        if (pos <= 0) {
            pos = tmpFileName.lastIndexOf(".png");
        }

        if (pos <= 0) {
            pos = tmpFileName.lastIndexOf(".PNG");
        }

        if (pos <= 0) {
            pos = tmpFileName.lastIndexOf(".BMP");
        }

        if (pos <= 0) {
            pos = tmpFileName.lastIndexOf(".bmp");
        }

        String dstFileName = tmpFileName;
        if (pos > 0) {
            dstFileName = tmpFileName.substring(0, pos + 4);
        }

        return dstFileName;
    }

    private static String getDecorativeUrl(String originalUrl, String picNamePrefix, String picNameSubfix) {
        String finalUrl = originalUrl;

        int splitIndex1 = originalUrl.lastIndexOf("/");
        if (splitIndex1 == -1) {
            return finalUrl;
        }

        String firstUrlHalf = originalUrl.substring(0, splitIndex1 + 1);
        String secondUrlHalf = originalUrl.substring(splitIndex1 + 1, originalUrl.length());

        if (TextUtils.isEmpty(secondUrlHalf)) {
            return finalUrl;
        }

        int splitIndex2 = secondUrlHalf.lastIndexOf(".");
        if (splitIndex2 == -1) {
            return finalUrl;
        }

        String firstNameHalf = secondUrlHalf.substring(0, splitIndex2);
        String secondNameHalf = secondUrlHalf.substring(splitIndex2, secondUrlHalf.length());

        StringBuffer buffer = new StringBuffer(originalUrl.length() + 4);
        buffer.append(firstUrlHalf).append(picNamePrefix).append(firstNameHalf).append(picNameSubfix)
                .append(secondNameHalf);

        CLog.d(TAG, "getDecorativeUrl = " + buffer.toString());
        return buffer.toString();
    }
    /**
     * 
     * @param originalUrl
     *            装饰前图片地址
     * @param picType
     *            图片类型：头像、照片缩略图、照片
     * @return
     */
    public static String getDecorativeUrl(String originalUrl, int picType) {
        //CLog.d(TAG, "getDecorativeUrl Net.type = " + Net.type + "  picType = " + picType);
        String finalUrl = originalUrl;

//        if (TextUtils.isEmpty(originalUrl)) {
//            return finalUrl;
//        }
//
//        int picQuality = Settings.getInstance().picQuality;
//
//        if (picType != DownloadItem.TYPE_DOWNLOAD_ITEM_STAR) {
//            // 图片路径带有前后缀
//            originalUrl = originalUrl.replace("a_", "").replace("p_", "").replace("m_", "").replace("_o", "")
//                    .replace("_xs", "").replace("_s", "").replace("_m", "").replace("_l", "").replace("_xl", "");
//        }
//
//        // http://pic1.xianglianai.cn/photo/1a/5e/09e99b2ef5704614c474af2e653b/1779780366102.jpg
//        if (picType == DownloadItem.TYPE_DOWNLOAD_ITEM_AVATAR) {
//            if (picQuality == Const_def_def.PIC_QUALITY_NORMAL
//                    || (picQuality == Const_def_def.PIC_QUALITY_AUTO && Net.type == Net.NET_WROK_2G)) {
//                return getDecorativeUrl(originalUrl, "a_", "_s");
//            } else {
//                return getDecorativeUrl(originalUrl, "a_", "_m");
//            }
//
//        } else if (picType == DownloadItem.TYPE_DOWNLOAD_ITEM_VIP_AVATAR) {
//            if (picQuality == Const_def.PIC_QUALITY_NORMAL
//                    || (picQuality == Const_def.PIC_QUALITY_AUTO && Net.type == Net.NET_WROK_2G)) {
//                return getDecorativeUrl(originalUrl, "a_", "_m");
//            } else {
//                return getDecorativeUrl(originalUrl, "a_", "_l");
//            }
//        } else if (picType == DownloadItem.TYPE_DOWNLOAD_ITEM_PHOTO_THUMBNAIL) {
//            if (picQuality == Const_def.PIC_QUALITY_NORMAL
//                    || (picQuality == Const_def.PIC_QUALITY_AUTO && Net.type == Net.NET_WROK_2G)) {
//                return getDecorativeUrl(originalUrl, "p_", "_xs");
//            } else {
//                return getDecorativeUrl(originalUrl, "p_", "_s");
//            }
//
//        } else if (picType == DownloadItem.TYPE_DOWNLOAD_ITEM_PHOTO) {
//            if (picQuality == Const_def.PIC_QUALITY_NORMAL
//                    || (picQuality == Const_def.PIC_QUALITY_AUTO && Net.type == Net.NET_WROK_2G)) {
//                return getDecorativeUrl(originalUrl, "p_", "_s");
//            } else {
//                return getDecorativeUrl(originalUrl, "p_", "_m");
//            }
//        } else if (picType == DownloadItem.TYPE_DOWNLOAD_ITEM_STAR) {
//            return originalUrl;
//        }

        return finalUrl;
    }

    /* 初始化图片到sdcard */
    public static String getSnsShareImagePath(Context context) {
        String filePath = null;
//        try {
//            String cachePath = SharedHelper.getInstance().getCachePath();
//            filePath = cachePath + "pic_share_0001.jpg";
//            File file = new File(filePath);
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//
//            Bitmap pic = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
//            FileOutputStream fos = new FileOutputStream(file);
//            pic.compress(CompressFormat.JPEG, 100, fos);
//            fos.flush();
//            fos.close();
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }

        return filePath;
    }

    /*
     * 获取表情图片
     */
    public static Bitmap getEmojiBitmap(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }

        String emojiPath = path.trim().replace(" ", "");
        CLog.v(TAG, "emojiname: " + emojiPath);

        File file = new File(emojiPath);
        if (!file.exists() && !file.isFile()) {
            CLog.v(TAG, "cannot find file: " + emojiPath);
            return null;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferQualityOverSpeed = true;
        options.inDither = true;
        Bitmap b = null;

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(emojiPath);
            b = BitmapFactory.decodeStream(fileInputStream, null, options);
            /* TODO */
            // b.recycle();

        } catch (Exception e) {
            CLog.d(TAG, "getLocalBmp", e);
        } catch (OutOfMemoryError e) {
            CLog.d(TAG, "getLocalBmp OOM ", e);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    CLog.d(TAG, "getLocalBmp", e);
                }
            }
        }
        return b;
    }
    public static Bitmap toRoundCorner(String path, int pixels, int width, int height) {
        if(TextUtils.isEmpty(path)){
            return null;
        }
        Bitmap b = getScaledLocalBmpByFilename(path, width, height);
        if(b == null){
            return null;
        }
        return toRoundCorner(b, pixels);
    }
    // 设置ImageView圆角图案
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);

        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;

        final Paint paint = new Paint();

        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        final RectF rectF = new RectF(rect);

        final float roundPx = pixels;

        paint.setAntiAlias(true);

        canvas.drawARGB(0, 0, 0, 0);

        paint.setColor(color);

        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));

        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;

    }
}
