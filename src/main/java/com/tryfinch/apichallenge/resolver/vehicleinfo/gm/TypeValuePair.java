package com.tryfinch.apichallenge.resolver.vehicleinfo.gm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeValuePair {
    private String type;
    private String value;

    public boolean getBoolean() {
        return "True".equalsIgnoreCase(value);
    }

    public Float getFloat() {
        if (value == null || "null".equalsIgnoreCase(value)) {
            return null;
        }

        return Float.valueOf(value);
    }
}
