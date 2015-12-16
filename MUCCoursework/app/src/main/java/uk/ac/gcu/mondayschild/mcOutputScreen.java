package uk.ac.gcu.mondayschild;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


/**
 * Created by rla on 11/09/2014.
 */
public class mcOutputScreen extends Activity implements View.OnClickListener {
    Button btnDatePick;
    Button btnShowSavedData;

    ImageView newsSign;  // Lab 3


    ArrayList<mcRSSDataItem> listDataItems;
    private dataItemAdapter adapter;


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
        setContentView(R.layout.mc_ouput_screen); // app main UI screen


        listDataItems = new ArrayList<mcRSSDataItem>();
        ListView listView = (ListView) findViewById(R.id.listView);

        dataItemAdapter adapter = new dataItemAdapter(this, listDataItems);

        listView.setAdapter(adapter);

        mcRSSDataItem userHoroscope = new mcRSSDataItem(); // Lab 5
        String RSSFeedURL = "http://www.telegraph.co.uk/technology/news/rss"; // Lab 5
        mcAsyncRSSParser rssAsyncParse = new mcAsyncRSSParser(this, RSSFeedURL);  // Lab 5
        try {
            userHoroscope = rssAsyncParse.execute("").get();   // Lab 5
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        TextView mytextview = (TextView) findViewById(R.id.text);
        mytextview.setText("News");
    }


    public void setUpListAdapter()
    {


        // Attach the adapter to a ListView


        //adapter = new ArrayAdapter<mcRSSDataItem>(this, listDataItems);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }



    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btnSavedData:
            {
                Intent mc_SavedDataOutput = new Intent(getApplicationContext(), mcSaveDataOutput.class);
                startActivity(mc_SavedDataOutput);
                break;
            }
        }
    }



    public class dataItemAdapter extends ArrayAdapter<mcRSSDataItem> {
        public dataItemAdapter(Context context, ArrayList<mcRSSDataItem> diAdapter) {
            super(context, 0, diAdapter);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            mcRSSDataItem theDataItem = getItem(position);
            Log.i("log" , "1");
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listviewlayout, parent, false);
            }

            Log.i("dataItemAd ", theDataItem.getItemTitle());
            TextView newsTitle = (TextView) convertView.findViewById(R.id.newsTitle);
            TextView newsLink = (TextView) convertView.findViewById(R.id.newsLink);
            TextView newsDescription = (TextView) convertView.findViewById(R.id.newsLink);

            newsTitle.setText(theDataItem.getItemTitle());
            newsTitle.setText(theDataItem.getItemDesc());
            newsTitle.setText(theDataItem.getItemLink());


            return convertView;
        }
    }

}
