package com.task.lab.crypto_watcher.core.dto.crypto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.Objects;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoRate {
    @JsonView(value = Views.Public.class)
    private int id;
    @JsonView(value = Views.Public.class)
    private String symbol;
    @JsonView(value = Views.Internal.class)
    @JsonProperty(value = "price_usd")
    private double priceUsd;

    public CryptoRate() {
    }

    public CryptoRate(int id, String symbol, double priceUsd) {
        this.id = id;
        this.symbol = symbol;
        this.priceUsd = priceUsd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(double priceUsd) {
        this.priceUsd = priceUsd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptoRate that = (CryptoRate) o;
        return id == that.id && Double.compare(that.priceUsd, priceUsd) == 0 && Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symbol, priceUsd);
    }
}
