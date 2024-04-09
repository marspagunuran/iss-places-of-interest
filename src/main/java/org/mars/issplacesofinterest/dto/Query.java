package org.mars.issplacesofinterest.dto;


import lombok.Data;

import java.util.List;

@Data
public class Query {

    private List<PlaceOfInterest> geosearch;

}
