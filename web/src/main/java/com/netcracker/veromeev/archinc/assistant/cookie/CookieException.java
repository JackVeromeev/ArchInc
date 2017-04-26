package com.netcracker.veromeev.archinc.assistant.cookie;

/**
 * Created by jack on 26/04/17.
 *
 * @author Jack Veromeyev
 */
public class CookieException extends Exception {

    CookieException(String message) {
        super(message);
    }

    CookieException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return "CookieException: " + getMessage() + "  " + getCause();
    }
}
