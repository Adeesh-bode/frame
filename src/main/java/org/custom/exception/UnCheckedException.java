package org.custom.exception;

class CustomUncheckedException extends RuntimeException {
    CustomUncheckedException(String message) {
        super(message);
    }
}

public class UnCheckedException {
    public static void main(String[] args) {

        System.out.println("Hello World");
        System.out.println("OK");

        if(true){
            throw new CustomUncheckedException("This is a Custom UncheckedException");
        }

        // this wont run---------
        System.out.println("End of World");
    }
}
