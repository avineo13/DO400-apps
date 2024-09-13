package com.redhat.shipping;

import java.util.HashMap;
import java.util.Map;

public class ShippingCalculator {

    public static final Map<String, Integer> REGIONS = new HashMap<>();

    static {

        REGIONS.put("NA", 100);
        REGIONS.put("LATAM", 200);
        REGIONS.put("EMEA", 300);
        REGIONS.put("APAC", 400);
    }

    public int costForRegion(String name) throws RegionNotFoundException {

        if (REGIONS.containsKey(name)) {
            return REGIONS.get(name);
        }
        throw new RegionNotFoundException();
    }   
}
