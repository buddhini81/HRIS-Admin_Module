/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ha.exceptions;

/**
 *
 * @author Buddhini
 */
public class PasswordEncryptionSytemUnavailableException extends Exception {

    public PasswordEncryptionSytemUnavailableException() {
    }

    public PasswordEncryptionSytemUnavailableException(String msg) {
        super(msg);
    }

    public PasswordEncryptionSytemUnavailableException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public PasswordEncryptionSytemUnavailableException(Throwable cause) {
        super(cause);
    }
}
