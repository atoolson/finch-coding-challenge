package com.tryfinch.apichallenge.resolver.vehicleinfo.gm;

import com.tryfinch.apichallenge.RestClient;
import com.tryfinch.apichallenge.resolver.vehicleinfo.VehicleInfo;
import com.tryfinch.apichallenge.resolver.vehicleinfo.VehicleInfoResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GMVehicleInfoResolver implements VehicleInfoResolver {

    private final RestClient restClient;

    @Override
    public boolean supports(String vin) {
        // VINs starting with "1G" are manufactured by GM
        // see https://en.wikipedia.org/wiki/Vehicle_identification_number#List_of_common_WMI
        // since the challenge doesn't conform to the WMI standard, let's just hardcode this
        // return vin.startsWith("1G");
        return "1234".equals(vin) || "1235".equals(vin);
    }

    @Override
    public VehicleInfo getVehicleInfo(String id) {
        if (!supports(id)) {
            throw new IllegalArgumentException("Getting the vehicle info for " + id + " is not supported by " + getClass().getSimpleName());
        }

        GMVehicleInfoRequest request = new GMVehicleInfoRequest();
        request.setId(id);

        ResponseEntity<GMVehicleInfoResponse> response = restClient.postForEntity("http://gmapi.azurewebsites.net/getVehicleInfoService", request, GMVehicleInfoResponse.class);

        return response.getBody().toVehicleInfo();
    }
}
