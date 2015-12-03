package com.cc.tool;

/**
 * Created by androllen on 2015/8/28. 开关调试
 */
public class CCDebug {

    public CCDebug(){
    }

    public static boolean DEBUG_IMAGE=true;

    public static boolean DEBUG_CACHE = false;
    public static boolean DEBUG_REQUEST = false;
    public static String DEBUG_REQUEST_LOG_TAG = "cube-request";
    public static String DEBUG_IMAGE_LOG_TAG = "cube-image";
    public static String DEBUG_IMAGE_LOG_TAG_TASK = "cube-image-task";
    public static String DEBUG_IMAGE_LOG_TAG_PROVIDER = "cube-image-provider";
    public static boolean DEBUG_SCROLL_HEADER_FRAME = false;
    public static boolean DEBUG_PAGE_INDICATOR = false;
    public static boolean DEBUG_LIST = false;
    public static boolean DEBUG_LIFE_CYCLE = false;
    
    public final static boolean DBG = true;
    public final static boolean LOG = true;
    // 广告开关
    public final static boolean HAS_AD = true;

    public final static boolean REPORT_EVENT = false;
    public final static String SVR_URL;
    public static String MSG_URL;
    public final static String UPLOAD_PHOTO_URL;
    public final static String UPLOAD_AVATAR_URL;
    public final static String UPLOAD_IDCARD_URL;

    public final static String UPLOAD_IMAGE_URL;

    public final static String PAY_URL;
    public final static String SOLDIERSHOW_URL;
    static {
        if (DBG) {
            UPLOAD_PHOTO_URL = "http://192.168.1.100:8080/friend/uploadphoto";
            UPLOAD_AVATAR_URL = "http://192.168.1.100:8080/friend/uploadavatar";
            UPLOAD_IDCARD_URL = "http://192.168.1.100:8080/friend/uploadidcard";
            UPLOAD_IMAGE_URL = "http://114.215.120.78:81/upload.php";
            SVR_URL = "http://114.215.120.78:8080/friend/api";
            //SVR_URL = "http://192.168.1.205:8090/friend/api"; //wfcesi
            MSG_URL = "http://114.215.120.78:8170/message/api"; // CESI
            PAY_URL = "http://114.215.120.78:7080/pay/api";
            SOLDIERSHOW_URL = "http://114.215.120.78:8080/friend/w/c2/";
        } else {
            UPLOAD_PHOTO_URL = "http://api.xianglianai.cn/friend/uploadphoto";
            UPLOAD_AVATAR_URL = "http://api.xianglianai.cn/friend/uploadavatar";
            UPLOAD_IDCARD_URL = "http://api.xianglianai.cn/friend/uploadidcard";
            UPLOAD_IMAGE_URL = "http://picup.xianglianai.cn/upload.php";
            MSG_URL = "http://msg.xianglianai.cn/api";
            SVR_URL = "http://api.xianglianai.cn/friend/api";
            PAY_URL = "http://pay.xianglianai.cn/api";
            SOLDIERSHOW_URL = "http://api.xianglianai.cn/friend/w/c2/";
        }
    }
}
