package com.task.lab.crypto_watcher.repo.api;

import com.task.lab.crypto_watcher.entity.CryptoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICryptoRepository extends JpaRepository<CryptoEntity, Integer> {

    Optional<CryptoEntity> findBySymbol(String symbol);
}
