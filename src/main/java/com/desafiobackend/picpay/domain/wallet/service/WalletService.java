package com.desafiobackend.picpay.domain.wallet.service;

import com.desafiobackend.picpay.domain.wallet.dto.CreateWalletDTO;
import com.desafiobackend.picpay.domain.wallet.dto.ViewWalletDTO;
import com.desafiobackend.picpay.domain.wallet.model.Wallet;
import com.desafiobackend.picpay.domain.wallet.repository.WalletRepository;
import com.desafiobackend.picpay.exceptions.WalletDataAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository repository;


    @Transactional
    public ViewWalletDTO createWallet(CreateWalletDTO dto){
        Optional<Wallet> cnpjOrEmail = repository.findByCpfCnpjOrEmail(dto.getCpfCnpj(), dto.getEmail());

        if (cnpjOrEmail.isPresent()){
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
        }

        Wallet wallet = CreateWalletDTO.toWallet(dto);
        repository.save(wallet);
        return new ViewWalletDTO(wallet);
    }
}
