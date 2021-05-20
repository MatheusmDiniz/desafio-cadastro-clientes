package com.desafio.domain.enums;

public enum TipoTelefone {

    RESIDENCIAL("RESIDENCIAL"),
    COMERCIAL("COMERCIAL"),
    CELULAR("CELULAR");

    private String tipo;

    TipoTelefone(String tipo) {
        this.tipo = tipo;
    }
    
}
