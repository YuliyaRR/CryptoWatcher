package com.task.lab.crypto_watcher.core.dto.user;

import com.task.lab.crypto_watcher.core.dto.crypto.Symbol;
import com.task.lab.crypto_watcher.validator.api.ValidString;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class UserSubscription {
    @ValidString
    private String name;
    @NotNull
    private Symbol symbol;

    public UserSubscription() {
    }

    public UserSubscription(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSubscription that = (UserSubscription) o;
        return Objects.equals(name, that.name) && Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, symbol);
    }
}
