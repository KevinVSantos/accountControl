package br.com.KevinVSantos.AccountControl.service;

import br.com.KevinVSantos.AccountControl.domain.entity.client.Client;
import br.com.KevinVSantos.AccountControl.handler.exception.ForbiddenException;
import br.com.KevinVSantos.AccountControl.repository.IClientRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClientService extends AbstractService<String, Client, IClientRepository> {

    public void isAuth(String document, String password){
        var user = this.getRepository().findById(document);

        if(user.isEmpty() || !Objects.equals(user.get().getPassword(), password)){
            throw new ForbiddenException();
        }
    }

}
