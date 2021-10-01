package com.bci.usuarios.utils;

public enum Constants {

    ERROR001("El usuario ingresado ya se encuentra registrado en el sistema"),
    ERROR002("El email ingresado ya se encuentra registrado en el sistema."),
    DEFAULT_ERROR("Error en la operación por favor comunicarse con soporte.");


    private final String value;

    Constants(String value) {
        this.value = value;
    }
    public String value() {
        return this.value;
    }

}