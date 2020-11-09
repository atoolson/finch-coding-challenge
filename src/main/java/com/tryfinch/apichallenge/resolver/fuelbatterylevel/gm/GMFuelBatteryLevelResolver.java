package com.tryfinch.apichallenge.resolver.fuelbatterylevel.gm;

import com.tryfinch.apichallenge.RestClient;
import com.tryfinch.apichallenge.resolver.fuelbatterylevel.FuelBatteryLevel;
import com.tryfinch.apichallenge.resolver.fuelbatterylevel.FuelBatteryLevelResolver;
import com.tryfinch.apichallenge.resolver.securitystatus.gm.GMSecurityStatusRequest;
import com.tryfinch.apichallenge.resolver.securitystatus.gm.GMSecurityStatusResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GMFuelBatteryLevelResolver implements FuelBatteryLevelResolver {

    private final RestClient restClient;

    @Override
    public boolean supports(String id) {
        return "1234".equals(id) || "1235".equals(id);
    }

    @Override
    public FuelBatteryLevel getFuelBatteryLevel(String id) {
        if (!supports(id)) {
            throw new IllegalArgumentException("Getting the fuel/battery levels for " + id + " is not supported by " + getClass().getSimpleName());
        }

        GMSecurityStatusRequest request = new GMSecurityStatusRequest();
        request.setId(id);

        ResponseEntity<GMFuelBatteryLevelResponse> response = restClient.postForEntity("http://gmapi.azurewebsites.net/getEnergyService", request, GMFuelBatteryLevelResponse.class);


        return response.getBody().toFuelBatteryLevel();
    }
}
