package net.nexustools.steve.randomkeypinunlock.activity;

import net.nexustools.steve.randomkeypinunlock.R;
import net.nexustools.steve.randomkeypinunlock.listener.LockScreenService;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;
import android.content.Intent;

public class LockScreenActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.TYPE_KEYGUARD | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_SECURE);
		setContentView(R.layout.activity_lockscreen);
		startService(new Intent(this, LockScreenService.class));
	}
}
