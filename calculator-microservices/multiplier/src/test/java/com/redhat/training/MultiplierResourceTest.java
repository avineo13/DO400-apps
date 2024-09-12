package com.redhat.training;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.redhat.training.service.SolverService;

import javax.ws.rs.WebApplicationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiplierResourceTest {
    
    SolverService solverService;
    MultiplierResource multiplierResource;

    @BeforeEach
    public void setup() {
        solverService = mock(SolverService.class);
        multiplierResource = new MultiplierResource(solverService);
    }

    @Test
    public void testMultiplicationOfPositiveValues() {

        final String val1 = "2";
        final String val2 = "3";

        when(solverService.solve(anyString()))
            .thenAnswer(invocation -> Float.valueOf(invocation.getArgument(0)));

        assertEquals(Float.valueOf("6"), multiplierResource.multiply(val1, val2));
    }

    @Test
    public void testMultiplicationOfPositiveAndNegativeValues() {

        final String val1 = "2";
        final String val2 = "-3";

        when(solverService.solve(anyString()))
            .thenAnswer(invocation -> Float.valueOf(invocation.getArgument(0)));

        assertEquals(Float.valueOf("-6"), multiplierResource.multiply(val1, val2));
    }

    @Test
    public void testMultiplicationOfInvalidValues() {

        final String val1 = "a";
        final String val2 = "-b";

        when(solverService.solve(anyString()))
            .thenThrow(new WebApplicationException("invalid-value"));

        final WebApplicationException ex = assertThrows(WebApplicationException.class, () -> {
            multiplierResource.multiply(val1, val2);
        });

        assertEquals("invalid-value", ex.getMessage());
    }
}
