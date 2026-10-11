package com.example.LoanMatrix.exception;

public class EligibilityDecisionAlreadyMadeException extends RuntimeException {
    public EligibilityDecisionAlreadyMadeException(String message) {
        super(message);
    }
}
