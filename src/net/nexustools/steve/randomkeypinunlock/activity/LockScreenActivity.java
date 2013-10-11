package net.nexustools.steve.randomkeypinunlock.activity;

import java.util.ArrayList;

import net.nexustools.steve.randomkeypinunlock.R;
import net.nexustools.steve.randomkeypinunlock.listener.LockScreenService;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

public class LockScreenActivity extends Activity {
	public static final int[] ZERO_TO_NINE = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	
	public RelativeLayout wrapperView;
	public Button okButton;
	public Button[] pinButtons;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_FULLSCREEN, PixelFormat.TRANSLUCENT);
		
		WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
		wrapperView = new RelativeLayout(getBaseContext());
		getWindow().setAttributes(params);
		
		View.inflate(this, R.layout.activity_lockscreen, wrapperView);
		wm.addView(wrapperView, params);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		okButton = (Button) wrapperView.findViewById(R.id.buttonOk);
		pinButtons = new Button[] {(Button) wrapperView.findViewById(R.id.button0), (Button) wrapperView.findViewById(R.id.button1), (Button) wrapperView.findViewById(R.id.button2), (Button) wrapperView.findViewById(R.id.button3), (Button) wrapperView.findViewById(R.id.button4), (Button) wrapperView.findViewById(R.id.button5), (Button) wrapperView.findViewById(R.id.button6), (Button) wrapperView.findViewById(R.id.button7), (Button) wrapperView.findViewById(R.id.button8), (Button) wrapperView.findViewById(R.id.button9)};
		OnClickListener listener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				randomizePINButtons();
			}
		};
		for(Button b : pinButtons)
			b.setOnClickListener(listener);
		okButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO: Compare/check password validity.
				finish();
			}
		});
		randomizePINButtons();
		startService(new Intent(this, LockScreenService.class));
	}
	
	public void randomizePINButtons() {
		ArrayList<Integer> numbersToAdd = new ArrayList<Integer>(ZERO_TO_NINE.length);
		for(int i : ZERO_TO_NINE)
			numbersToAdd.add(i);
		
		for(Button b : pinButtons)
			b.setText("" + numbersToAdd.remove((int) (Math.random() * numbersToAdd.size())));
	}
}
