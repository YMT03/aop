package i.ramos.aop.exception;

import org.springframework.stereotype.Component;

@Component
public class ExceptionFactory {

    public MyException createExceptionWithMsg(String message){
        return new MyException(message);
    }

}
