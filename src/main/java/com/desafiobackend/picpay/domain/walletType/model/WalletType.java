package com.desafiobackend.picpay.domain.walletType.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity(name = "tb_wallet_type")
@Table(name = "tb_wallet_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WalletType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;


    public enum Enum {
        USER(1L, "user"),
        MERCHANT(2L, "merchant");

        Enum(Long id, String description){
            this.id = id;
            this.description = description;
        }
        private Long id;
        private String description;

        public WalletType get() {
            return new WalletType(id, description);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletType that = (WalletType) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}
