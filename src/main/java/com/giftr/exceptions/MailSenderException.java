package com.giftr.exceptions;

public class MailSenderException extends Exception {

    public MailSenderException() {}

    public MailSenderException(String message) {
        super(message);
    }

    public MailSenderException(Throwable cause) {
        super (cause);
    }

    public MailSenderException(String message, Throwable cause) {
        super (message, cause);
    }

}
