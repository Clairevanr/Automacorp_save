package com.emse.spring.automacorp.gouv_api;

public record ApiGouvAdress(
    String id,
    String label,
    String housenumber,
    Double score,
    String postcode,
    String citycode,
    String city,
    String context,
    String type,
    Double x,
    Double y){


}
