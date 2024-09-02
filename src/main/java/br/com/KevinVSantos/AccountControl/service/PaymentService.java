package br.com.KevinVSantos.AccountControl.service;

import br.com.KevinVSantos.AccountControl.domain.entity.account.Account;
import br.com.KevinVSantos.AccountControl.domain.entity.payment.Payment;
import br.com.KevinVSantos.AccountControl.handler.exception.EntityNotFoundException;
import br.com.KevinVSantos.AccountControl.handler.exception.ForbiddenException;
import br.com.KevinVSantos.AccountControl.handler.exception.PaymentAmountException;
import br.com.KevinVSantos.AccountControl.handler.exception.PaymentInsuficientBalanceException;
import br.com.KevinVSantos.AccountControl.repository.IPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService extends AbstractService<Long, Payment, IPaymentRepository> {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    public Payment create(Payment entity, String password) {

        var origin = verifyAccount(entity.getOriginId(), entity.getOriginAgency(), "Origin account not found");
        clientService.isAuth(origin.getClientDocument(), password);

        var destination = verifyAccount(entity.getDestinationId(), entity.getDestinationAgency(), "Destination account not found");

        verifyAmount(entity.getAmount(), origin.getBalance());

        entity.setParentId(null);

        var result = this.getRepository().save(entity);

        transfer(origin, destination, entity.getAmount());

        return result;
    }

    public Payment revertPayment(Long id, String justification, String password){

        var oldPayment =  this.findById(id);

        if(oldPayment.getChildren()!= null)
        {
            throw new ForbiddenException();
        }

        var destination = verifyAccount(oldPayment.getOriginId(), oldPayment.getOriginAgency(), "Origin account not found");
        clientService.isAuth(destination.getClientDocument(), password);
        var origin = verifyAccount(oldPayment.getDestinationId(), oldPayment.getDestinationAgency(), "Destination account not found");

        verifyAmount(oldPayment.getAmount(), origin.getBalance());
        var revertPayment = new Payment();
        revertPayment.setOriginId(origin.getId());
        revertPayment.setOriginAgency(origin.getAgency());
        revertPayment.setDestinationId(destination.getId());
        revertPayment.setDestinationAgency(destination.getAgency());
        revertPayment.setParentId(oldPayment.getId());
        revertPayment.setAmount(oldPayment.getAmount());
        revertPayment.setJustification(justification);

        var result = this.getRepository().save(revertPayment);

        transfer(origin, destination, oldPayment.getAmount());

        return result;
    }

    private Account verifyAccount(Integer id, Integer agency, String message){

        try {
            return accountService.findById(id, agency);
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException(message);
        }
    }

    private void verifyAmount(BigDecimal paymentAmount, BigDecimal balance){
        if(paymentAmount.compareTo(BigDecimal.ZERO)<= 0)
        {
            throw new PaymentAmountException();
        }

        if(balance.compareTo(paymentAmount) < 0){
            throw new PaymentInsuficientBalanceException();
        }
    }

    private void transfer(Account origin, Account destination, BigDecimal amount){

        origin.setBalance(origin.getBalance().subtract(amount));
        this.accountService.update(origin);
        destination.setBalance(destination.getBalance().add(amount));
        this.accountService.update(destination);

    }

}
