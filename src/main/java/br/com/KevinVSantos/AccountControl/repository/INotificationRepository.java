package br.com.KevinVSantos.AccountControl.repository;

import br.com.KevinVSantos.AccountControl.domain.entity.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface INotificationRepository extends JpaRepository<Notification, Long> {

    @Query("select n from Notification n where n.notificated = FALSE")
    List<Notification> findNotSendedNotification();

}
