package com.tryfinch.apichallenge.resolver.securitystatus.gm;

import com.tryfinch.apichallenge.RestClient;
import com.tryfinch.apichallenge.resolver.securitystatus.SecurityStatus;
import com.tryfinch.apichallenge.resolver.securitystatus.SecurityStatusResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GMSecurityStatusResolver implements SecurityStatusResolver {

    private final RestClient restClient;

    @Override
    public boolean supports(String id) {
        return id.equals("1234") || id.equals("1235");
    }

    @Override
    public SecurityStatus getSecurityStatus(String id) {
        if (!supports(id)) {
            throw new IllegalArgumentException("Getting the security status for " + id + " is not supported by " + getClass().getSimpleName());
        }

        GMSecurityStatusRequest request = new GMSecurityStatusRequest();
        request.setId(id);

        ResponseEntity<GMSecurityStatusResponse> response = restClient.postForEntity("http://gmapi.azurewebsites.net/getSecurityStatusService", request, GMSecurityStatusResponse.class);


        return response.getBody().toSecurityStatus();
    }


}
