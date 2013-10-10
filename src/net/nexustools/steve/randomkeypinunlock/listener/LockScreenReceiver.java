package net.nexustools.steve.randomkeypinunlock.listener;

import net.nexustools.steve.randomkeypinunlock.activity.LockScreenActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
public class LockScreenReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("Received intent " + intent.getAction() + ".");
		if(intent.getAction().equals(Intent.ACTION_SCREEN_ON) || intent.getAction().equals(Intent.ACTION_SCREEN_OFF) || intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED))  {
			Intent lockIntent = new Intent(context, LockScreenActivity.class);
			lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(lockIntent);
		}
	}
}