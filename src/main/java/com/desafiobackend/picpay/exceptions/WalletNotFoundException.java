package com.desafiobackend.picpay.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalletNotFoundException extends PicPayException{

    private Long walletId;

    @Override
    public ProblemDetail toProblemDetail() {
        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Wallet not found");
        pb.setDetail("There is no wallet with this id: " + walletId + ".");

        return pb;
    }
}
