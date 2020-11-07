package com.tryfinch.apichallenge.resolver.securitystatus.gm;

import com.tryfinch.apichallenge.resolver.securitystatus.SecurityStatus;
import com.tryfinch.apichallenge.resolver.securitystatus.SecurityStatusResolver;
import com.tryfinch.apichallenge.resolver.vehicleinfo.gm.GMVehicleInfoRequest;
import com.tryfinch.apichallenge.resolver.vehicleinfo.gm.GMVehicleInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class GMSecurityStatusResolver implements SecurityStatusResolver {

    private final RestTemplate restTemplate;

    @Override
    public boolean supports(String id) {
        return id.equalsIgnoreCase("1234") || id.equalsIgnoreCase("1235");
    }

    @Override
    public SecurityStatus getSecurityStatus(String id) {
        if (!supports(id)) {
            throw new IllegalArgumentException("Getting the security status for " + id + " is not supported by " + getClass().getSimpleName());
        }

        GMSecurityStatusRequest request = new GMSecurityStatusRequest();
        request.setId(id);

        // TODO retry
        ResponseEntity<GMSecurityStatusResponse> response = restTemplate.postForEntity("http://gmapi.azurewebsites.net/getSecurityStatusService", request, GMSecurityStatusResponse.class);


        return response.getBody().toSecurityStatus();
    }


}
