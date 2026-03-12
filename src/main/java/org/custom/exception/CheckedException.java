package org.custom.exception;

class CustomCheckedException extends Exception{
    CustomCheckedException(String message){
        super(message);
    }
}

public class CheckedException {
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
