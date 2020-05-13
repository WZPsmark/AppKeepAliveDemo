package com.sk.appkeepalivedemo.activity1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

/**
 * Created by smark on 2020/5/13.
 * 邮箱：smarkwzp@163.com
 * 用于保活的1像素activity
 */
public class AliveActivity extends Activity {
    private static final String TAG = "AliveActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "AliveActivity启动");

        Window window = getWindow();
        window.setGravity(Gravity.START | Gravity.TOP);
        WindowManager.LayoutParams params = window.getAttributes();
        //宽高
        params.width = 1;
        params.height = 1;
        //设置位置
        params.x = 0;
        params.y = 0;
        window.setAttributes(params);

        KeepManager.getInstance().setKeep(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "AliveActivity关闭");
    }
}
