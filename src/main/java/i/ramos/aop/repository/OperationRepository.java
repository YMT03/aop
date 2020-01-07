package i.ramos.aop.repository;

import i.ramos.aop.bo.AvailableDays;
import i.ramos.aop.bo.OperationConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Repository
@Slf4j
public class OperationRepository {

    private List<OperationConfig> operationConfigs;

    @PostConstruct
    private void createDummyData(){
        log.info("Inserting dummy data into 'OperationRepository'");
        operationConfigs = new ArrayList<>();
        Random random = new Random();
        List<String> operationNames = Arrays.asList("BUY_USD","SELL_USD", "BUY_ARS", "SELL_ARS", "BUY_GAMES", "SELL_GAMES", "OTHER_OPERATION");
        operationNames.forEach((x)->{
            OperationConfig operationConfig = new OperationConfig();
            operationConfig.setAvailableDays(new AvailableDays(random.nextBoolean(),random.nextBoolean(),random.nextBoolean(),random.nextBoolean(),random.nextBoolean(),random.nextBoolean(),random.nextBoolean(),random.nextBoolean()));
            //operation.setAvailableHours(new AvailableHours());
            operationConfig.setStatus(random.nextBoolean()? OperationConfig.Status.ACTIVE: OperationConfig.Status.INACTIVE);
            operationConfig.setInactiveMessage(operationConfig.getStatus().equals(OperationConfig.Status.INACTIVE) && random.nextBoolean() ?"SOME MESSAGE EXPLAINING WHY THIS OPERATION IS DISABLED":null);
            operationConfig.setName(x);
            operationConfigs.add(operationConfig);
        });
        log.info("Dummy data created");
        operationConfigs.forEach(x->log.info(x.toString()));
    }

    public OperationConfig getByName(String name) throws Exception {
        return operationConfigs.stream().filter(x->x.getName().equals(name)).findFirst().orElseThrow(NotFoundException::new);
    }

    public List<OperationConfig> getAll(){
        return operationConfigs;
    }

    private static class NotFoundException extends RuntimeException{}

}
