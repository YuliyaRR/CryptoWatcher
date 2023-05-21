package com.task.lab.crypto_watcher.service.api;

import com.task.lab.crypto_watcher.core.dto.crypto.CryptoRate;
import com.task.lab.crypto_watcher.entity.CryptoEntity;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface ICryptoService {
    List<CryptoRate> get();
    CryptoRate getPriceCrypto(@NotNull String symbol);
    CryptoEntity getCryptoEntity(@NotNull String symbol);
    void updateCryptoRate();
}
