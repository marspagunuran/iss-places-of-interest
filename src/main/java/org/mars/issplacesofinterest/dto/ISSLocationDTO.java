package org.mars.issplacesofinterest.dto;

import lombok.Data;

@Data
public class ISSLocationDTO {

    private double latitude;
    private double longitude;

    public ISSLocationDTO(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
