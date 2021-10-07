package com.opsu.exceptions;

public class EmptyDataBaseException extends Exception{
    public EmptyDataBaseException(String message, Throwable error){
        super(message, error);
    }
    public EmptyDataBaseException(String message){
        super(message);
    }
}
