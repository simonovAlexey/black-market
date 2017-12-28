package com.simonov.blackMarket.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Identifiable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class User implements Identifiable<Integer> {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;

    public User(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User(int id,String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.id=id;
    }


}
