package com.example.newMock2.Model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ResponseDTO {
    private String rqUID;
    private String clientId;
    private String account;
    private String currency;
    private BigDecimal balance;
    private String maxLimit;

}
