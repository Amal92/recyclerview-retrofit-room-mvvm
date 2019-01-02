package com.amp.sample_travel.Models.Response;

import com.amp.sample_travel.Models.LocationData;

import java.util.List;

/**
 * Created by amal on 02/01/19.
 */

public class ApiResponse {
    private List<LocationData> locations;
    private String cust_name;

    public List<LocationData> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationData> locations) {
        this.locations = locations;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }
}
