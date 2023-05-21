package com.task.lab.crypto_watcher.core.events;

import com.task.lab.crypto_watcher.core.dto.crypto.CryptoRate;
import org.springframework.context.ApplicationEvent;

public class CryptoEvents extends ApplicationEvent {

    private final CryptoRate cryptoRate;

    public CryptoEvents(Object source, CryptoRate cryptoRate) {
        super(source);
        this.cryptoRate = cryptoRate;
    }

    public CryptoRate getCryptoRate() {
        return cryptoRate;
    }
}
