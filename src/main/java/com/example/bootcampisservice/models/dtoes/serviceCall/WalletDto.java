package com.example.bootcampisservice.models.dtoes.serviceCall;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WalletDto {
    private Long balance;
    private String walletCode;
    private String phoneNumber;
}
