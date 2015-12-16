package uk.ac.gcu.mondayschild;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;


/**
 * Created by rla on 17/10/2014.
 */
public class mcRSSParser {

    private mcRSSDataItem RSSDataItem;
    LinkedList<mcRSSDataItem> dataItems;

    public void setRSSDataItem(String sItemData)
    {
        RSSDataItem.setItemTitle(sItemData);
        RSSDataItem.setItemDesc(sItemData);
        RSSDataItem.setItemLink(sItemData);
    }

    public mcRSSDataItem getRSSDataItem()
    {
        return this.RSSDataItem;
    }

    public mcRSSParser()
    {
        this.RSSDataItem =  new mcRSSDataItem();
        setRSSDataItem(null);
    }

    public void parseRSSDataItem(XmlPullParser theParser, int theEventType)
    {
       dataItems = new LinkedList<>();
        mcRSSDataItem dataItem;
        int arrayIndex = 0;
        try
        {
            Log.e("1","IO error during parsing");
            while (theEventType != XmlPullParser.END_DOCUMENT)
            {
                // Found a start tag
                if(theEventType == XmlPullParser.START_TAG)
                {
                    dataItem = new mcRSSDataItem();
                    // Check which Tag has been found
                    if (theParser.getName().equalsIgnoreCase("title"))
                    {

                        // Now just get the associated text
                        String temp = theParser.nextText();
                        // store data in class
                       dataItem.setItemTitle(temp);
                        Log.i("Title: ", dataItem.getItemTitle());
                    }
                    else
                        // Check which Tag we have
                        if (theParser.getName().equalsIgnoreCase("link"))
                        {
                            // Now just get the associated text
                            String temp = theParser.nextText();
                            // store data in class
                           dataItem.setItemLink(temp);
                           // Log.i("Link : ", temp);
                            Log.i("Link: ", dataItem.getItemLink());
                        }
                        else

                            // Check which Tag we have
                            if (theParser.getName().equalsIgnoreCase("description"))
                            {
                                 // Now just get the associated text
                                 String temp = theParser.nextText();
                                 // store data in class
                                dataItem.setItemDesc(temp);
                                Log.i("Description : ", temp);

                                   dataItems.add(arrayIndex, dataItem);

                               arrayIndex++;

                                Log.i("LinkedList size: ", String.valueOf(dataItems.size()) + " || Array index: " + String.valueOf(arrayIndex));

                            }
                }

                // Get the next event
                theEventType = theParser.next();

            } // End of while
        }
        catch (XmlPullParserException parserExp1)
        {
            Log.e("MyTag","Parsing error" + parserExp1.toString());
        }

        catch (IOException parserExp1)
        {
            Log.e("MyTag","IO error during parsing");
        }
    }

    public void parseRSSData(String RSSItemsToParse) throws MalformedURLException {
        URL rssURL = new URL(RSSItemsToParse);
        InputStream rssInputStream;
        try
        {
            XmlPullParserFactory parseRSSfactory = XmlPullParserFactory.newInstance();
            parseRSSfactory.setNamespaceAware(true);
            XmlPullParser RSSxmlPP = parseRSSfactory.newPullParser();
            String xmlRSS = getStringFromInputStream(getInputStream(rssURL), "UTF-8");
            RSSxmlPP.setInput(new StringReader(xmlRSS));
            int eventType = RSSxmlPP.getEventType();

            parseRSSDataItem(RSSxmlPP,eventType);

        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }

        Log.e("MyTag","End document");
    }

    public InputStream getInputStream(URL url) throws IOException
    {
            return url.openConnection().getInputStream();
    }

    public static String getStringFromInputStream(InputStream stream, String charsetName) throws IOException
    {
        int n = 0;
        char[] buffer = new char[1024 * 4];
        InputStreamReader reader = new InputStreamReader(stream, charsetName);
        StringWriter writer = new StringWriter();
        while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
        return writer.toString();
    }
}
