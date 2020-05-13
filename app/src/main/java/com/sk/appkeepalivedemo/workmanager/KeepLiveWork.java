package com.sk.appkeepalivedemo.workmanager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * Created by smark on 2020/5/13.
 * 邮箱：smarkwzp@163.com
 * 利用jetpack中的workManager启动WorkJobService进行保活
 */
public class KeepLiveWork extends Worker {

    public KeepLiveWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        WorkJobService.startJob(getApplicationContext());

        return Result.success();
    }
}
