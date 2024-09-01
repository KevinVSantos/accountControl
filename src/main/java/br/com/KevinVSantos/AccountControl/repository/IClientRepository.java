package br.com.KevinVSantos.AccountControl.repository;

import br.com.KevinVSantos.AccountControl.domain.entity.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client, String> {
}
