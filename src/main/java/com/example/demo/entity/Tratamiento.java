package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Tratamiento {
    @Id
    private int id;
    private LocalDate Fecha;
    private String Procedimientos, Resultados, Medicamentos, Instrucciones_postoperatorias;

    @ManyToOne
    private TipoTratamiento tipoTratamiento;
    public LocalDate getFecha() {
        return Fecha;
    }
    public String getProcedimientos() {
        return Procedimientos;
    }
    public String getResultados() {
        return Resultados;
    }
    public String getMedicamentos() {
        return Medicamentos;
    }
    public String getInstrucciones_postoperatorias() {
        return Instrucciones_postoperatorias;
    }
    public TipoTratamiento getTipoTratamiento() {
        return tipoTratamiento;
    }
    public void setFecha(LocalDate fecha) {
        Fecha = fecha;
    }
    public void setProcedimientos(String procedimientos) {
        Procedimientos = procedimientos;
    }
    public void setResultados(String resultados) {
        Resultados = resultados;
    }
    public void setMedicamentos(String medicamentos) {
        Medicamentos = medicamentos;
    }
    public void setInstrucciones_postoperatorias(String instrucciones_postoperatorias) {
        Instrucciones_postoperatorias = instrucciones_postoperatorias;
    }
    public void setTipoTratamiento(TipoTratamiento tipoTratamiento) {
        this.tipoTratamiento = tipoTratamiento;
    }
    
    
}
