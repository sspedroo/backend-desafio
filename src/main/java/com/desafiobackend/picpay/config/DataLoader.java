package com.desafiobackend.picpay.config;

import com.desafiobackend.picpay.domain.walletType.model.WalletType;
import com.desafiobackend.picpay.domain.walletType.repository.WalletTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final WalletTypeRepository walletTypeRepository;

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(WalletType.Enum.values())
                .forEach(wl -> walletTypeRepository.save(wl.get()));
    }
}
