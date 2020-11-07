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

@RestController
@RequestMapping("/api/v1/vehicle-info")
@RequiredArgsConstructor
@Slf4j
public class VehicleInfoController {

    private final Set<VehicleInfoResolver> resolvers;

    @GetMapping("/{vin}")
    public VehicleInfo getVehicleInfo(@PathVariable String vin) {
        log.debug("a vehicle info request was made for {}", vin);

        for (VehicleInfoResolver resolver : resolvers) {
            if (resolver.supports(vin)) {
                return resolver.getVehicleInfo(vin);
            }
        }

        throw new IllegalArgumentException("Cannot provide vehicle information for " + vin);
    }

}
