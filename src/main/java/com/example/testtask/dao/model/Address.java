package com.example.testtask.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "t_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "street")
    private String street;
    @Column(name = "suite")
    private String suite;
    @Column(name = "city")
    private String city;
    @Column(name = "zipcode")
    private String zipcode;
    @Column(name = "geo_lat")
    private Double geoLat;
    @Column(name = "geo_lng")
    private Double geoLng;
}
