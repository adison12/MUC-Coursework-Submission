package uk.ac.gcu.mondayschild;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by rla on 16/09/2014.
 */
public class mcSaveDataOutput extends Activity implements View.OnClickListener {

    SharedPreferences mcSharedPrefs;
    Button btnBack;
    TextView mcSDODOW;



    // ****************************************************************
    // onCreate.
    // An event handler called when the app is first created.
    // Usually contains all initializations and setting up for the app.
    // Generally the place to show the app main UI screen.
    // ****************************************************************
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mc_display_shared_prefs); // app main UI screen
        // Setup, access and listen for the pick date button
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        // create text view for output
        mcSDODOW = (TextView) findViewById(R.id.tvDOW);
        // Load any saved preferences
        mcSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        loadSavedPreferences();
        Log.e("n","SDOutput msg");
    }

    private void loadSavedPreferences() {
        mcSDODOW.setText(mcSDODOW.getText());

    }

    public void onClick(View view) {
        setResult(Activity.RESULT_OK);
        finish();
    }


}
