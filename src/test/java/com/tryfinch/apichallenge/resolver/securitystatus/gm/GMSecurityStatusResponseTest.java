package com.tryfinch.apichallenge.resolver.securitystatus.gm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GMSecurityStatusResponseTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void fromJson() throws JsonProcessingException {
        String json = "{\n" +
                "  \"service\": \"getSecurityStatus\",\n" +
                "  \"status\": \"200\",\n" +
                "  \"data\": {\n" +
                "    \"doors\": {\n" +
                "      \"type\": \"Array\",\n" +
                "      \"values\": [\n" +
                "        {\n" +
                "          \"location\": {\n" +
                "            \"type\": \"String\",\n" +
                "            \"value\": \"frontLeft\"\n" +
                "          },\n" +
                "          \"locked\": {\n" +
                "            \"type\": \"Boolean\",\n" +
                "            \"value\": \"False\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"location\": {\n" +
                "            \"type\": \"String\",\n" +
                "            \"value\": \"frontRight\"\n" +
                "          },\n" +
                "          \"locked\": {\n" +
                "            \"type\": \"Boolean\",\n" +
                "            \"value\": \"True\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"location\": {\n" +
                "            \"type\": \"String\",\n" +
                "            \"value\": \"backLeft\"\n" +
                "          },\n" +
                "          \"locked\": {\n" +
                "            \"type\": \"Boolean\",\n" +
                "            \"value\": \"False\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"location\": {\n" +
                "            \"type\": \"String\",\n" +
                "            \"value\": \"backRight\"\n" +
                "          },\n" +
                "          \"locked\": {\n" +
                "            \"type\": \"Boolean\",\n" +
                "            \"value\": \"True\"\n" +
                "          }\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";

        GMSecurityStatusResponse response = mapper.readValue(json, GMSecurityStatusResponse.class);
    }
}