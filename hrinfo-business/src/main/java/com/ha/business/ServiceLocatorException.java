/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ha.business;

/**
 *
 * @author Buddhini
 */
public class ServiceLocatorException extends Exception {

    public ServiceLocatorException() {
    }

    public ServiceLocatorException(String msg) {
        super(msg);
    }

    public ServiceLocatorException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ServiceLocatorException(Throwable cause) {
        super(cause);
    }
}
