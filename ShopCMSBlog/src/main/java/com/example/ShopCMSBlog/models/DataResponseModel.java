package com.example.ShopCMSBlog.models;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DataResponseModel<T> {
    private T data;
    private String message;
    private Long statusCode;

    public DataResponseModel(T data) {
        this.data = data;
    }

    public DataResponseModel(T data, String message, Long statusCode) {
        this.data = data;
        this.message = message;
        this.statusCode = (long) statusCode;
    }

    public static <T> DataResponseModel<T> of(T data, String message, Long statusCode) {
        return new DataResponseModel<>(data, message, statusCode);
    }
    public static <T> DataResponseModel<T> of(String message, Long statusCode) {
        return of(null, message, statusCode);
    }

}
