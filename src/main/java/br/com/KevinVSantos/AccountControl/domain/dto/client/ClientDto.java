package br.com.KevinVSantos.AccountControl.domain.dto.client;

import br.com.KevinVSantos.AccountControl.domain.entity.client.Client;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true, value = {"password", "accounts"})
public class ClientDto {
}
