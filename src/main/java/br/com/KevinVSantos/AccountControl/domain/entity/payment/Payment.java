package br.com.KevinVSantos.AccountControl.domain.entity.payment;

import br.com.KevinVSantos.AccountControl.domain.entity.AbstractEntity;
import br.com.KevinVSantos.AccountControl.domain.entity.account.Account;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "originId")
    private Integer originId;

    @JoinColumn(name = "originAgency")
    private Integer originAgency;

    @JsonIgnore
    @ManyToOne(targetEntity = Account.class)
    @JoinColumns(value = {
            @JoinColumn(name = "originId", referencedColumnName = "id", insertable = false, updatable = false),
            @JoinColumn(name = "originAgency", referencedColumnName = "agency", insertable = false, updatable = false)
    })
    private Account origin;

    @JoinColumn(name = "destinationId")
    private Integer destinationId;

    @JoinColumn(name = "destinationAgency")
    private Integer destinationAgency;

    @JsonIgnore
    @ManyToOne(targetEntity = Account.class)
    @JoinColumns(value = {
            @JoinColumn(name = "destinationId", referencedColumnName = "id",insertable = false, updatable = false),
            @JoinColumn(name = "destinationAgency", referencedColumnName = "agency",insertable = false, updatable = false)
    })
    private Account destination;

    @JoinColumn(name = "parentId")
    private Long parentId;

    @OneToOne( optional=true, targetEntity = Payment.class)
    @JoinColumn(name="parentId", referencedColumnName = "id", insertable = false, updatable = false)
    private Payment parent;

    @JsonIgnore
    @OneToOne(mappedBy="parent", optional=true, orphanRemoval = true, cascade = CascadeType.ALL)
    private Payment children;

    private BigDecimal amount;

    private String justification;

    @Override
    @JsonIgnore
    public Long getGenericId() {
        return this.getId();
    }
}
