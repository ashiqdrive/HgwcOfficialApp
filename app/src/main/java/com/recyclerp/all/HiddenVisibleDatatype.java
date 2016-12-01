package com.recyclerp.all;

/**
 * Created by ashiq on 04-Oct-16.
 */

public class HiddenVisibleDatatype {

    private String stringHidden;
    private String stringVisible;

    public HiddenVisibleDatatype() {
    }

    public HiddenVisibleDatatype(String stringHidden, String stringVisible) {
        setStringHidden(stringHidden);
        setStringVisible(stringVisible);
    }

    public HiddenVisibleDatatype(String stringVisible) {
        setStringVisible(stringVisible);
    }

    public String getStringHidden() {
        return stringHidden;
    }

    public void setStringHidden(String stringHidden) {
        this.stringHidden = stringHidden;
    }

    public String getStringVisible() {
        return stringVisible;
    }

    public void setStringVisible(String stringVisible) {
        this.stringVisible = stringVisible;
    }
}
