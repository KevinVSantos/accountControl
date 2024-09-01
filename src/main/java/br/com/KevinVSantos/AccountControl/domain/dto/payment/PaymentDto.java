package br.com.KevinVSantos.AccountControl.domain.dto.payment;

import br.com.KevinVSantos.AccountControl.domain.entity.client.Client;
import br.com.KevinVSantos.AccountControl.domain.entity.payment.Payment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentDto extends Payment {
}
