package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, String> {
    Optional<Paciente> findByCedula(String cedula);
}
