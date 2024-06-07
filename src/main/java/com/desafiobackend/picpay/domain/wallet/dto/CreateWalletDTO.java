package com.desafiobackend.picpay.domain.wallet.dto;

import com.desafiobackend.picpay.domain.wallet.model.Wallet;
import com.desafiobackend.picpay.domain.walletType.model.WalletType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class CreateWalletDTO {
    @NotBlank
    private String fullname;
    @NotBlank
    @CPF
    private String cpfCnpj;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private WalletType.Enum walletType;

    public static Wallet toWallet(CreateWalletDTO dto){
        return Wallet.builder()
                .fullName(dto.fullname)
                .cpfCnpj(dto.cpfCnpj)
                .email(dto.email)
                .password(dto.password)
                .walletType(dto.walletType.get())
                .build();
    }
}
