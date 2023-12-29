package com.example.bootcampisservice.exception;

import com.example.bootcampisservice.models.documents.ExceptionDocument;
import com.example.bootcampisservice.sampleResponse.IdentityResponse;
import com.example.bootcampisservice.services.ExceptionService;
import com.example.bootcampisservice.util.ResourceBundleUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {
    private final ResourceBundleUtil resourceBundleUtil;

    private final ExceptionService exceptionService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @Autowired
    public ControllerExceptionHandler(ResourceBundleUtil resourceBundleUtil, ExceptionService exceptionService) {
        this.resourceBundleUtil = resourceBundleUtil;
        this.exceptionService = exceptionService;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody IdentityResponse<?> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException exception, HttpServletRequest request) {
        String propertyName = exception.getPropertyName();
        Class<?> requiredType = exception.getRequiredType();

        ExceptionDocument exceptionDocument = new ExceptionDocument();
        exceptionDocument.setMessage("input parameter " + propertyName + " required this type : " + requiredType);
        exceptionDocument.setCode("internal.server.error");
        exceptionService.saveException(exceptionDocument);

        String lang = request.getHeader("lang");
        return IdentityResponse.builder()
                .message(resourceBundleUtil.getMessage("internal.server.error", lang))
                .code("internal.server.error")
                .date(new Date())
                .hasError(true)
                .data(null)
                .build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody IdentityResponse<?> handleMethodArgumentException(
            MethodArgumentNotValidException argumentNotValidException, HttpServletRequest request) {
        List<FieldError> fieldErrors = argumentNotValidException.getBindingResult().getFieldErrors();
        String code1 = "";
        for (FieldError fieldError : fieldErrors) {
            code1 = fieldError.getDefaultMessage();
        }

        if (code1 == null || code1.isEmpty()) {
            code1 = "Identity.method.argument.is.not.valid";
        }
        return getIdentityResponse(request, code1);
    }

    private IdentityResponse<?> getIdentityResponse(HttpServletRequest request, String code1) {
        String message1 = resourceBundleUtil.getMessage(code1, request.getHeader("lang"));

        ExceptionDocument exceptionDocument = new ExceptionDocument();
        exceptionDocument.setMessage(message1);
        exceptionDocument.setCode(code1);
        exceptionService.saveException(exceptionDocument);

        return IdentityResponse.builder()
                .message(message1)
                .code(code1)
                .date(new Date())
                .hasError(true)
                .data(null)
                .build();
    }

    @ExceptionHandler(IdentityServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody IdentityResponse<?> handleBankException(IdentityServiceException b, HttpServletRequest request) {
        String code2 = b.getMessage();

        return getIdentityResponse(request, code2);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody IdentityResponse<?> handleAnyException(Exception exception, HttpServletRequest request) {
        String lang = request.getHeader("lang");
        String message3 = resourceBundleUtil.getMessage("Identity.internal.server.error", lang);

        LOGGER.error("Error occurred in controller!", exception);

        ExceptionDocument exceptionDocument = new ExceptionDocument();
        exceptionDocument.setMessage(message3);
        exceptionDocument.setCode("Identity.internal.server.error");
        exceptionService.saveException(exceptionDocument);

        return IdentityResponse.builder()
                .message(message3)
                .code("Identity.internal.server.error")
                .date(new Date())
                .hasError(true)
                .data(null)
                .build();
    }
}
