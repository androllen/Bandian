package com.cc.bandian;

import com.cc.tool.Const_def;
import com.cc.viewmodel.Listener.MeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by androllen on 2015/9/2.
 */
public class Me implements Cloneable{
    public static int sUid = Const_def.VOID_INT;
    public static boolean isDirty = true;

    /* attributes has no default value */
    public static int sSex = Const_def.VOID_INT;
    public static String sRegTime = null;
    public static String sUsername = null;
    public static String sPassword = null;
    public static String sNickname = null;
    public static String sAvatar = null;// 审核通过的头像地址
    public static String sNewavatar = null;// 审核中的头像地址
    public static String sBirthday = null; /* yyyy-MM-dd */

    /* attributes has default value */
    public static int sMembership = Const_def.MS_NO_PAIED; /* 2: payed 会员 */
    public static int sVip = Const_def.MS_NO_PAIED; /* 2: payed vip */
    public static int sRmail = Const_def.MS_NO_PAIED; /* 2: payed 收信宝 */
    public static int sWmail = Const_def.MS_NO_PAIED; /* 2: payed 发信宝 */
    public static int sMatchmaker1 = Const_def.MS_NO_PAIED; // 1：非红娘红线 2：红娘红线
    public static int sMatchmaker2 = Const_def.MS_NO_PAIED; // 1：非红娘围脖 2：红娘围脖
    public static int sMatchmaker3 = Const_def.MS_NO_PAIED; // 1：非战衣 2：战衣
    public static int sMatchmaker1count = 0; // 1：红娘红线剩余数
    public static int sMatchmaker2count = 0; // 1：围脖剩余数

    public static int sHeight = 0;
    public static int sEducation = 1;
    public static int sConst_defellation = Const_def.VOID_INT;// 星座
    public static int sProvince = 110000; /* 6 integer */
    public static int sCity = 111100; /* 6 integer */
    public static int sNativeCity = 111100; /* 6 integer */
    public static int sWeight = 120;
    public static int sBloodtype = 1; /* index */
    public static int sIncome = 1; /* index */
    public static int sJob = 3; /* index */
    public static int sHouse = 0; /* index */
    public static int sChild = 0; /* index */
    public static int sMarriage = 0; /* index */
    public static String sInterest = ""; /* String seperated by , */
    public static String sStyle = ""; /* String setpeated by , */
    public static int sCharmparts = 0; /* index */
    public static String sMobile = null;
    public static int sRemotelove = 0; /* index */
    public static int sLovertype = 1; /* Not used now */
    public static int sCohabit = 1; /* Not used now */
    public static int sSexfirst = 0; /* index */
    public static int sWithparent = 0; /* index */
    public static int sSmoke = 0; /* boolean */
    public static int sDrink = 0; /* boolean */
    public static String sFeeling = null; /* String */
    public static float sLat = (float) Const_def.VOID_INT;
    public static float sLng = (float) Const_def.VOID_INT;
    public static String sLastlogin = null; /* String */

    public static String sDeadline = null;// 蓝钻截止日期
    public static String sVipDeadline = null;// vip截止日期
    public static String sRmailDeadline = null;// 收信宝截止日期
    public static String sWmailDeadline = null;// 私信包月截止日期
    public static String sMatchmaker1deadline = null;// 红娘红线截止日期
    public static String sMatchmaker2deadline = null;// 红娘围脖截止日期
    public static String sMatchmaker3deadline = null;// 战衣截止日期
    public static String sIp = null;

    public static int sMobilechk = 0;// 手机号认证 0：未认证 1：已认证
    public static int sIdcardchk = 1;// 身份证上传 0：未上传 1：已上传 3：认证
    public static int sEduchk = 1;// 身份证上传 0：未上传 1：已上传 3：认证
    public static String sQq = null;// qq
    public static int sQqpublish = 1;// qq公开 0：不公开 1：公开
    public static int sMobilepublish = 1;// 手机号公开 0：不公开 1：公开
    // public static int sPrivateset = 0;// 隐私设置 0：所有人 1：会员

    public static int sGold = 0;// 金币数

    public static String sVideoUrl = null;
    public static String sNewVideoUrl = null;

    private static List<MeListener> sMeListeners = new ArrayList<MeListener>();

    public static void addChangeListener(MeListener l) {
        synchronized (sMeListeners) {
            if (sMeListeners.contains(l)) {
                sMeListeners.remove(l);
            }
            sMeListeners.add(l);
        }
    }

    public static void removeChangeListener(MeListener l) {
        synchronized (sMeListeners) {
            sMeListeners.remove(l);
        }
    }

    public static void notifyMeChanged(boolean user_changed) {
        synchronized (sMeListeners) {
            for (MeListener listener : sMeListeners) {
                listener.onChanged(user_changed);
            }
        }
    }


}
