package com.desafiobackend.picpay.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;



@Setter
@Getter
public class InsufficientBalanceException extends PicPayException{


    @Override
    public ProblemDetail toProblemDetail() {
        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Insufficient balance");
        pb.setDetail("You cannot transfer a value bigger than your current balance.");

        return pb;
    }
}
