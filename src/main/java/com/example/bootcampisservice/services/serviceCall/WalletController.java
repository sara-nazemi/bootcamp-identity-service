package com.example.bootcampisservice.services.serviceCall;

import com.example.bootcampisservice.models.dtoes.serviceCall.WalletDto;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feign")
public class WalletController{
    @Autowired
    private WalletServiceCallImpl walletServiceCall;
    @PostMapping("/create")
    public WalletDto createWallet(@RequestHeader String token) throws ServiceException {
        return walletServiceCall.createWallet(token);
    }
}
