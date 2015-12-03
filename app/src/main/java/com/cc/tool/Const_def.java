package com.cc.tool;

import android.os.Environment;

/**
 * Created by androllen on 2015/8/19.
 */
public class Const_def {

    public final static String ACTION_XLA_ALARM_TIMEOUT = "xianlianai.action.ALARM_TIMEOUT";
    public final static String ACTION_XLA_EXIT = "xianlianai.action.EXIT_TIMEOUT";
    public final static String ACTION_XLA_AD_ALARM_TIMEOUT = "xianlianai.action.AD_ALARM_TIMEOUT";

    public final static String KEY_F_STATIC_CACHE_PATH = "static_cache_path";
    public final static String PREFS_NAME = "LovePrefsFile";

    // 守护服务
    public final static String ACTION_XLA_DAEMON_TIMEOUT = ".action.DAEMON_TIMEOUT";
    public final static int ACTION_XLA_DAEMON_TIMEOUT_REQUEST_CODE = 3;

    public static final String KEY_DATA = "Const_def";
    public static final String ROOTPATHDIR="bandian-dir";
    public static final String MSG_URL = "Const_def";
    public final static int TIMER_START = 1;
    public final static int TIMER_STOP = 0;

    public final static int VOID_INT = -9999999;
    public final static int VALID_UID = 10300000;
    public final static int DIVIDER_UID = 13630000;
    public final static int KEFU_UID_DIVIDER = 2000;

    public final static int MALE = 1;
    public final static int FEMALE = 0;
    public final static int MS_NO_PAIED = 1;
    public final static int MS_PAIED = 2;

    public final static int MS_NO_VIP = 1;
    public final static int MS_VIP = 2;

    public final static int TYPE_IMAGE_AVATAR = 1;
    public final static int TYPE_IMAGE_PHOTO = 2;
    public final static int TYPE_IMAGE_IDCARD = 3;
    public final static int TYPE_IMAGE_EDU = 4;

    public final static int MS_MAIL_LOCK = 1;
    public final static int MS_MAIL_NO_LOCK = 0;

    public final static int MOBILE_CHK = 1;// 手机号认证 0：未认证 1：已认证
    public final static int MOBILE_NO_CHK = 0;
    public final static int IDCARD_CHK = 3; // 身份证上传 1：未上传 2：审核中 3：审核通过
    public final static int IDCARD_CHKING = 2;
    public final static int IDCARD_NO_UPLOAD = 1;

    public final static int TYPE_CHK = 3; // 身份证上传 1：未上传 2：审核中 3：审核通过
    public final static int TYPE_CHKING = 2;
    public final static int TYPE_CHK_NO_UPLOAD = 1;

    public final static int QQ_PUB = 1;// qq公开 0：不公开 1：公开
    public final static int QQ_NO_PUB = 0;
    public final static int MOBILE_PUB = 1;// 手机号公开 0：不公开 1：公开
    public final static int MOBILE_NO_PUB = 0;

    public final static int PRIVATE_SET_MEMBER = 1;
    public final static int PRIVATE_SET_ALL = 0;

    public final static int MAX_AGE = 50;
    public final static int MIN_AGE = 16;
    public final static int MAX_HEIGHT = 200;
    public final static int MIN_HEIGHT = 140;

    public final static int DEFAULT_PROVINCE = 110000;

    public final static int COLUMNS = 3;
    public final static int ROWS = 8;

    public final static int KEFU_ID = 1000;

    public final static String UTF8 = "UTF-8";

    /* status */
    public static boolean sCloudMaintaining = false;
    // 表情 0 未购买 ，1购买
    public final static int EMOJI_PAYED = 1;
    public final static int EMOJI_NO_PAYED = 0;

    // 通知类型
    public final static int TYPE_MSG_MEMBERSHIP_NO_REFRESH = 1;
    public final static int TYPE_MSG_MEMBERSHIP_REFRESH = 2;
    public final static int TYPE_MSG_AVATAR_REFRESH = 3;
    public final static int TYPE_MSG_PHOTO_REFRESH = 5;

    // 生分证、学历
    public final static int TYPE_MSG_IDCARD_EDU_REFRESH = 6;
    public final static int TYPE_MSG_GOLD_REFRESH = 7;

    public final static int TYPE_MSG_PHOTO_CHK = 4;

    // 申请礼包类型
    public final static int TYPE_GIFTPKG_GOLD_NEWER = 1;
    public final static int TYPE_GIFTPKG_GOLD_MOBILE = 2;
    public final static int TYPE_GIFTPKG_GOLD_IDCARD = 3;
    public final static int TYPE_GIFTPKG_GOLD_EDU = 4;
    public final static int TYPE_GIFTPKG_GOLD_AVATR = 5;
    public final static int TYPE_GIFTPKG_GOLD_MYDETAIL = 6;
    public final static int TYPE_GIFTPKG_GOLD_MYPIC = 7;

    /* 图片质量 */
    public static final int PIC_QUALITY_AUTO = 1;
    public static final int PIC_QUALITY_BEST = 2;
    public static final int PIC_QUALITY_NORMAL = 3;

    /* 女性第一次和对方未主动联系的人发私信 */
    public static final int FM_MSG_FILTER_ENABLE = 1; // 可以主动发起
    public static final int FM_MSG_FILTER_DIS_ENABLE = 0; // 不能主动发起

    /* 支付开关 */
    public static final int PAY_SWITCHER_OFF = 0;
    public static final int PAY_SWITCHER_ON = 1;

    // 新广播类型
    public static final int MSG_TYPECONTENT_TEXT = 1; // 文本
    public static final int MSG_TYPECONTENT_PICTURE = 2; // 图片类型
    public static final int MSG_TYPECONTENT_WEBVIEW = 3; // 网页形式

    public static final int MSG_TYPEGUIDE_NO_INTENT = 1; // 不跳转
    public static final int MSG_TYPEGUIDE_INTENT_APP = 2; // 应用内跳转
    public static final int MSG_TYPEGUIDE_INTENT_WEBSITE = 3; // 网站内跳转
    public static final int MSG_TYPEGUIDE_INTENT_DOWNLOAD = 4; // 下载
    public static final int MSG_TYPEGUIDE_INTENT_APPWEB = 5; // 跳到本应用浏览器

    // TODO replace
    public static int MSG_FIRST_REQ_GONGGAO = 0; /* 1:是 0：否 */

    // public static int VISITOES_OTHER_UID = Const_def.VOID_INT; // 谁看了我对方的Uid;

    /* zip下载路径 */
    public static final String DOWNLOAD_ZIPPath = Environment.getExternalStorageDirectory().toString() + "/xla/emoji/";
    //视频下载地址
    public static final String DOWNLOAD_VIDEOPATH=Environment.getExternalStorageDirectory().toString() +"/cc/video/";

    public final static int ONE_SECOND = 1000;
    public final static int HALF_MINUTE = 29 * ONE_SECOND;
    public final static int ONE_MINUTE = 60 * ONE_SECOND;
    public final static int ONE_HOUR = 60 * ONE_MINUTE;
    public final static int ONE_DAY = 24 * ONE_HOUR;

    public final static int TIME_SLICE_5 = 5 * ONE_MINUTE;
    public final static int TIME_SLICE_2 = 2 * ONE_MINUTE;
    public final static int PAUSE_POINT = 2;
    public final static int RESUME_POINT = 7;


    //
    public static final int RecordMaxTime=10;
}
