package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Tratamiento;

public interface TratamientoRepository extends CrudRepository<Tratamiento, Integer>{
    List<Tratamiento> findByTratamientoPorPaciente_PacienteId(String cedula);
}
