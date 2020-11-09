package com.tryfinch.apichallenge.controller;

import com.tryfinch.apichallenge.resolver.vehicleinfo.VehicleInfo;
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
@RequestMapping("/api/v1/vehicle-info")
@Tag(name = "Vehicle Info", description = "Endpoints for information about vehicle information")
@RequiredArgsConstructor
@Slf4j
public class VehicleInfoController {

    private final Set<VehicleInfoResolver> resolvers;

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle info by id", description = "Returns a VehicleInfo object for the vehicle with the " +
            "given id.")
    public VehicleInfo getVehicleInfo(@PathVariable String id) {
        log.debug("a vehicle info request was made for {}", id);

        // for each resolver go see if it supports this VIN
        // if so, get the info
        for (VehicleInfoResolver resolver : resolvers) {
            if (resolver.supports(id)) {
                return resolver.getVehicleInfo(id);
            }
        }

        // we can't support this id - thrown an error
        log.error("Could not retrieve vehicle information for vehicle " + id);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not retrieve vehicle information for vehicle " + id);
    }

}
