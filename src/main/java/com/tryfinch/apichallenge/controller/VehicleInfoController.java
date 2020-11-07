package com.tryfinch.apichallenge.controller;

import java.util.Set;

import com.tryfinch.apichallenge.resolver.vehicleinfo.VehicleInfo;
import com.tryfinch.apichallenge.resolver.vehicleinfo.VehicleInfoResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for accepting web requests for vehicle info. It handles the request by asking all of the known
 * {@link VehicleInfoResolver} resolvers if it can support this VIN, and if so, asks it to retrieve the data.
 */
@RestController
@RequestMapping("/api/v1/vehicle-info")
@RequiredArgsConstructor
@Slf4j
public class VehicleInfoController {

    private final Set<VehicleInfoResolver> resolvers;

    @GetMapping("/{vin}")
    public VehicleInfo getVehicleInfo(@PathVariable String id) {
        log.debug("a vehicle info request was made for {}", id);

        // for each resolver go see if it supports this VIN
        // if so, get the info
        for (VehicleInfoResolver resolver : resolvers) {
            if (resolver.supports(id)) {
                return resolver.getVehicleInfo(id);
            }
        }

        // we can't support this VIN - thrown an error
        // TODO map this to a HTTP status code
        throw new IllegalArgumentException("Cannot provide vehicle information for " + id);
    }

}
