package br.com.KevinVSantos.AccountControl.domain.entity.client;


import br.com.KevinVSantos.AccountControl.domain.entity.AbstractEntity;
import br.com.KevinVSantos.AccountControl.domain.entity.account.Account;
import br.com.KevinVSantos.AccountControl.domain.enums.ClientTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Client extends AbstractEntity<String> {

    @Id
    private String document;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private ClientTypeEnum type;

    @Embedded
    private Address address;

    @OneToMany
    @JoinColumn(name = "clientDocument", insertable = false, updatable = false)
    private List<Account> accounts;

    @Override
    @JsonIgnore
    public String getGenericId() {
        return this.getDocument();
    }
}
