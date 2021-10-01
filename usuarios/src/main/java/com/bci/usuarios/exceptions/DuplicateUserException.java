package com.bci.usuarios.exceptions;



public class DuplicateUserException extends Exception{

    public DuplicateUserException() {
        super("Usuario duplicado");
    }
}
