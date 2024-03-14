package com.example.demo.entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;

@Entity
public class TratamientoPorPaciente {
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "paciente_ID")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "tratamiento_ID")
    private Tratamiento tratamiento;

    @ManyToOne
    @JoinColumn(name = "Odontologo_ID")
    private Odontologo odontologo;
}
