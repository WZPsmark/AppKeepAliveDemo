package com.sk.appkeepalivedemo.account;

import android.accounts.Account;
import android.app.Service;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Created by smark on 2020/5/13.
 * 邮箱：smarkwzp@163.com
 */
public class SyncService extends Service {
    private SyncAdapter mSyncAdapter;

    private static final String TAG = "SyncService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mSyncAdapter.getSyncAdapterBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSyncAdapter = new SyncAdapter(getApplicationContext(), true);
    }

    public static class SyncAdapter extends AbstractThreadedSyncAdapter {

        public SyncAdapter(Context context, boolean autoInitialize) {
            super(context, autoInitialize);
        }

        @Override
        public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
            Log.e(TAG, "同步账户");
            //与互联网 或者 本地数据库同步账户
        }
    }
}
