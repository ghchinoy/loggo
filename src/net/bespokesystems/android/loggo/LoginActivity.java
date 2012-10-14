package net.bespokesystems.android.loggo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener {

	private TextView usernameView;
	private TextView passwordView;
	private Button loginButton;
	
	private LoggoApplication app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		app = (LoggoApplication)getApplicationContext();
		
		usernameView = (TextView)findViewById(R.id.editText_username);
		passwordView = (TextView)findViewById(R.id.editText_password);
		loginButton = (Button)findViewById(R.id.login_button);
		loginButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		// handle the condition that username and password haven't been entered
		if (usernameView.getText().length() == 0 && passwordView.getText().length() == 0) {
			
		} else {
			// do verification of login information here
			
			// if login is valid, set valid & fire main intent
			app.setIsLoggedIn(true);
			
			Intent i = new Intent(this, MainActivity.class);
	    	i.putExtra("caller", "LoginActivity");
	    	startActivity(i); 
	    	finish(); // remove this activity from the stack
		}
		
	}
}
