package com.task.lab.crypto_watcher.service.impl;

import com.task.lab.crypto_watcher.core.dto.crypto.CryptoRate;
import com.task.lab.crypto_watcher.core.dto.error.ErrorCode;
import com.task.lab.crypto_watcher.core.exception.ConversionTimeException;
import com.task.lab.crypto_watcher.core.exception.NotFoundDataBaseException;
import com.task.lab.crypto_watcher.entity.CryptoEntity;
import com.task.lab.crypto_watcher.core.events.CryptoEvents;
import com.task.lab.crypto_watcher.repo.api.ICryptoRepository;
import com.task.lab.crypto_watcher.service.api.ICryptoService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.convert.ConversionService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CryptoServiceImpl implements ICryptoService {
    @Value("${crypto.api.url}")
    private String CRYPTO_URL;
    @Value("${crypto.id}")
    private String CRYPTO_IDS;
    private final ICryptoRepository repository;
    private final ConversionService conversionService;
    private final RestTemplate restTemplate;
    @Autowired
    private ApplicationEventPublisher publisher;

    public CryptoServiceImpl(ICryptoRepository repository, ConversionService conversionService, RestTemplate restTemplate) {
        this.repository = repository;
        this.conversionService = conversionService;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<CryptoRate> get() {
        if(!conversionService.canConvert(CryptoEntity.class, CryptoRate.class)) {
            throw new ConversionTimeException("Unable to convert", ErrorCode.ERROR);
        }
        return this.repository.findAll()
                .stream()
                .map(entity -> conversionService.convert(entity, CryptoRate.class))
                .toList();
    }

    @Override
    public CryptoRate getPriceCrypto(@NotNull String symbol) {
        if(!conversionService.canConvert(CryptoEntity.class, CryptoRate.class)) {
            throw new ConversionTimeException("Unable to convert", ErrorCode.ERROR);
        }

        CryptoEntity cryptoEntity = repository.findBySymbol(symbol)
                .orElseThrow(() -> new NotFoundDataBaseException("Currency not found in database", ErrorCode.ERROR));

        return conversionService.convert(cryptoEntity, CryptoRate.class);
    }

    @Override
    public CryptoEntity getCryptoEntity(@NotNull String symbol) {
        return repository.findBySymbol(symbol)
                .orElseThrow(() -> new NotFoundDataBaseException("Currency not found in database", ErrorCode.ERROR));
    }

    @Override
    @Scheduled(fixedDelay = 60000)
    public void updateCryptoRate() {
        CryptoRate[] cryptoRates = restTemplate.getForObject(CRYPTO_URL + CRYPTO_IDS, CryptoRate[].class);

        if(Objects.nonNull(cryptoRates)) {
            List<CryptoEntity>allCrypto = repository.findAll();

            for (CryptoEntity cryptoEntity : allCrypto) {
                int idCryptoDB = cryptoEntity.getId();
                for (CryptoRate cryptoRate : cryptoRates) {
                    if(cryptoRate.getId() == idCryptoDB) {
                        double newPriceUsd = cryptoRate.getPriceUsd();
                        double currentPriceDB = cryptoEntity.getCurrentExchangeRate();

                        if(Math.abs(newPriceUsd * 100.0 / currentPriceDB - 100) > 1) {
                           publisher.publishEvent(new CryptoEvents(this, cryptoRate));
                        }

                        cryptoEntity.setCurrentExchangeRate(newPriceUsd);

                        break;
                    }
                }
            }
            repository.saveAll(allCrypto);
        }
    }

}
