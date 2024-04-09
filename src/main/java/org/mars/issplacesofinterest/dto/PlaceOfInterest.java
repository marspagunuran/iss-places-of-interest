package org.mars.issplacesofinterest.dto;

import lombok.Data;

@Data
public class PlaceOfInterest {

    private String title;
    private double lat;
    private double lon;
    private String country;

}