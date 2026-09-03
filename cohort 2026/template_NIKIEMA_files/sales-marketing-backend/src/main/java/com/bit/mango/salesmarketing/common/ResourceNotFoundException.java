package com.bit.mango.salesmarketing.common;

/**
 * A dedicated exception for "the thing you asked for doesn't exist"
 * (e.g. GET /api/customers/999 when there is no customer #999).
 *
 * Right now, every Service's getById() throws a plain RuntimeException
 * with a message. That works, but Spring has no way to know "this
 * should become a 404 Not Found" vs "this is a real server bug" -
 * it just sees a generic RuntimeException and returns a scary 500
 * error with a full stack trace to whoever called the API (React,
 * in your case). Using this specific exception type instead lets us
 * tell Spring exactly how to respond - see GlobalExceptionHandler.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
