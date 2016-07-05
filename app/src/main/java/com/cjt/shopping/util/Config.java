package com.cjt.shopping.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 作者: 陈嘉桐 on 2016/7/4
 * 邮箱: 445263848@qq.com.
 */
public class Config {
    public static String APP = "com.cjt.shopping";
    public static String KEY_USERID = "keyuserid";
    public static String KEY_PHONE = "keyphone";
    public static String KEY_USERNAME = "keyusername";

    //保存用户手机号码
    public static void savePhone(Context context, String phone) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(APP, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(KEY_PHONE, phone);
        editor.commit();
    }

    //获取用户手机号码
    public static String getPhone(Context context) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(APP, Activity.MODE_PRIVATE);
        return mySharedPreferences.getString(KEY_PHONE, "");
    }

    //保存用户ID
    public static void saveUserId(Context context, String userid) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(APP, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(KEY_USERID, userid);
        editor.commit();
    }

    //获取用户ID
    public static String getUserId(Context context) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(APP, Activity.MODE_PRIVATE);
        return mySharedPreferences.getString(KEY_USERID, "");
    }

    //保存用户名
    public static void saveUserName(Context context, String username) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(APP, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.commit();
    }

    //获取用户名
    public static String getUserName(Context context) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(APP, Activity.MODE_PRIVATE);
        return mySharedPreferences.getString(KEY_USERNAME, "");
    }

}
