package com.desafiobackend.picpay.domain.transfer.dto;

import com.desafiobackend.picpay.domain.wallet.dto.ViewWalletDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewTransferDTO {
    private UUID id;
    private ViewWalletDTO payer;
    private ViewWalletDTO payee;
    private BigDecimal value;
}
