package com.example.ShopCMSBlog.exceptions;

import com.example.ShopCMSBlog.models.DataResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Long INTERNAL_SERVER_ERROR = 500L;
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleParseError(HttpMessageNotReadableException ex) {
        String msg = "JSON parse error: " + ex.getMostSpecificCause().getMessage();
        return ResponseEntity
                .badRequest()
                .body(Map.of("kl", "error", "msg", msg));
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<DataResponseModel> handleBaseException(AppException ex) {
        var errorModel = ex.getErrorModel();
        var messageError = errorModel !=null ? ex.getErrorModel().getMessage() : ex.getMessage();
        DataResponseModel dataResponse = new DataResponseModel(null, messageError, INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(dataResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DataResponseModel<String>> handleQuantityNotEnoughException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> errorFields = bindingResult.getFieldErrors();
        StringBuilder errorMessageBuilder = new StringBuilder();

        for (FieldError error : errorFields) {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errorMessageBuilder.append(fieldName).append(": ").append(errorMessage).append(", ");
        }

        String errorMessage = errorMessageBuilder.toString().replaceAll(", $", "");
        return new ResponseEntity(DataResponseModel.of(errorMessage,  INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

