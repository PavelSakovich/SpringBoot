package com.example.entity;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(name="id_user")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="age")
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address_user")
    private UserAddress userAddress;


    public User(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public User(int id, String firstName, String lastName, int age, UserAddress userAddress){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.userAddress = userAddress;
    }
}
