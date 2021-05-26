package pers.avc.wechat.admin.exception;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pers.avc.wechat.admin.dto.ResultResp;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 方法参数校验, 针对于 RequestBody，以对象封装的参数
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultResp<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage(), e);
        return new ResultResp<String>(1,  e.getBindingResult().getFieldError().getDefaultMessage(), null);
    }


    @ExceptionHandler(ValidationException.class)
    public ResultResp<?> handleValidationException(ValidationException e){
        return new ResultResp<String>(1,  e.getCause().getMessage(), null);
    }

    /**
     * 单个参数，没有用对象封装
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultResp<?> handleConstraintViolationException(ConstraintViolationException e) {
       // logger.error(e.getMessage(), e);
        String msg = e.getConstraintViolations().stream().map(item -> (ConstraintViolationImpl)item)
                .map(ConstraintViolationImpl::getMessage).collect(Collectors.joining(","));
        return new ResultResp<>(1, msg, null);
    }

    /**
     * 对象封装的参数, 非Response
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResultResp<?> bindException(BindException e) {
        logger.error(e.getMessage(), e);
        List<String> message = e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResultResp<>(1,  "",message);
    }


    @ExceptionHandler(Exception.class)
    public ResultResp<?> commonException(Exception e) {
        logger.error(e.getMessage(), e);
        return new ResultResp<String>(1,  e.getCause().getMessage(), null);
    }
}
