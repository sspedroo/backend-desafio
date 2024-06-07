package com.desafiobackend.picpay.domain.transfer.repository;

import com.desafiobackend.picpay.domain.transfer.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, UUID> {
}
