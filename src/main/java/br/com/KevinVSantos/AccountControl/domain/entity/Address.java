package br.com.KevinVSantos.AccountControl.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    public String cep;
    public Integer number;
    public String country;
    public String obs;
}
