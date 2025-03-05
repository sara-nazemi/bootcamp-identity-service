package com.example.bootcampisservice.services.feignServiceCall;

import com.example.bootcampisservice.models.dtoes.serviceCall.WalletDto;
import org.hibernate.service.spi.ServiceException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "walletservice", url = "http://192.168.200.181:8082/wallet")
public interface WalletServiceCallFeignImpl {
    @RequestMapping(method = RequestMethod.POST, value = "/create", produces = "application/json")
    WalletDto createWallet(@RequestHeader String token) throws ServiceException;
}
