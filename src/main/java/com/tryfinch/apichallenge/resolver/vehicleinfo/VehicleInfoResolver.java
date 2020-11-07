package com.tryfinch.apichallenge.resolver.vehicleinfo;

/**
 * Interface that defines the contract between our API and a manufacturer's API for getting vehicle info
 */
public interface VehicleInfoResolver {

    boolean supports(String vin);
    VehicleInfo getVehicleInfo(String id);
}
