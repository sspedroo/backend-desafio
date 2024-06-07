package com.desafiobackend.picpay.domain.wallet.model;

import com.desafiobackend.picpay.domain.walletType.model.WalletType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "tb_wallet")
@Table(name = "tb_wallet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;

    @Column(unique = true)
    private String cpfCnpj;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal balance;
    @ManyToOne
    @JoinColumn(name = "wallet_type_id")
    private WalletType walletType;

    @PrePersist
    private void prePersist(){
        this.balance = BigDecimal.ZERO;
    }

    public boolean isTransferAllowedForWalletType() {
        return this.walletType.equals(WalletType.Enum.USER.get());
    }

    public boolean isBalanceEqualOrGreaterThan(BigDecimal value) {
        return this.balance.doubleValue() >= value.doubleValue();
    }

    public void debit(BigDecimal value) {
        this.balance = this.balance.subtract(value);
    }

    public void credit(BigDecimal value){
        this.balance = this.balance.add(value);
    }
}
