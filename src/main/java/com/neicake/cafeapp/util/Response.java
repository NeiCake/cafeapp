package com.neicake.cafeapp.util;

public class Response {
    private String message;
    private ResponseCode code;




    public enum ResponseCode{
        SUCCESS,
        FAILURE,
        INVALID_AMOUNT_TO_PURCHASE,
        INSUFFICIENT_STOCK,
        THERE_MUST_ALWAYS_BE_A_LICH_KING,
        INVALID_DATA
    }

    public Response() {
    }

    public Response(String message, ResponseCode code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }
}
