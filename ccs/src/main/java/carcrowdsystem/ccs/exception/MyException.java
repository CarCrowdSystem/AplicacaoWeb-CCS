package carcrowdsystem.ccs.exception;

public class MyException extends Exception {
    public Integer httpCode;
    public String message;
    public String errorCode;
    public MyException(Integer httpCode, String message, String errorCode) {
        this.httpCode = httpCode;
        this.message = message;
        this.errorCode = errorCode;
    }
}
