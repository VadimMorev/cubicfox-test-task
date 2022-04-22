package com.example.testtask.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "t_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_t_user_address_id"))
    private Address address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "website")
    private String website;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_t_user_company_id"))
    private Company company;
}
