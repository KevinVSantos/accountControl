package br.com.KevinVSantos.AccountControl.domain.entity.account;

import br.com.KevinVSantos.AccountControl.domain.entity.AbstractEntity;
import br.com.KevinVSantos.AccountControl.domain.entity.client.Client;
import br.com.KevinVSantos.AccountControl.domain.entity.payment.Payment;
import br.com.KevinVSantos.AccountControl.domain.enums.AccountStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@IdClass(AccountId.class)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Account extends AbstractEntity<AccountId> {

    @Id
    @GeneratedValue
    private Integer id;

    @Id
    private Integer agency;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private AccountStatusEnum status;

    @JoinColumn(name = "clientDocument")
    private String clientDocument;

    @JsonIgnore
    @ManyToOne( targetEntity = Client.class)
    @JoinColumn(name = "clientDocument", insertable = false, updatable = false)
    private Client client;

    @OneToMany( mappedBy = "destination")
    private List<Payment> credits;

    @OneToMany( mappedBy = "origin")
    private List<Payment> debits;

    @Override
    @JsonIgnore
    public AccountId getGenericId() {
        return new AccountId(id,agency);
    }

}
