package org.mars.issplacesofinterest.entities;

import lombok.Data;

@Data
public class MediaWikiPlaces {

    private String title;
    private double lat;
    private double lon;
    private String country;

}