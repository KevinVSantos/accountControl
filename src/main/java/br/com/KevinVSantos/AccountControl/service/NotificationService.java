package br.com.KevinVSantos.AccountControl.service;

import br.com.KevinVSantos.AccountControl.domain.entity.account.Account;
import br.com.KevinVSantos.AccountControl.domain.entity.notification.Notification;
import br.com.KevinVSantos.AccountControl.domain.entity.payment.Payment;
import br.com.KevinVSantos.AccountControl.domain.enums.NotificationTypeEnum;
import br.com.KevinVSantos.AccountControl.repository.INotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {

    @Autowired
    private INotificationRepository repository;

    public void create(Account origin, Account destination, Payment payment){

        var debitNotification = new Notification(null, origin.getId(), origin.getAgency(), payment.getId(), payment.getAmount(), NotificationTypeEnum.DEBITO, false);
        repository.save(debitNotification);

        var creditNotification = new Notification(null, destination.getId(), destination.getAgency(), payment.getId(), payment.getAmount(), NotificationTypeEnum.CREDITO, false);
        repository.save(creditNotification);

    }
}
