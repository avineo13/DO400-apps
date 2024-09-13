package com.redhat.shipping;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ShippingCalculatorTest {
    
    @Test
    public void onNARegionCostIs100() throws Exception {

        // Given
        ShippingCalculator calculator  = new ShippingCalculator();

        // When
        int cost = calculator.costForRegion("NA");

        // Then
        assertEquals(100, cost);
    }
    
    @Test
    public void onLATAMRegionCostIs200() throws Exception {

        // Given
        ShippingCalculator calculator  = new ShippingCalculator();

        // When
        int cost = calculator.costForRegion("LATAM");

        // Then
        assertEquals(200, cost);
    }
    
    @Test
    public void onEMEARegionCostIs300() throws Exception {

        // Given
        ShippingCalculator calculator  = new ShippingCalculator();

        // When
        int cost = calculator.costForRegion("EMEA");

        // Then
        assertEquals(300, cost);
    }
    
    @Test
    public void onAPACRegionCostIs400() throws Exception {

        // Given
        ShippingCalculator calculator  = new ShippingCalculator();

        // When
        int cost = calculator.costForRegion("APAC");

        // Then
        assertEquals(400, cost);
    }
 
    @Test
    public void onNotSupportedRegionNotFoundExceptionisRaised() throws Exception {

        // Given
        ShippingCalculator calculator  = new ShippingCalculator();

        // When, Then
        RegionNotFoundException exception = assertThrows(RegionNotFoundException.class, () -> {
            calculator.costForRegion("Unknown Region");
        });
    }

}
