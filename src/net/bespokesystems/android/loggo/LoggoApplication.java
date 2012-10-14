package net.bespokesystems.android.loggo;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * An application object to manage shared preferences centrally.
 * @author G. Hussain Chinoy
 *
 */
public class LoggoApplication extends Application {

	public static String KEY_LOGGED_IN = "KEY_LOGGED_IN";
	
	private static String TAG = "LoggoApplication";
	private static LoggoApplication singleton;
	private SharedPreferences mPrefs;
	
	public LoggoApplication getInstance() {
		return singleton;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		singleton = this;
	}
	
	/**
	 * Retrieves the shared preference state of whether the user is logged in.
	 * @return
	 */
	public boolean isLoggedIn() {
		boolean loggedIn = mPrefs.getBoolean(KEY_LOGGED_IN, false);
		Log.d(TAG, "Retreiving login state: " + loggedIn);
		return loggedIn;
	}
	
	/**
	 * Sets the logged in state
	 * @param isLoggedIn
	 */
	public void setIsLoggedIn(boolean isLoggedIn) {
		Log.d(TAG, "Login state set to: " + isLoggedIn);
		
		Editor prefsEditor = mPrefs.edit();
		prefsEditor.putBoolean(KEY_LOGGED_IN, isLoggedIn);
		prefsEditor.commit();
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
		
		// when app is terminated, log out
		setIsLoggedIn(false);
	}
	
}
