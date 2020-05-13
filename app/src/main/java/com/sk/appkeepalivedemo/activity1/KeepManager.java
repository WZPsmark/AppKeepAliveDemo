package com.sk.appkeepalivedemo.activity1;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.lang.ref.WeakReference;

/**
 * Created by smark on 2020/5/13.
 * 邮箱：smarkwzp@163.com
 * 1像素activity保活管理类
 */
public class KeepManager {

    private static final KeepManager mInstance = new KeepManager();

    private KeepAliveReceiver mKeepAliveReceiver;

    private WeakReference<Activity> mKeepActivity;

    public KeepManager() {

    }

    public static KeepManager getInstance() {
        return mInstance;
    }

    /**
     * 注册 开屏 关屏 广播
     *
     * @param context
     */
    public void registerKeep(Context context) {
        IntentFilter filter = new IntentFilter();

        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);

        mKeepAliveReceiver = new KeepAliveReceiver();
        context.registerReceiver(mKeepAliveReceiver, filter);
    }

    /**
     * 注销 广播接收者
     *
     * @param context
     */
    public void unregisterKeep(Context context) {
        if (mKeepAliveReceiver != null) {
            context.unregisterReceiver(mKeepAliveReceiver);
        }
    }

    /**
     * 开启1像素Activity
     *
     * @param context
     */
    public void startKeep(Context context) {
        Intent intent = new Intent(context, AliveActivity.class);
        // 结合 taskAffinity 一起使用 在指定栈中创建这个activity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 关闭1像素Activity
     */
    public void finishKeep() {
        if (mKeepActivity != null) {
            Activity activity = mKeepActivity.get();
            if (activity != null) {
                activity.finish();
            }
            mKeepActivity = null;
        }
    }

    /**
     * 设置弱引用
     *
     * @param keep
     */
    public void setKeep(AliveActivity keep) {
        mKeepActivity = new WeakReference<Activity>(keep);
    }


}
