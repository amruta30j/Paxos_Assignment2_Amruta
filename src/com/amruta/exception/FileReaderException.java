package com.amruta.exception;

/**
 * Created by amrutaj on 12/12/2018.
 */
public class FileReaderException extends Exception {

    /** Customised Exception class to deal with
     * the exceptions occured while reading the file.
     *
     * @param message
     */

    public FileReaderException(String message) {
        super(message);
    }

    public FileReaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
