package com.tryfinch.apichallenge.resolver.vehicleinfo.gm;

import com.tryfinch.apichallenge.resolver.vehicleinfo.VehicleInfo;
import com.tryfinch.apichallenge.resolver.vehicleinfo.VehicleInfoResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class GMVehicleInfoResolver implements VehicleInfoResolver {

    private final RestTemplate restTemplate;

    @Override
    public boolean supports(String vin) {
        // VINs starting with "1G" are manufactured by GM
        // see https://en.wikipedia.org/wiki/Vehicle_identification_number#List_of_common_WMI
        // since the challenge doesn't conform to the WMI standard, let's just hardcode this
        // return vin.startsWith("1G");
        return "1234".equalsIgnoreCase(vin) || "1235".equalsIgnoreCase(vin);
    }

    @Override
    public VehicleInfo getVehicleInfo(String id) {
        if (!supports(id)) {
            throw new IllegalArgumentException("Getting the vehicle info for " + id + " is not supported by " + getClass().getSimpleName());
        }

        GMVehicleInfoRequest request = new GMVehicleInfoRequest();
        request.setId(id);

        // TODO retry
        ResponseEntity<GMVehicleInfoResponse> response = restTemplate.postForEntity("http://gmapi.azurewebsites.net/getVehicleInfoService", request, GMVehicleInfoResponse.class);

        // TODO handle 429s
        // TODO handle 500s
        // TODO handle nulls

        return response.getBody().toVehicleInfo();
    }
}
