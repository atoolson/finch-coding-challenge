package com.tryfinch.apichallenge.resolver.vehicleinfo.tesla;

import com.tryfinch.apichallenge.resolver.vehicleinfo.VehicleInfo;
import com.tryfinch.apichallenge.resolver.vehicleinfo.VehicleInfoResolver;

/**
 * This class is included only as an example of how the API is extensible for other manufacturers.
 */
public class TeslaVehicleInfoResolver implements VehicleInfoResolver {
    @Override
    public boolean supports(String id) {
        return false;
    }

    @Override
    public VehicleInfo getVehicleInfo(String id) {
        return null;
    }
}
