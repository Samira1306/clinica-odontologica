package com.example.demo.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Cita;

public interface CitaRepository extends JpaRepository<Cita, Integer>{
     List<Cita> findByFechaAndHora(LocalDate fecha, LocalTime hora);
     List<Cita> findByEstado(String estado);
}
