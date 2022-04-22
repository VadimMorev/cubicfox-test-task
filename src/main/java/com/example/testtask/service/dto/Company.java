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
public class Company{

	@JsonProperty("bs")
	private String bs;

	@JsonProperty("catchPhrase")
	private String catchPhrase;

	@JsonProperty("name")
	private String name;
}