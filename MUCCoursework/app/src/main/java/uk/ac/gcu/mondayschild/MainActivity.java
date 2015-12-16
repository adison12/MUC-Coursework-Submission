package uk.ac.gcu.mondayschild;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.util.LinkedList;


//import android.support.v4.app.FragmentManager;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    DatePicker dpBDay; // Lab 1
    Button submitBtn; // Lab 1
    mcSaveData mcSDPrefs; // Lab 2
    SharedPreferences mySharedPrefs; // Lab 2
    FragmentManager fmAboutDialogue; // Lab 2
    String sOutputMsg; // Lab 3


    Spinner spinner; //spinner for choosing between City/Post Code/County
    private mcRSSDataItemsAdapter adapter; //list adapter for our fdList variable
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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  //Lab 6
        setContentView(R.layout.activity_main); // app main UI screen


        setUpSpinner();
        // Create an instance of the datePicker Object for the Birthday
        //dpBDay = (DatePicker) findViewById(R.id.datePickerBDay);
        //dpBDay.setCalendarViewShown(false);
        // Setup, access and listen for the submit button
        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);
        // Create  Default preferences for the app.
        mySharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        mcSDPrefs  = new mcSaveData(mySharedPrefs);
        mcSDPrefs.setDefaultPrefs();

        fmAboutDialogue = this.getFragmentManager();

        //userStarSignInfo = new mcStarSignsInfo();   // Lab 4


    }

    @Override
    public void onClick(View view) {



        // Create an instance of the MondaysChild Class
        //mondaysChild mcYourDay = new mondaysChild(dpBDay.getDayOfMonth(),dpBDay.getMonth(),dpBDay.getYear());
        // Create an instance of the astrology Class
        //astrology usersStarSign = new astrology(dpBDay.getDayOfMonth(),dpBDay.getMonth()+1); // Lab 3

        // Create database handler instance
       // mcStarSignsInfoDBMgr dbStarSignMgr = new mcStarSignsInfoDBMgr(this,"starsigns.s3db",null,1); // Lab 4
       // try {
       //     dbStarSignMgr.dbCreate();
       // } catch (IOException e) {
       //     e.printStackTrace();
      //  }

        // Retrieve Star Sign Info
       // userStarSignInfo = dbStarSignMgr.findStarSign(usersStarSign.getsStarSign()); // Lab 4

        // SAve preferences
      //  mcSDPrefs.savePreferences("mc_DOW", mcYourDay.getiDOW());
      //  mcSDPrefs.savePreferences("mc_Month", mcYourDay.getiMonth());
     //   mcSDPrefs.savePreferences("mc_DayBorn", mcYourDay.getsDOW());
     //   mcSDPrefs.savePreferences("mc_StarSign", usersStarSign.getsStarSign()); // Lab 3

        //Starting a new Intent
        Intent mcOutput_Screen = new Intent(getApplicationContext(), mcOutputScreen.class);

        //Send data to the new Activity
        /* ========================================================================================
            Previously used in Lab 3
        sOutputMsg = mcYourDay.getsOutputMsg() + "\nYour Star Sign is " + usersStarSign.getsStarSign(); // Lab 3
        mcOutput_Screen.putExtra("mcOutputMsg", sOutputMsg); //Lab 3
        mcOutput_Screen.putExtra("mcStarSign", usersStarSign.getsStarSign()); // Lab 3
         */


        // Send serialised Object to new activity for display - Lab 4
       mcOutput_Screen.putExtra("starSignInfo", "asas1"); // Lab 4
        //sOutputMsg = mcYourDay.getsOutputMsg(); // Lab 5
       mcOutput_Screen.putExtra("mcOutputMsg", "asas2"); //Lab 5

        // Log the output data
        //Log.e("n", mcYourDay.getsOutputMsg());




        // Start the new Activity
        startActivity(mcOutput_Screen);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mc_menu, menu);
        return true;
    }

    //@TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mBio: // Lab 6
                Intent mcBioDraw = new Intent(this, mcBioActivity.class);
                this.startActivity(mcBioDraw);
                return true;
            case R.id.mQuit:
                finish();
                return true;
            case R.id.mAbout:
                // About Dialogue;
                DialogFragment mcAboutDlg = new mcAboutDialogue();
                mcAboutDlg.show(fmAboutDialogue, "mc_About_Dlg");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void setUpSpinner()
    {
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.news_type_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    //function set up a list adapter for the listview, inflates listview with contents of fdList
    public void setUpListAdapter()
    {
      // adapter = new mcRSSDataItemsAdapter(this, fdList);
         //Attach the adapter to a ListView
        //android.widget.ListView listView = (ListView) findViewById(R.id.listView);
       //listView.setAdapter(adapter);

        //Log.i("setUpListAdapter()", "adapter initialised and set. getCount(): " + adapter.getCount());
    }

    //function to determine appropriate output depending on spinner selection
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {

    }

    //function determining what happens if nothing is selected in the spinner
    public void onNothingSelected(AdapterView<?> parent) {
        Log.i("onNothingSelected()", "Nothing selected in the spinner.");

    }

    public class mcRSSDataItemsAdapter extends ArrayAdapter<mcRSSDataItem> {
        public mcRSSDataItemsAdapter(Context context, LinkedList<mcRSSDataItem> mcRSSDataItems) {
            super(context, 0, mcRSSDataItems);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            mcRSSDataItem mcRSSDataItem = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listviewlayout, parent, false);
            }

          //  TextView fuelType = (TextView) convertView.findViewById(R.id.fuelType);
         //   TextView fuelHighest = (TextView) convertView.findViewById(R.id.fuelHighest);
          //  TextView fuelAverage = (TextView) convertView.findViewById(R.id.fuelAverage);
          //  TextView fuelLowest = (TextView) convertView.findViewById(R.id.fuelLowest);

          //  fuelType.setText(mcRSSDataItem.getFuelType());
          //  fuelHighest.setText("Highest units cost: " + Float.toString(mcRSSDataItem.getHighestUnits()) + "p");
          //  fuelAverage.setText("Average units cost: " + Float.toString(mcRSSDataItem.getAverageUnits()) + "p");
          //  fuelLowest.setText("Lowest units cost: " + Float.toString(mcRSSDataItem.getLowestUnits()) + "p");


            return convertView;
        }
    }

}
