package com.desafiobackend.picpay.controller;

import com.desafiobackend.picpay.domain.transfer.dto.TransferDTO;
import com.desafiobackend.picpay.domain.transfer.dto.ViewTransferDTO;
import com.desafiobackend.picpay.domain.transfer.service.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfers")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;


    @PostMapping("/transfer")
    public ResponseEntity<ViewTransferDTO> transfer(@RequestBody @Valid TransferDTO dto){
        ViewTransferDTO transfer = transferService.transfer(dto);
        return ResponseEntity.ok(transfer);
    }
}
