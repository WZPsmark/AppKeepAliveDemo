# AppKeepAliveDemo
Android 包含大多数App进程保活方案及代码整理接入即可，使用简单

项目简介：
项目包含  1、1像素activity保活方案，代码见activity1包下，
            使用方式:
            KeepManager.getInstance().registerKeep(this);
			
		  2、前台服务保活方案，代码见service下ForegroundService，
		  使用方式:
		   startService(new Intent(this, ForegroundService.class));
		   
		  3、粘性服务保活策略，代码见service下StickyService
		  使用方式：
		   startService(new Intent(this, StickyService.class));
		   
		  4、JobScheduler进行保活 代码见jobscheduler包下AliveJobService
		  使用方式：
		   AliveJobService.startJob(this);
		   
		  5、账户拉活 代码见account包下，
		  使用方式：
		   AccountHelper.addAccount(this);
           AccountHelper.autoSync();
		   
		  6、WorkManager后台启动JobScheduler进行保活，代码见workmanager包下
		  使用方式：
		   OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest
                .Builder(KeepLiveWork.class)
                .setInitialDelay(10, TimeUnit.SECONDS)
                .build();

           WorkManager.getInstance(this).enqueue(oneTimeWorkRequest);
		   
		上述权限及声明详见项目AndroidManifest.xml
		
		
     还有双进程守护方案 利用aidl binder通讯 通过JobSchedule启动，
	 此方法在8.0以上就不好用了，系统会杀掉进程组，所以并没有在此项目中列出

     当然高端用户体量大的直接找厂商要一个白名单，也不用累死累活的做保活了，
     低端用户上述方法任选一种或多种保活，基本能保证大多数手机上的保活了
	 
注意:如果觉得此项目对你有帮助的话，手抖一下帮忙点个star吧    
     
我不授渔，我只授鱼
		 

