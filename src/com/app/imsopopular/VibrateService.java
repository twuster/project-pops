package com.app.imsopopular;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;

public class VibrateService extends Service{
	AlarmManager alarm;
	Intent mIntent;
	PendingIntent pIntent;

	@Override
	public void onCreate(){
		super.onCreate();
		alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		// Start every 30 seconds
		mIntent = new Intent(this, VibrateService.class);
		pIntent = PendingIntent.getService(this, 0, mIntent, 0);
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), MainActivity.time*1000, pIntent);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
		if(MainActivity.stopped == true){
			stopService(intent);
			alarm.cancel(pIntent);
		}
		else{
			Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);  
			long[] pattern = {0,200,200,200};
			vibrator.vibrate(pattern,-1);
			Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
			r.play();
		//	Log.d("VibrateService", "SHOULD BE VIBRATING");
		}
		return Service.START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}


}