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
public class Response {
	@JsonProperty("id")
	private int id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("username")
	private String username;

	@JsonProperty("email")
	private String email;

	@JsonProperty("website")
	private String website;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("address")
	private Address address;

	@JsonProperty("company")
	private Company company;

}