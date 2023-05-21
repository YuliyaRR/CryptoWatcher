package com.task.lab.crypto_watcher.service;

import com.task.lab.crypto_watcher.core.events.CryptoEvents;
import com.task.lab.crypto_watcher.service.api.INotifyService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ChangingCryptoRateHandler {
    private final INotifyService notifyService;

    public ChangingCryptoRateHandler(INotifyService notifyService) {
        this.notifyService = notifyService;
    }
    @EventListener
    public void handleIncreaseCryptoEvent (CryptoEvents cryptoEvents) {
        notifyService.notifyUser(cryptoEvents.getCryptoRate());
    }
}
