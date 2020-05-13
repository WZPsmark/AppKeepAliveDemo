package com.sk.appkeepalivedemo;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.os.Bundle;

import com.sk.appkeepalivedemo.account.AccountHelper;
import com.sk.appkeepalivedemo.activity1.KeepManager;
import com.sk.appkeepalivedemo.jobscheduler.AliveJobService;
import com.sk.appkeepalivedemo.service.ForegroundService;
import com.sk.appkeepalivedemo.service.StickyService;
import com.sk.appkeepalivedemo.workmanager.KeepLiveWork;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1像素广播注册
        KeepManager.getInstance().registerKeep(this);

        //前台服务保活
        startService(new Intent(this, ForegroundService.class));

        //开启粘性服务进行保活
        startService(new Intent(this, StickyService.class));

        //使用JobScheduler进行保活
        AliveJobService.startJob(this);


        // 账户拉活
        AccountHelper.addAccount(this);
        AccountHelper.autoSync();


        //利用WorkManager后台启动JobScheduler进行保活
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest
                .Builder(KeepLiveWork.class)
                .setInitialDelay(10, TimeUnit.SECONDS)
                .build();

        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest);



        //还有双进程守护 利用aidl binder通讯 通过JobSchedule启动，此方法在8.0以上就不好用了，系统会杀掉进程组

        //当然高端用户体量大的直接找厂商要一个白名单，也不用累死累活的做保活了，
        // 低端用户上述方法任选一种或多种保活，基本能保证大多数手机上的保活了



    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeepManager.getInstance().unregisterKeep(this);
    }
}
