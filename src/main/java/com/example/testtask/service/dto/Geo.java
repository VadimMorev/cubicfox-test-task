package com.example.testtask.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Geo{

	@JsonProperty("lng")
	private String lng;

	@JsonProperty("lat")
	private String lat;
}