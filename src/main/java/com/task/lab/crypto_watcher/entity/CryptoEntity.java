package com.task.lab.crypto_watcher.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(schema = "app", name = "crypto")
public class CryptoEntity {
    @Id
    private int id;
    private String symbol;
    @Column(name = "current_rate")
    private double currentExchangeRate;

    public CryptoEntity() {
    }

    public CryptoEntity(int id, String symbol, double currentExchangeRate) {
        this.id = id;
        this.symbol = symbol;
        this.currentExchangeRate = currentExchangeRate;
    }

    public int getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getCurrentExchangeRate() {
        return currentExchangeRate;
    }

    public void setCurrentExchangeRate(double currentExchangeRate) {
        this.currentExchangeRate = currentExchangeRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptoEntity that = (CryptoEntity) o;
        return id == that.id && Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symbol);
    }

    @Override
    public String toString() {
        return "CryptoEntity{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", currentExchangeRate=" + currentExchangeRate +
                '}';
    }
}
