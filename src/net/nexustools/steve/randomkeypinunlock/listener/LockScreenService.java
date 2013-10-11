package net.nexustools.steve.randomkeypinunlock.listener;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class LockScreenService extends Service {
	LockScreenReceiver lSR;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate() {
		((KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE)).newKeyguardLock("IN").disableKeyguard();
		IntentFilter filter = new IntentFilter();
		//filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		
		lSR = new LockScreenReceiver();
		registerReceiver(lSR, filter);
	}
	
	@Override
	public void onDestroy() {
		unregisterReceiver(lSR);
	}
}