package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    private LocalDate fecha;
    private  LocalTime hora;
    private String motivoConsulta;
    private String estado;


    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getEstado() {
        return estado;
    }
    public int getId() {
        return id;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public LocalTime getHora() {
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
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }
    
    @ManyToOne
    @JoinColumn(name = "Paciente_ID") // El nombre de la columna que representa la clave foránea a Paciente
    private Paciente paciente;



    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    

}
