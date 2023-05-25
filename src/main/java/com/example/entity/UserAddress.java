package com.example.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_address")
public class UserAddress {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name="city")
    private String city;

    @Column(name= "street")
    private String street;

    @Column(name="house")
    private int house;

    @OneToOne(mappedBy = "userAddress", orphanRemoval = true)
    private User user;

    @Override
    public String toString() {
        return "UserAddress{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house=" + house +
                '}';
    }

    public UserAddress(String city, String street, int house) {
        this.city = city;
        this.street = street;
        this.house = house;
    }
}

