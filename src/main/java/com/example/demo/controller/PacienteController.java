package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@GetMapping("/new_frontend")
	public String mostrarFormularioNuevo() {
		return "nuevoPaciente";
	}

	@PostMapping("/new_frontend")
	public String nuevo(@RequestParam("cedula") String cedula,
			@RequestParam("nombres") String nombres,
			@RequestParam("apellidos") String apellidos,
			@RequestParam("fecha_nacimiento") LocalDate fechaNacimiento,
			@RequestParam("residencia") String residencia,
			@RequestParam("telefono") String telefono,
			@RequestParam("email") String email,
			Model model) {
		Paciente paciente = new Paciente();
		paciente.setCedula(cedula);
		paciente.setNombres(nombres);
		paciente.setApellidos(apellidos);
		paciente.setFecha_nacimiento(fechaNacimiento);
		paciente.setResidencia(residencia);
		paciente.setTelefono(telefono);
		paciente.setEmail(email);

		pacienteRepository.save(paciente);

		model.addAttribute("mensaje", "Paciente creado correctamente");
		return "creacionPaciente";
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Paciente> listarTodos() {
		return pacienteRepository.findAll();
	}

	@GetMapping(path = "/all_paciente")
	public String listarTodos_frontend(Model modelo) {
		ArrayList<Paciente> lista = (ArrayList<Paciente>) pacienteRepository.findAll();
		modelo.addAttribute("pacientes", lista);
		return "ListarPacientes";
	}

	@DeleteMapping(path = "/delete")
	public @ResponseBody String eliminar(@RequestParam String cedula) {
		if (pacienteRepository.existsById(cedula)) {
			pacienteRepository.deleteById(cedula);
			return "Paciente eliminado exitosamente";
		}

		return "El paciente con el ID proporcionado no existe";
	}

	@GetMapping("/edit/{cedula}")
	public String mostrarFormularioEdicion(@PathVariable(name = "cedula") String cedula, Model model) {
		Optional<Paciente> optionalPaciente = pacienteRepository.findById(cedula);
		if (optionalPaciente.isPresent()) {
			Paciente paciente = optionalPaciente.get();
			model.addAttribute("paciente", paciente);
			return "actualizarPaciente"; // Nombre de la vista HTML para el formulario de edición
		} else {
			// Manejo del caso donde el paciente no se encuentra
			return "redirect:/paciente/all_paciente"; // Redirige a la lista de pacientes
		}
	}

	@PostMapping("/update")
	public String actualizarPaciente(@RequestParam String cedula, @RequestParam String nombres,
			@RequestParam String apellidos, @RequestParam LocalDate fecha_nacimiento, @RequestParam String residencia,
			@RequestParam String telefono, @RequestParam String email) {
		Optional<Paciente> optionalPaciente = pacienteRepository.findById(cedula);
		if (optionalPaciente.isPresent()) {
			Paciente paciente = optionalPaciente.get();
			paciente.setNombres(nombres);
			paciente.setApellidos(apellidos);
			paciente.setFecha_nacimiento(fecha_nacimiento);
			paciente.setResidencia(residencia);
			paciente.setTelefono(telefono);
			paciente.setEmail(email);
			pacienteRepository.save(paciente);
			return "redirect:/paciente/all_paciente"; // Redirige a la lista de pacientes
		} else {
			// Manejo del caso donde el paciente no se encuentra
			return "redirect:/paciente/all_paciente"; // Redirige a la lista de pacientes
		}
	}

}