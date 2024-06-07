package com.desafiobackend.picpay.domain.transfer.model;

import com.desafiobackend.picpay.domain.wallet.model.Wallet;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "tb_transfer")
@Table(name = "tb_transfer")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "wallet_sender_id")
    private Wallet sender;
    @ManyToOne
    @JoinColumn(name = "wallet_receiver_id")
    private Wallet receiver;
    private BigDecimal value;

}
