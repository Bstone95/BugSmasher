package com.example.bugsmasher;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    MainView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Disable the title
        //requestWindowFeature (Window.FEATURE_NO_TITLE);  // use the styles.xml file to set no title bar
        // Make full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Start the view
        v = new MainView(this);
        setContentView(v);
    }

    @Override
    protected void onPause () {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("key_highscore", Assets.high_score);
        editor.commit();

        Assets.high_score=prefs.getInt("key_highscore",0);
        if (Assets.mp != null) {
            Assets.mp.stop();
            Assets.mp.release();
            Assets.mp = null;
        }
        super.onPause();
        v.pause();
    }

    @Override
    protected void onResume () {
        super.onResume();
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Assets.high_score = prefs.getInt("key_highscore", 0);
        v.resume();
    }
    @Override
    public void onBackPressed(){
        if (Assets.mp != null) {
            Assets.mp.stop();
            Assets.mp.release();
            Assets.mp = null;
        }
        super.onBackPressed();
    }
}
