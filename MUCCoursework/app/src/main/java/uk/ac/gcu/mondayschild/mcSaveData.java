package uk.ac.gcu.mondayschild;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;


/**
 * Created by rla on 16/09/2014.
 */
public class mcSaveData extends Activity {

 // *********************************************
// Declare variables etc.
// *********************************************

    SharedPreferences mcSharedPrefs;

    private String strName;



// *********************************************
// Declare getters and setters etc.
// *********************************************

    private void setName(String isDOW)
    {
        this.strName = isDOW;
    }

    public String getName()
    {
        return strName;
    }


// **************************************************
// Declare constructor and date manipulation methods.
// **************************************************

    public mcSaveData(SharedPreferences mcSDPrefs){
        setName("Asda");
        //setmcSDMonth(1);
        //setmcSDDayBorn("Sunday");
        try {
            this.mcSharedPrefs = mcSDPrefs;
        }
        catch (Exception e)
        {
            Log.e("n","Pref Manager is NULL" );
        }
        setDefaultPrefs();
    }

    public void savePreferences(String key, boolean value) {
        SharedPreferences.Editor editor = mcSharedPrefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void savePreferences(String key, String value) {
        SharedPreferences.Editor editor = mcSharedPrefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void savePreferences(String key, int value) {
        SharedPreferences.Editor editor = mcSharedPrefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void setDefaultPrefs(){
        savePreferences("mc_DOW", 1);
    }


}
