package com.tryfinch.apichallenge.controller;

import com.tryfinch.apichallenge.resolver.fuelbatterylevel.FuelBatteryLevel;
import com.tryfinch.apichallenge.resolver.fuelbatterylevel.FuelBatteryLevelResolver;
import com.tryfinch.apichallenge.resolver.vehicleinfo.VehicleInfoResolver;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

/**
 * Controller for accepting web requests for vehicle info. It handles the request by asking all of the known
 * {@link VehicleInfoResolver} resolvers if it can support this VIN, and if so, asks it to retrieve the data.
 */
@RestController
@RequestMapping("/api/v1/fuel-battery-status")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Fuel and Battery Status", description = "Endpoints for information about fuel and/or battery status of a vehicle")
public class FuelBatteryLevelController {

    private final Set<FuelBatteryLevelResolver> resolvers;

    @GetMapping("/{id}")
    @Operation(summary = "Get fuel/battery for a vehicle by vehicle id", description = "Returns a FuelBatteryLevel " +
            "object for the vehicle with the given id.")
    public FuelBatteryLevel getSecurityStatus(@PathVariable String id) {
        log.debug("a fuel/battery request was made for {}", id);

        // for each resolver go see if it supports this VIN
        // if so, get the info
        for (FuelBatteryLevelResolver resolver : resolvers) {
            if (resolver.supports(id)) {
                return resolver.getFuelBatteryLevel(id);
            }
        }

        // we can't support this id - thrown an error
        log.error("Could not retrieve fuel/battery for vehicle " + id);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not retrieve fuel/battery for vehicle " + id);
    }

}
