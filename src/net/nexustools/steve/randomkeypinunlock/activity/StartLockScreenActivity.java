package net.nexustools.steve.randomkeypinunlock.activity;

import net.nexustools.steve.randomkeypinunlock.listener.LockScreenService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StartLockScreenActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startService(new Intent(this, LockScreenService.class));
		finish();
	}
}
