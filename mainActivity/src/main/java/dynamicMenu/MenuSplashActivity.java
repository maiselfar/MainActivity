package dynamicMenu;
 
import com.hmkcode.android.sign.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import globalVariables.GlobalVariable;

public class MenuSplashActivity extends Activity {
 
   private static String TAG = MenuSplashActivity.class.getName();
   private static long SLEEP_TIME = 2;    // Sleep for some time
 
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
 
      this.requestWindowFeature(Window.FEATURE_NO_TITLE);    // Removes title bar
      this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);    // Removes notification bar
 
      setContentView(R.layout.menu_page_splash);
 
      // Start timer and launch main activity
      IntentLauncher launcher = new IntentLauncher();
      launcher.start();
   }
 
   private class IntentLauncher extends Thread {
      @Override
      /**
       * Sleep for some time and than start new activity.
       */
      public void run() {
         try {
            // Sleeping
            Thread.sleep(SLEEP_TIME*1000);
         } catch (Exception e) {
            Log.e(TAG, e.getMessage());
         }
 
         // Start main activity
         Intent intent = new Intent(MenuSplashActivity.this, DynamicMenuFragmentActivity.class);
         intent.putExtra("tableNumber", GlobalVariable.getTableNumber());
         MenuSplashActivity.this.startActivity(intent);
         MenuSplashActivity.this.finish();
      }
   }
}
//433