package br.com.KevinVSantos.AccountControl.domain.entity.notification;

import br.com.KevinVSantos.AccountControl.domain.enums.NotificationTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer accountId;

    @NotNull
    private Integer accountAgency;

    @NotNull
    private Long paymentId;

    @NotNull
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @NotNull
    private NotificationTypeEnum type;

    private boolean notificated = false;
}
