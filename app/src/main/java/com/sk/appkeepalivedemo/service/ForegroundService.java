package com.sk.appkeepalivedemo.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

/**
 * Created by smark on 2020/5/13.
 * 邮箱：smarkwzp@163.com
 * 开启服务进行保活
 * 不同版本有差异，需分开处理 此种方法适用于音乐播放器保活，8.0以后会在通知栏显示
 */
public class ForegroundService extends Service {
    private static final String TAG = "ForegroundService";
    private static final int SERVICE_ID = 1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e(TAG, "ForegroundService 服务创建了");

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {//4.3以下
            //将service设置成前台服务，并且不显示通知栏消息
            startForeground(SERVICE_ID, new Notification());
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) { //Android4.3-->Android7.0
            //将service设置成前台服务
            startForeground(SERVICE_ID, new Notification());
            //删除通知栏消息
            startService(new Intent(this, InnerService.class));
        } else { // 8.0 及以上
            //通知栏消息需要设置channel
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            //NotificationManager.IMPORTANCE_MIN 通知栏消息的重要级别  最低，不让弹出
            //IMPORTANCE_MIN 前台时，在阴影区能看到，后台时 阴影区不消失，增加显示 IMPORTANCE_NONE时 一样的提示
            //IMPORTANCE_NONE app在前台没有通知显示，后台时有
            NotificationChannel channel = new NotificationChannel("channel", "xx", NotificationManager.IMPORTANCE_NONE);
            if (manager != null) {
                manager.createNotificationChannel(channel);
                Notification notification = new NotificationCompat.Builder(this, "channel").build();
                //将service设置成前台服务，8.x退到后台会显示通知栏消息，9.0会立刻显示通知栏消息
                startForeground(SERVICE_ID, notification);
            }
        }
    }

    public static class InnerService extends Service {

        @Override
        public void onCreate() {
            super.onCreate();
            Log.e(TAG, "InnerService 服务创建了");
            // 让服务变成前台服务
            startForeground(SERVICE_ID, new Notification());
            // 关闭自己
            stopSelf();
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }
}
