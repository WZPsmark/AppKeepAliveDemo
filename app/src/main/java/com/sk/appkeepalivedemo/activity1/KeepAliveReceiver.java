package com.sk.appkeepalivedemo.activity1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by smark on 2020/5/13.
 * 邮箱：smarkwzp@163.com
 * 息屏广播监听
 */
public class KeepAliveReceiver extends BroadcastReceiver {
    private static final String TAG = "KeepAliveReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "onReceive:" + action);
        if (TextUtils.equals(action, Intent.ACTION_SCREEN_OFF)) {
            //息屏 开启
            KeepManager.getInstance().startKeep(context);
        } else if (TextUtils.equals(action, Intent.ACTION_SCREEN_ON)) {
            //开屏 关闭
            KeepManager.getInstance().finishKeep();
        }

    }
}
