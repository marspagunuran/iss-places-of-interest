package org.mars.issplacesofinterest.entities;


import lombok.Data;

import java.util.List;

@Data
public class Query {

    private List<MediaWikiPlaces> geosearch;

}
