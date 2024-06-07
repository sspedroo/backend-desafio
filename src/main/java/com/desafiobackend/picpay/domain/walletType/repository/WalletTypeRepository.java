package com.desafiobackend.picpay.domain.walletType.repository;

import com.desafiobackend.picpay.domain.walletType.model.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
}
