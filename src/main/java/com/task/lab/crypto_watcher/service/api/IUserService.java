package com.task.lab.crypto_watcher.service.api;

import com.task.lab.crypto_watcher.core.dto.user.UserSubscription;
import com.task.lab.crypto_watcher.entity.UserEntity;
import jakarta.validation.constraints.NotNull;

public interface IUserService {
    UserEntity getUserEntity(@NotNull UserSubscription userSubscription);
}
