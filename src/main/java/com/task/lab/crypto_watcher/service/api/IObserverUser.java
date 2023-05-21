package com.task.lab.crypto_watcher.service.api;

import com.task.lab.crypto_watcher.core.dto.crypto.CryptoRate;
import jakarta.validation.constraints.NotNull;

public interface IObserverUser {
    void notifyUser(@NotNull CryptoRate cryptoRate);
}
