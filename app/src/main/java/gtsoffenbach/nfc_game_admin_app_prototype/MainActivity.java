package gtsoffenbach.nfc_game_admin_app_prototype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class MainActivity extends ActionBarActivity {



    public interface ActivityLifeCycleListener{
        public void onActivityResume();
        public void onActivityPause();
        public void onActivityNewIntent(Intent intent);

    }

    private ActivityLifeCycleListener activityLifeCycleListener = null;


    
   // public static NFCFramework framework;
    private static  nfcManager nfcManager;
    private CharSequence mTitle;
    private TagItemMagazine tagItemMagazine;
    public static AlertDialogAnimation dialogAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, FragmentOverview.newInstance(0))
                .commit();
        mTitle = getTitle();



        this.tagItemMagazine = new TagItemMagazine();
       // framework = new NFCFramework(this);
        nfcManager = new nfcManager(this);
    }

    public TagItemMagazine getTagItemMagazine(){
        return this.tagItemMagazine;
    }

    public static nfcManager getNfcManager(){
        return nfcManager;
    }

   




    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    public void setActivityLifeCycleListener(ActivityLifeCycleListener value){
        this.activityLifeCycleListener = value;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPause() {
        super.onPause();
       /* if (framework != null) {
            if (framework.checkNFC()) {
                framework.uninstallService();
            }
        }*/

        if(this.activityLifeCycleListener!=null){
            this.activityLifeCycleListener.onActivityPause();
        }
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        /*if (framework != null) {
            framework.resolveIntent(getIntent());
        }*/

        if(this.activityLifeCycleListener!=null){
            this.activityLifeCycleListener.onActivityNewIntent(intent);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        setIntent(new Intent());

       /* if (framework != null) {
            if (framework.checkNFC()) {
                framework.installService();
            }
        } */

        if(this.activityLifeCycleListener!=null){
            this.activityLifeCycleListener.onActivityResume();
        }
    }


}
