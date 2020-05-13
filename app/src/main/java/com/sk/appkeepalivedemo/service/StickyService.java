package com.sk.appkeepalivedemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * Created by smark on 2020/5/13.
 * 邮箱：smarkwzp@163.com
 * 使用粘性服务进行保活
 */
public class StickyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //START_STICKY
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
