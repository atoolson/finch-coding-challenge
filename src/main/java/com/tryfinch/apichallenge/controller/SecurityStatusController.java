package com.tryfinch.apichallenge.controller;

import com.tryfinch.apichallenge.resolver.securitystatus.SecurityStatus;
import com.tryfinch.apichallenge.resolver.securitystatus.SecurityStatusResolver;
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
@RequestMapping("/api/v1/security-status")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Vehicle Security", description = "Endpoints for information about security status of a vehicle")
public class SecurityStatusController {

    private final Set<SecurityStatusResolver> resolvers;

    @GetMapping("/{id}")
    @Operation(summary = "Get security status for a vehicle by vehicle id", description = "Returns a SecurityStatus " +
            "object for the security of the vehicle with the given id. If no vehicle is found with that id, a 404 is " +
            "returned.")
    public SecurityStatus getVehicleInfo(@PathVariable String id) {
        log.debug("a security status request was made for {}", id);

        // for each resolver go see if it supports this VIN
        // if so, get the info
        for (SecurityStatusResolver resolver : resolvers) {
            if (resolver.supports(id)) {
                return resolver.getSecurityStatus(id);
            }
        }

        // we can't support this id - thrown an error
        log.error("Could not retrieve security status for vehicle " + id);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not retrieve security status for vehicle " + id);
    }

}
