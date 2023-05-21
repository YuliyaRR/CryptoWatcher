package com.task.lab.crypto_watcher.converters;

import com.task.lab.crypto_watcher.core.dto.crypto.CryptoRate;
import com.task.lab.crypto_watcher.entity.CryptoEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConverterCryptoFromEntityToDto implements Converter<CryptoEntity, CryptoRate> {

    @Override
    public CryptoRate convert(CryptoEntity source) {
        return new CryptoRate(source.getId(), source.getSymbol(), source.getCurrentExchangeRate());
    }
}
