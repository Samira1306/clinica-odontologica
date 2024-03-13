package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    private LocalDate fecha;
    private  LocalDateTime hora;
    private String motivoConsulta;


    
    public int getId() {
        return id;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public LocalDateTime getHora() {
        return hora;
    }
    public String getMotivoConsulta() {
        return motivoConsulta;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }
    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }
    
    
    

}
