package com.task.lab.crypto_watcher.repo.api;

import com.task.lab.crypto_watcher.entity.NotifyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface INotifyRepository extends JpaRepository<NotifyEntity, Long> {

    Optional<List<NotifyEntity>> findAllByCryptoId(int cryptoId);
}
