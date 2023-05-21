package com.task.lab.crypto_watcher.web.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.task.lab.crypto_watcher.core.dto.crypto.CryptoRate;
import com.task.lab.crypto_watcher.core.dto.crypto.Views;
import com.task.lab.crypto_watcher.core.dto.user.UserSubscription;
import com.task.lab.crypto_watcher.service.api.ICryptoService;
import com.task.lab.crypto_watcher.service.api.INotifyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/crypto")
public class CryptoController {
    private final ICryptoService cryptoService;
    private final INotifyService notifyService;

    public CryptoController(ICryptoService cryptoService, INotifyService notifyService) {
        this.cryptoService = cryptoService;
        this.notifyService = notifyService;
    }

    @GetMapping
    @JsonView(value = Views.Public.class)
    public ResponseEntity<List<CryptoRate>> getAll() {
        return new ResponseEntity<>(cryptoService.get(), HttpStatus.OK);
    }

    @GetMapping(path = "/price")
    @JsonView(value = Views.Internal.class)
    public ResponseEntity<CryptoRate> getPriceCrypto(@RequestParam(name = "symbol") String symbol) {
        return new ResponseEntity<>(cryptoService.getPriceCrypto(symbol), HttpStatus.OK);
    }

    @PostMapping(path = "/notify")
    public ResponseEntity<?> addNotify(@Valid @RequestBody UserSubscription userSubscription) {
        notifyService.addNotification(userSubscription);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
