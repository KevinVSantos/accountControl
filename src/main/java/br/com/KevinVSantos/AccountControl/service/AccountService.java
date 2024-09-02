package br.com.KevinVSantos.AccountControl.service;

import br.com.KevinVSantos.AccountControl.domain.entity.account.Account;
import br.com.KevinVSantos.AccountControl.domain.entity.account.AccountId;
import br.com.KevinVSantos.AccountControl.handler.exception.EntityNotFoundException;
import br.com.KevinVSantos.AccountControl.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends AbstractService<AccountId, Account, IAccountRepository> {

    @Autowired
    private ClientService clientService;

    @Override
    public void delete(AccountId id){
        this.getRepository().deleteById(id);
    }

    public Account findById(Integer id, Integer agency){
        var accountId = new AccountId(id, agency);
        return findById(accountId);
    }

    @Override
    public Account create(Account entity){

        try {
            clientService.findById(entity.getClientDocument());
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Client not found");
        }

        return super.create(entity);
    }

}
