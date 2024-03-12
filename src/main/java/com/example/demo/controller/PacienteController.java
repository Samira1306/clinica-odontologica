package com.example.demo.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Odontologo;
import com.example.demo.entity.Paciente;
import com.example.demo.repository.PacienteRepository;

@Controller
@RequestMapping(path = "/paciente")
public class PacienteController {
	@Autowired
	private PacienteRepository pacienteRepository;

	@PostMapping(path = "/new")
	public @ResponseBody String nuevo(@RequestParam String cedula, @RequestParam String nombres,
			@RequestParam String apellidos, @RequestParam LocalDate fecha_nacimiento, @RequestParam String residencia,
			@RequestParam String telefono, @RequestParam String email) {
		Paciente p = new Paciente();
		p.setCedula(cedula);
		p.setNombres(nombres);
		p.setApellidos(apellidos);
		p.setFecha_nacimiento(fecha_nacimiento);
		p.setResidencia(residencia);
		p.setTelefono(telefono);
		p.setEmail(email);

		pacienteRepository.save(p);
		return "Listo";
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Paciente> listarTodos() {
		return pacienteRepository.findAll();
	}

	@DeleteMapping(path = "/delete")
	public @ResponseBody String eliminar(@RequestParam String cedula) {
		if (pacienteRepository.existsById(cedula)) {
			pacienteRepository.deleteById(cedula);
			return "Paciente eliminado exitosamente";
		}

		return "El paciente con el ID proporcionado no existe";
	}

	@PostMapping(path = "/update")
	public @ResponseBody String actualizarDato(@RequestParam String cedula,
			@RequestParam(required = false) String nombres, @RequestParam(required = false) String apellidos,
			@RequestParam(required = false) String residencia,
			@RequestParam(required = false) LocalDate fecha_nacimiento, @RequestParam(required = false) String telefono,
			@RequestParam(required = false) String email) {

		Optional<Paciente> optionalPaciente = pacienteRepository.findById(cedula);
		if (optionalPaciente.isPresent()) {

			Paciente p = optionalPaciente.get();
			p.setCedula(cedula != null ? cedula : p.getCedula());
			p.setNombres(nombres != null ? nombres : p.getNombres());
			p.setApellidos(apellidos != null ? apellidos : p.getApellidos());
			p.setResidencia(residencia != null ? residencia : p.getResidencia());
			p.setFecha_nacimiento(fecha_nacimiento != null ? fecha_nacimiento : p.getFecha_nacimiento());
			p.setTelefono(telefono != null ? telefono : p.getTelefono());
			p.setEmail(email != null ? email : p.getEmail());

			pacienteRepository.save(p);
			return "Paciente actualizado exitosamente";
		} else {
			return "El paciente con el ID proporcionado no existe";
		}
	}
}
