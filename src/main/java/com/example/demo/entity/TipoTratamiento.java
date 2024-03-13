package com.example.demo.entity;

import jakarta.persistence.Id;

public class TipoTratamiento {
    @Id
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
