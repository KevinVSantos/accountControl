package br.com.KevinVSantos.AccountControl.domain.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class Client {

    @Id
    public String document;
    public String name;
    public String password;

    @Embedded
    public Address address;
}
