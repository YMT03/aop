package i.ramos.aop.service;

import i.ramos.aop.annotation.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OtherService {

    @Operation("OTHER_OPERATION")
    public void otherOperation(){
        log.info("OTHER OPERATION IN PROGRESS");
    }

}
