package com.tryfinch.apichallenge.resolver.vehicleinfo;

public interface VehicleInfoResolver {

    boolean supports(String vin);
    VehicleInfo getVehicleInfo(String vin);
}
