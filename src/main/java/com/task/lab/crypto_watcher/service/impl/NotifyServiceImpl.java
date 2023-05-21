package com.task.lab.crypto_watcher.service.impl;

import com.task.lab.crypto_watcher.core.dto.crypto.CryptoRate;
import com.task.lab.crypto_watcher.core.dto.error.ErrorCode;
import com.task.lab.crypto_watcher.core.dto.user.UserSubscription;
import com.task.lab.crypto_watcher.core.exception.NotFoundDataBaseException;
import com.task.lab.crypto_watcher.entity.CryptoEntity;
import com.task.lab.crypto_watcher.entity.NotifyEntity;
import com.task.lab.crypto_watcher.entity.UserEntity;
import com.task.lab.crypto_watcher.repo.api.INotifyRepository;
import com.task.lab.crypto_watcher.service.api.ICryptoService;
import com.task.lab.crypto_watcher.service.api.INotifyService;
import com.task.lab.crypto_watcher.service.api.IUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class NotifyServiceImpl implements INotifyService {
    private static final Logger LOGGER = LogManager.getLogger(NotifyServiceImpl.class);
    @Value("${crypto.message}")
    private String MESSAGE;
    private final INotifyRepository notifyRepository;
    private final IUserService userService;
    private final ICryptoService cryptoService;

    public NotifyServiceImpl(INotifyRepository notifyRepository, IUserService userService, ICryptoService cryptoService) {
        this.notifyRepository = notifyRepository;
        this.userService = userService;
        this.cryptoService = cryptoService;
    }

    @Override
    @Transactional
    public void addNotification(@Valid @NotNull UserSubscription userSubscription) {
        UserEntity userEntity = userService.getUserEntity(userSubscription);
        CryptoEntity cryptoEntity = cryptoService.getCryptoEntity(userSubscription.getSymbol().name());

        notifyRepository.save(new NotifyEntity(userEntity, cryptoEntity, cryptoEntity.getCurrentExchangeRate()));
    }

    @Override
    public void notifyUser(@NotNull CryptoRate cryptoRate) {
        int idCrypto = cryptoRate.getId();
        double priceUsd = cryptoRate.getPriceUsd();

        List<NotifyEntity> notifyEntities = notifyRepository.findAllByCryptoId(idCrypto)
                .orElseThrow(() -> new NotFoundDataBaseException("Currency not found in database", ErrorCode.ERROR));

        for (NotifyEntity notify : notifyEntities) {
            double baseRate = notify.getBasicRate();
            double delta = (priceUsd * 100d / baseRate) - 100;

            LOGGER.warn(String.format(MESSAGE, cryptoRate.getSymbol(), notify.getUser().getUsername(), delta));
        }
    }


}
