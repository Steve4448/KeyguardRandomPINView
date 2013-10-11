package net.nexustools.steve.randomkeypinunlock.activity;

import java.util.ArrayList;

import net.nexustools.steve.randomkeypinunlock.R;
import net.nexustools.steve.randomkeypinunlock.listener.LockScreenService;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class LockScreenActivity extends Activity {
	public static final int[] ZERO_TO_NINE = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	public Button okButton;
	public Button[] pinButtons;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_lockscreen);
		okButton = (Button)findViewById(R.id.buttonOk);
		pinButtons = new Button[] {
				(Button)findViewById(R.id.button0),
				(Button)findViewById(R.id.button1),
				(Button)findViewById(R.id.button2),
				(Button)findViewById(R.id.button3),
				(Button)findViewById(R.id.button4),
				(Button)findViewById(R.id.button5),
				(Button)findViewById(R.id.button6),
				(Button)findViewById(R.id.button7),
				(Button)findViewById(R.id.button8),
				(Button)findViewById(R.id.button9)
		};
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
	            //TODO: Compare/check password validity.
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
			b.setText("" + numbersToAdd.remove((int)(Math.random() * numbersToAdd.size())));
	}
}
