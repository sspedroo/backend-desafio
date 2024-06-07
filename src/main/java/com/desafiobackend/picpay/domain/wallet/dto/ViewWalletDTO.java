package com.desafiobackend.picpay.domain.wallet.dto;

import com.desafiobackend.picpay.domain.wallet.model.Wallet;
import com.desafiobackend.picpay.domain.walletType.model.WalletType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewWalletDTO {
    private Long id;
    private String fullname;
    private String cpfCnpj;
    private String email;
    private String password;
    private BigDecimal balance;
    private WalletType walletType;

    public ViewWalletDTO (Wallet entity){
        this.id = entity.getId();
        this.fullname = entity.getFullName();
        this.cpfCnpj = entity.getCpfCnpj();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.balance = entity.getBalance();
        this.walletType = entity.getWalletType();
    }
}
