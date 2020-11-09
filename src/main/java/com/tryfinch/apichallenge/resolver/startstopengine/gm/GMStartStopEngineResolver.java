package com.tryfinch.apichallenge.resolver.startstopengine.gm;

import com.tryfinch.apichallenge.RestClient;
import com.tryfinch.apichallenge.resolver.startstopengine.StartStopEngineCommand;
import com.tryfinch.apichallenge.resolver.startstopengine.StartStopEngineResolver;
import com.tryfinch.apichallenge.resolver.startstopengine.StartStopEngineResponse;
import com.tryfinch.apichallenge.resolver.vehicleinfo.gm.GMVehicleInfoRequest;
import com.tryfinch.apichallenge.resolver.vehicleinfo.gm.GMVehicleInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GMStartStopEngineResolver implements StartStopEngineResolver {

    private final RestClient restClient;

    @Override
    public boolean supports(String id) {
        return "1234".equals(id) || "12345".equals(id);
    }

    @Override
    public StartStopEngineResponse startStopEngine(String id, StartStopEngineCommand command) {
        if (!supports(id)) {
            throw new IllegalArgumentException("Starting/stopping the vehicle info for " + id + " is not supported by " + getClass().getSimpleName());
        }

        GMStartStopEngineRequest request = new GMStartStopEngineRequest();
        request.setId(id);
        request.setCommand(GMStartStopEngineCommand.fromStartStopEngineCommand(command));

        ResponseEntity<GMStartStopEngineResponse> response = restClient.postForEntity("http://gmapi.azurewebsites.net/actionEngineService", request, GMStartStopEngineResponse.class);

        return response.getBody().toStartStopEngineResponse();
    }
}
