package com.desafiobackend.picpay.domain.transfer.service;

import com.desafiobackend.picpay.domain.authorization.AuthorizationService;
import com.desafiobackend.picpay.domain.notification.NotificationService;
import com.desafiobackend.picpay.domain.transfer.dto.TransferDTO;
import com.desafiobackend.picpay.domain.transfer.dto.ViewTransferDTO;
import com.desafiobackend.picpay.domain.transfer.model.Transfer;
import com.desafiobackend.picpay.domain.transfer.repository.TransferRepository;
import com.desafiobackend.picpay.domain.wallet.dto.ViewWalletDTO;
import com.desafiobackend.picpay.domain.wallet.model.Wallet;
import com.desafiobackend.picpay.domain.wallet.repository.WalletRepository;
import com.desafiobackend.picpay.exceptions.InsufficientBalanceException;
import com.desafiobackend.picpay.exceptions.TransferNotAllowedForWalletTypeException;
import com.desafiobackend.picpay.exceptions.TransferNotAuthorizedException;
import com.desafiobackend.picpay.exceptions.WalletNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class TransferService {
    private final TransferRepository transferRepository;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final WalletRepository walletRepository;



    @Transactional
    public ViewTransferDTO transfer(TransferDTO dto){

        Wallet sender = walletRepository.findById(dto.getPayer())
                .orElseThrow(() -> new WalletNotFoundException(dto.getPayer()));

        Wallet receiver = walletRepository.findById(dto.getPayee())
                .orElseThrow(() -> new WalletNotFoundException(dto.getPayee()));

        validateTransfer(dto, sender);

        sender.debit(dto.getValue());
        receiver.credit(dto.getValue());

        Transfer transfer = Transfer.builder()
                .sender(sender)
                .receiver(receiver)
                .value(dto.getValue())
                .build();

        walletRepository.save(sender);
        walletRepository.save(receiver);

        Transfer transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return ViewTransferDTO.builder()
                .id(transferResult.getId())
                .payee(new ViewWalletDTO(receiver))
                .payer(new ViewWalletDTO(sender))
                .value(transferResult.getValue())
                .build();
    }

    private void validateTransfer(TransferDTO dto, Wallet sender) {
        if (!sender.isTransferAllowedForWalletType()){
            throw new TransferNotAllowedForWalletTypeException();
        }

        if (!sender.isBalanceEqualOrGreaterThan(dto.getValue())){
            throw new InsufficientBalanceException();
        }

        if (!authorizationService.isAuthorized(dto)){
            throw new TransferNotAuthorizedException();
        }
    }
}
