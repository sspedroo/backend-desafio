package com.desafiobackend.picpay.domain.notification;

import com.desafiobackend.picpay.client.NotificationClient;
import com.desafiobackend.picpay.domain.transfer.model.Transfer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationClient notificationClient;

    public void sendNotification(Transfer transfer) {

        try {
            log.info("Sending notification");

            ResponseEntity<Void> response = notificationClient.sendNotification(transfer);

            if (response.getStatusCode().isError()){
                log.error("Error while sending notification, status code: " + response.getStatusCode().value());
            }
        } catch (Exception e){
            log.error("Error while sending notification", e);
        }
    }
}
