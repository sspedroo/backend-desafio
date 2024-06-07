package com.desafiobackend.picpay.domain.transfer.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferDTO {
    @DecimalMin("0.01")
    @NotNull
    private BigDecimal value;
    @NotNull
    private Long payer;
    @NotNull
    private Long payee;
}
