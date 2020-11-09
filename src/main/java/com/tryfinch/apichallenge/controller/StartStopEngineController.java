package com.tryfinch.apichallenge.controller;

import com.tryfinch.apichallenge.resolver.fuelbatterylevel.FuelBatteryLevel;
import com.tryfinch.apichallenge.resolver.fuelbatterylevel.FuelBatteryLevelResolver;
import com.tryfinch.apichallenge.resolver.startstopengine.StartStopEngineRequest;
import com.tryfinch.apichallenge.resolver.startstopengine.StartStopEngineResolver;
import com.tryfinch.apichallenge.resolver.startstopengine.StartStopEngineResponse;
import com.tryfinch.apichallenge.resolver.vehicleinfo.VehicleInfoResolver;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

/**
 * Controller for accepting web requests for starting and stopping a vehicle. It handles the request by asking all of the known
 * {@link StartStopEngineResolver} resolvers if it can support this VIN, and if so, asks it to retrieve the data.
 */
@RestController
@RequestMapping("/api/v1/start-stop-vehicle")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Start and Stop Vehicle", description = "Endpoints for starting and stopping a vehicle's engine")
public class StartStopEngineController {

    private final Set<StartStopEngineResolver> resolvers;

    @PostMapping("/")
    @Operation(summary = "Start or stop a vehicle by id", description = "Returns a StartStopEngineResponse " +
            "object for the vehicle with the given id.")
    public StartStopEngineResponse getSecurityStatus(@RequestBody StartStopEngineRequest request) {
        log.debug("a start/stop request was made for {}", request);

        // for each resolver go see if it supports this VIN
        // if so, get the info
        for (StartStopEngineResolver resolver : resolvers) {
            if (resolver.supports(request.getVehicleId())) {
                return resolver.startStopEngine(request.getVehicleId(), request.getCommand());
            }
        }

        // we can't support this id - thrown an error
        log.error("Could not perform {}. No resolver supports it.", request);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not " + request.getCommand() + " for vehicle " + request.getVehicleId());
    }

}
