package org.mars.issplacesofinterest.utils;

public class QueryBuilder {

    public static String media_wiki_api_buildQuery(double latitude, double longitude) {
        return String.format("?action=query&list=geosearch&prop=coordinates&gsradius=10000&gscoord=%.6f|%.6f&gslimit=10&format=json",
                latitude, longitude);
    }

    public static String photon_api_buildQuery(double latitude, double longitude) {
        return String.format("?lon=%.6f&lat=%.6f", longitude, latitude);
    }
}
