package com.web.controller.booking;

import java.util.ArrayList;
import java.util.List;

class SelectedScooters{

    private List<String> selected = new ArrayList<String>();

    public SelectedScooters(){}

    public List<String> getSelected() {
        return selected;
    }

    public void setSelected(List<String> selected) {
        this.selected = selected;
    }
}