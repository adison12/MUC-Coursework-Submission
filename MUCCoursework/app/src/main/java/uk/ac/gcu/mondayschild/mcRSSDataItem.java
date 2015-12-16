package uk.ac.gcu.mondayschild;

import java.io.Serializable;

/**
 * Created by rla on 17/10/2014.
 */
public class mcRSSDataItem implements Serializable {

// *********************************************
// Declare variables etc.
// *********************************************

    private String itemTitle;
    private String itemDesc;
    private String itemLink;

// *********************************************
// Declare getters and setters etc.
// *********************************************

    public String getItemTitle()
    {
        return this.itemTitle;
    }

    public void setItemTitle(String sItemTitle)
    {

        this.itemTitle = sItemTitle;
    }

    public String getItemDesc()
    {
        return this.itemDesc;
    }

    public void setItemDesc(String sItemDesc)
    {
        this.itemDesc = sItemDesc;
    }

    public String getItemLink()
    {
        return this.itemLink;
    }

    public void setItemLink(String sItemLink)
    {
        this.itemLink = sItemLink;
    }

// **************************************************
// Declare constructor.
// **************************************************

    public mcRSSDataItem()
    {
        this.itemTitle = "";
        this.itemDesc = "";
        this.itemLink = "";
    }

    @Override
    public String toString() {
        String starSignRSSHoroscopeData;
        starSignRSSHoroscopeData = "mcRSSDataItem [itemTitle=" + itemTitle;
        starSignRSSHoroscopeData = ", itemDesc=" + itemDesc;
        starSignRSSHoroscopeData = ", itemLink=" + itemLink +"]";
        return starSignRSSHoroscopeData;
    }

}
