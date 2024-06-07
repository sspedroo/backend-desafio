package com.desafiobackend.picpay.controller;

import com.desafiobackend.picpay.domain.wallet.dto.CreateWalletDTO;
import com.desafiobackend.picpay.domain.wallet.dto.ViewWalletDTO;
import com.desafiobackend.picpay.domain.wallet.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService service;


    @PostMapping("/wallets")
    public ResponseEntity<ViewWalletDTO> createWallet(@RequestBody @Valid CreateWalletDTO dto){
        ViewWalletDTO wallet = service.createWallet(dto);
        return ResponseEntity.ok(wallet);
    }
}
