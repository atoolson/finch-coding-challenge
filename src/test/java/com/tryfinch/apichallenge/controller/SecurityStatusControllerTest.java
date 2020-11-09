package com.tryfinch.apichallenge.controller;

import com.tryfinch.apichallenge.resolver.securitystatus.SecurityStatusResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SecurityStatusControllerTest {

    SecurityStatusController controller;
    SecurityStatusResolver gm;
    SecurityStatusResolver tesla;

    @BeforeEach
    void init() {
        gm = mock(SecurityStatusResolver.class);
        when(gm.supports("gm1")).thenReturn(true);

        tesla = mock(SecurityStatusResolver.class);
        when(tesla.supports("tesla1")).thenReturn(true);

        controller = new SecurityStatusController(Set.of(gm, tesla));
    }

    /**
     * Make sure a request for "gm1" gets routed to gm and not tesla
     * And make sure a request for "tesla1" gets routed to tesla and not gm
     */
    @Test
    void getSecurityStatus() {
        // make sure gm gets the request
        controller.getSecurityStatus("gm1");
        verify(gm, times(1)).getSecurityStatus("gm1");
        verify(tesla, never()).getSecurityStatus("gm1");

        // make sure tesla gets the request
        controller.getSecurityStatus("tesla1");
        verify(gm, times(1)).getSecurityStatus("gm1"); // this is from last time
        verify(tesla, times(1)).getSecurityStatus("tesla1");
    }

    @Test
    void getSecurityStatusUnsupported() {
        // make sure an unsupported request throws a 400
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
            controller.getSecurityStatus("lamborghini1")
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }
}