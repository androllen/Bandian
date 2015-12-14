package com.cc.tool.help;

import com.cc.cache.image.ImageReuseInfo;
import com.cc.cache.image.ImageReuseInfoManger;
import com.cc.tool.util.DisplayUtils;


public class ImageSize {

    private static final String[] sizeList = new String[]{"small_180", "big_360", "big"};

    public static final ImageReuseInfoManger sImageReuseInfoManger = new ImageReuseInfoManger(sizeList);
    public static final ImageReuseInfo sGridImageReuseInfo = sImageReuseInfoManger.create("big_360");
    public static final ImageReuseInfo sBigImageReuseInfo = sImageReuseInfoManger.create("big");
    public static final ImageReuseInfo sSmallImageReuseInfo = sImageReuseInfoManger.create("small_180");

    public static final int sGirdImageSize = (DisplayUtils.SCREEN_WIDTH_PIXELS - DisplayUtils.dp2px(12 + 12 + 10)) / 2;
    public static final int sBigImageSize = DisplayUtils.SCREEN_WIDTH_PIXELS - DisplayUtils.dp2px(10 + 10);
}