package com.tryfinch.apichallenge.controller;

import com.tryfinch.apichallenge.resolver.securitystatus.SecurityStatusResolver;
import com.tryfinch.apichallenge.resolver.vehicleinfo.VehicleInfoResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class VehicleInfoControllerTest {

    VehicleInfoController controller;
    VehicleInfoResolver gm;
    VehicleInfoResolver tesla;

    @BeforeEach
    void init() {
        gm = mock(VehicleInfoResolver.class);
        when(gm.supports("gm1")).thenReturn(true);

        tesla = mock(VehicleInfoResolver.class);
        when(tesla.supports("tesla1")).thenReturn(true);

        controller = new VehicleInfoController(Set.of(gm, tesla));
    }

    /**
     * Make sure a request for "gm1" gets routed to gm and not tesla
     * And make sure a request for "tesla1" gets routed to tesla and not gm
     */
    @Test
    void getVehicleInfo() {
        // make sure gm gets the request
        controller.getVehicleInfo("gm1");
        verify(gm, times(1)).getVehicleInfo("gm1");
        verify(tesla, never()).getVehicleInfo("gm1");

        // make sure tesla gets the request
        controller.getVehicleInfo("tesla1");
        verify(gm, times(1)).getVehicleInfo("gm1"); // this is from last time
        verify(tesla, times(1)).getVehicleInfo("tesla1");
    }

    @Test
    void getVehicleInfoUnsupported() {
        // make sure an unsupported request throws a 400
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                controller.getVehicleInfo("lamborghini1")
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }
}