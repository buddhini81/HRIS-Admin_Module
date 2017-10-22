/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ha.exceptions;


/**
 *
 * @author Buddhini
 */
public class BusinessException extends Exception {

     public BusinessException() {
    }

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
