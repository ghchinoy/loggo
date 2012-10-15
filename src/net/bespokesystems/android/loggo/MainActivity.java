package net.bespokesystems.android.loggo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static String TAG = "MainActivity";
	
	private TextView loggedInStatus;
	private LoggoApplication app;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        // Using an Application object to set the shared preferences and provide
        app = (LoggoApplication)getApplicationContext();
        
    }

    @Override
    protected void onStart() {
    	super.onStart();
    	
    	checkLogin();
        
        setContentView(R.layout.activity_main);
        
        loggedInStatus = (TextView)findViewById(R.id.loggedInStatusView);
        loggedInStatus.setText( "Welcome, you are logged in.");
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /**
     * Checks with the application to determine the login state
     * Redirects to the Login Activity
     */
    private void checkLogin() {
    	if (!app.isLoggedIn()) { // Not logged in
    		Log.d(TAG, "Not logged in. Redirecting to Login activity.");
    		// fire an intent to login
    		Intent i = new Intent(this, LoginActivity.class);
	    	i.putExtra("caller", "MainActivity");
	    	startActivity(i);
    	} else {
    		Log.d(TAG, "Logged in.");
    	}
    }
    
    /**
     * React to menu items.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case R.id.menu_logout:
    		app.setIsLoggedIn(false);
    		checkLogin();
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
}
