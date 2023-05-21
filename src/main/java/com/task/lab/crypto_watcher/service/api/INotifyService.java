package com.task.lab.crypto_watcher.service.api;

import com.task.lab.crypto_watcher.core.dto.user.UserSubscription;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface INotifyService extends IObserverUser{
    void addNotification(@Valid @NotNull UserSubscription userSubscription);

}
