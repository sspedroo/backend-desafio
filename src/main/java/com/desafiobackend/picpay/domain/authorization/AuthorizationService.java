package com.desafiobackend.picpay.domain.authorization;

import com.desafiobackend.picpay.client.AuthorizationClient;
import com.desafiobackend.picpay.client.dto.AuthorizationResponse;
import com.desafiobackend.picpay.domain.transfer.dto.TransferDTO;
import com.desafiobackend.picpay.domain.transfer.model.Transfer;
import com.desafiobackend.picpay.exceptions.PicPayException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public boolean isAuthorized(TransferDTO transferDTO) {
        ResponseEntity<AuthorizationResponse> authorized = authorizationClient.isAuthorized();

        if (authorized.getStatusCode().isError()) {
            throw new PicPayException();
        }

        return authorized.getBody().getAuthorized();
    }
}
