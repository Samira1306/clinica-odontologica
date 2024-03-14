package com.example.demo.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Cita;
import com.example.demo.entity.Paciente;
import com.example.demo.repository.CitaRepository;
import com.example.demo.repository.PacienteRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping(path = "/citas")
public class CitasController {
    
    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;


    @PostMapping("/agendarCita")
public ResponseEntity<String> agendarCita(@RequestBody Map<String, Object> request) {
    LocalDate fecha = LocalDate.parse((String) request.get("fecha"));
    LocalTime hora = LocalTime.parse((String) request.get("hora"));
    String motivoConsulta = (String) request.get("motivoConsulta");
    String cedulaPaciente = (String) request.get("cedulaPaciente");

    // Buscar al paciente por su cédula
    Optional<Paciente> pacienteOptional = pacienteRepository.findByCedula(cedulaPaciente);
    if (pacienteOptional.isPresent()) {
        Paciente paciente = pacienteOptional.get();

        // Verificar si la hora está ocupada en la misma fecha
        List<Cita> citasEnLaMismaHora = citaRepository.findByFechaAndHora(fecha, hora);
        if (!citasEnLaMismaHora.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La hora seleccionada ya está ocupada.");
        }

        // Verificar el rango horario
        if ((hora.isBefore(LocalTime.of(8, 0)) || hora.isAfter(LocalTime.of(12, 0))) &&
            (hora.isBefore(LocalTime.of(14, 0)) || hora.isAfter(LocalTime.of(17, 0)))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La hora seleccionada no está dentro del rango permitido.");
        }

        // Crear la nueva cita
        Cita nuevaCita = new Cita();
        nuevaCita.setFecha(fecha);
        nuevaCita.setHora(hora);
        nuevaCita.setMotivoConsulta(motivoConsulta);
        nuevaCita.setEstado("CONFIRMADA");
        nuevaCita.setPaciente(paciente); 

        
        citaRepository.save(nuevaCita);

        return ResponseEntity.ok("Cita agendada correctamente.");
    } else {
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún paciente con la cédula especificada.");
    
    }
        
    
}
       
    @PostMapping("/cancelarCita")
    public ResponseEntity<String> cancelarCita(@RequestParam int citaId) {
    Optional<Cita> optionalCita = citaRepository.findById(citaId);
    if (optionalCita.isPresent()) {
        Cita cita = optionalCita.get();
        cita.setEstado("CANCELADA");
        citaRepository.save(cita);
        
        
        marcarHoraComoDisponible(cita.getFecha(), cita.getHora());
        
        return ResponseEntity.ok("Cita cancelada correctamente.");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la cita con el ID proporcionado.");
    }
}

private void marcarHoraComoDisponible(LocalDate fecha, LocalTime hora) {
    
    List<Cita> citasEnLaMismaHora = citaRepository.findByFechaAndHora(fecha, hora);
    if (!citasEnLaMismaHora.isEmpty()) {
        // Marcar la primera cita encontrada como disponible
        Cita cita = citasEnLaMismaHora.get(0);
        cita.setEstado("DISPONIBLE");
        citaRepository.save(cita);
    }

    

}

@GetMapping("/citasConfirm")
public ResponseEntity<List<Cita>> obtenerCitasConfirmadas() {
    List<Cita> citasConfirmadas = citaRepository.findByEstado("CONFIRMADA");
    return ResponseEntity.ok(citasConfirmadas);
}

    

}
