package br.com.KevinVSantos.AccountControl.domain.entity.client;


import br.com.KevinVSantos.AccountControl.domain.entity.AbstractEntity;
import br.com.KevinVSantos.AccountControl.domain.entity.account.Account;
import br.com.KevinVSantos.AccountControl.domain.enums.ClientTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull
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

    @AssertTrue(message = "is not valid")
    public boolean isDocument(){
        if( (this.getType() == ClientTypeEnum.PF && this.getDocument().length() != 11) ||
             this.getType() == ClientTypeEnum.PJ && this.getDocument().length() != 14)
        {
            return false;
        }

        return true;
    }
}
