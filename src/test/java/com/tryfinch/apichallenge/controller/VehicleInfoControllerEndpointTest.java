package com.tryfinch.apichallenge.controller;

import com.tryfinch.apichallenge.ApiChallengeApplication;
import com.tryfinch.apichallenge.resolver.vehicleinfo.VehicleInfo;
import com.tryfinch.apichallenge.resolver.vehicleinfo.gm.GMVehicleInfoResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApiChallengeApplication.class })
@WebMvcTest(controllers = VehicleInfoController.class)
@AutoConfigureWebClient
class VehicleInfoControllerEndpointTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GMVehicleInfoResolver gmVehicleInfoResolver;

    @Test
    void testEndpoint200() throws Exception {
        VehicleInfo info = new VehicleInfo();
        info.setColor("some awesome color");
        given(gmVehicleInfoResolver.supports("gm1")).willReturn(true);
        given(gmVehicleInfoResolver.getVehicleInfo("gm1")).willReturn(info);

        mvc.perform(get("/api/v1/vehicle-info/gm1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.color", is(info.getColor())));
    }

    @Test
    void testEndpoint404() throws Exception {
        mvc.perform(get("/api/v1/vehicle-info/lamborghini1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }
}