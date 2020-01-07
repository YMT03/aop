package i.ramos.aop.aspect;

import i.ramos.aop.annotation.Operation;
import i.ramos.aop.bo.OperationConfig;
import i.ramos.aop.exception.ErrorMessage;
import i.ramos.aop.exception.ExceptionFactory;
import i.ramos.aop.repository.OperationRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class OperationAspect {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private ExceptionFactory exceptionFactory;

    @Around("@annotation(i.ramos.aop.annotation.Operation)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Operation operation = method.getAnnotation(Operation.class);
        log.info("Intercepting Operation " + operation.value());
        OperationConfig operationConfig = operationRepository.getByName(operation.value());
        validateOperation(operationConfig);
        return joinPoint.proceed();
    }

    private void validateOperation(OperationConfig operationConfig){
        validateStatus(operationConfig);
        validateDay(operationConfig);
        validateHoliday(operationConfig);
    }

    private void validateHoliday(OperationConfig operationConfig) {
        log.info("Validation here.. Checking if today is holiday and if its allowed");
    }

    private void validateDay(OperationConfig operationConfig) {
        log.info("Validation here.. Comparing current day with available days. Same logic");
    }

    private void validateStatus(OperationConfig operationConfig) {
        if(operationConfig.getStatus().equals(OperationConfig.Status.INACTIVE)){
            if (StringUtils.hasText(operationConfig.getInactiveMessage()))
                throw exceptionFactory.createExceptionWithMsg(operationConfig.getInactiveMessage());
            throw exceptionFactory.createExceptionWithMsg(ErrorMessage.DISABLED_OPERATION_DEFAULT_MSG);
        }
    }

}
