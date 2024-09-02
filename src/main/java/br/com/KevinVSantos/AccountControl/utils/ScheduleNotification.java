package br.com.KevinVSantos.AccountControl.utils;

import br.com.KevinVSantos.AccountControl.domain.entity.notification.Notification;
import br.com.KevinVSantos.AccountControl.repository.INotificationRepository;
import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component @EnableScheduling
public class ScheduleNotification {

    @Autowired
    private INotificationRepository repository;

    @Value("${app.notification.url}")
    private String url;

    @Scheduled(fixedDelayString = "${app.notification.interval}", timeUnit = TimeUnit.SECONDS)
    public void sendNotification() throws UnirestException {

        var notifications = repository.findNotSendedNotification();

        for (var not: notifications) {
            Gson gson = new Gson();
            var result = Unirest.post(url).body(gson.toJson(not)).asString();

            if(result.getStatus() < 200 ||  result.getStatus() >= 300)
            {
                break;
            }

            not.setNotificated(true);
            repository.save(not);

        }

    }
}
