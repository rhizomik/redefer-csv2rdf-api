package com.tfg.exceptions;

import org.apache.jena.base.Sys;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneralException extends Exception {

    public GeneralException(String message){
        super(
                "TIME : " + new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z").format(new Date(System.currentTimeMillis()))
                + "MESSAGE: " + message
        );
    }
}
