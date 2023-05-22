package com.task.lab.crypto_watcher.service.impl;

import com.task.lab.crypto_watcher.core.dto.user.UserSubscription;
import com.task.lab.crypto_watcher.entity.UserEntity;
import com.task.lab.crypto_watcher.repo.api.IUserRepository;
import com.task.lab.crypto_watcher.service.api.IUserService;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository repository;
    public UserServiceImpl(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public UserEntity getUserEntity(@NotNull UserSubscription userSubscription) {
        Optional<UserEntity> byUsername = repository.findByUsername(userSubscription.getName());

        if(byUsername.isEmpty()) {
            return repository.save(new UserEntity(UUID.randomUUID(), userSubscription.getName()));
        } else {
            return byUsername.get();
        }
    }

}
