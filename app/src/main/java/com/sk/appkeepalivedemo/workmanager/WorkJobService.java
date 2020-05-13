package com.sk.appkeepalivedemo.workmanager;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;

/**
 * Created by smark on 2020/5/13.
 * 邮箱：smarkwzp@163.com
 */
public class WorkJobService extends JobService {

    private static final String TAG = "WorkJobService";

    public static void startJob(Context context) {
        Log.e(TAG, "startJob");

        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);

        JobInfo.Builder builder = new JobInfo.Builder(8, new ComponentName(context.getPackageName(),
                WorkJobService.class.getName())).setPersisted(true);

        // 小于7.0
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            builder.setPeriodic(1000);
        } else {
            builder.setMinimumLatency(1000);
        }

        jobScheduler.schedule(builder.build());
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e(TAG, "onStartJob");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            startJob(this);
        }

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
