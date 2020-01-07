package i.ramos.aop.service;

import i.ramos.aop.annotation.Operation;
import i.ramos.aop.repository.OperationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyService {
    @Operation("BUY_USD")
    public void buyUsd(){
        log.info("BUYING USDS");
    }

    @Operation("BUY_ARS")
    public void buyArs(){
        log.info("BUYING ARS");
    }

    @Operation("SELL_USD")
    public void sellUsd(){
        log.info("SELLING USDS");
    }

    @Operation("SELL_ARS")
    public void sellArs(){
        log.info("SELLING ARS");
    }

}
