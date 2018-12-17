package com.amruta.exception;

/**
 * Created by amrutaj on 17/12/2018.
 */
public class ItemPairFetcherException extends Exception{

    public ItemPairFetcherException(String message) {
        super(message);
    }

    public ItemPairFetcherException(String message, Throwable cause) {
        super(message, cause);
    }
}
