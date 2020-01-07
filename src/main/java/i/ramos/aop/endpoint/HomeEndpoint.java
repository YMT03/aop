package i.ramos.aop.endpoint;

import i.ramos.aop.service.BuyService;
import i.ramos.aop.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeEndpoint {

    @Autowired
    private BuyService buyService;

    @Autowired
    private OtherService otherService;

    @GetMapping("/test/{id}")
    public ResponseEntity doSomething(@PathVariable Integer id){
        switch (id){
            case 0:
                buyService.buyArs();
                break;
            case 1:
                buyService.buyUsd();
                break;
            case 2:
                buyService.sellArs();
                break;
            case 3:
                buyService.sellUsd();
                break;
            case 4:
                otherService.otherOperation();
                break;
        }
        return ResponseEntity.ok().build();
    }


}
