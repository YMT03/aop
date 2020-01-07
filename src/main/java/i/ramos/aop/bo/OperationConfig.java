package i.ramos.aop.bo;

import lombok.Data;


@Data
public class OperationConfig {

    private String name;
    private AvailableDays availableDays;
    private AvailableHours availableHours;
    private Boolean auditable;
    private String inactiveMessage;
    private Status status;

    public enum Status{
        ACTIVE, INACTIVE
    }

}
