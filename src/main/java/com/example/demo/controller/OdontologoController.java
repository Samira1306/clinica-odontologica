package com.example.demo.controller;

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
import com.example.demo.repository.OdontologoRepository;

@Controller
@RequestMapping(path = "/odontologo")
public class OdontologoController {

	@Autowired
	private OdontologoRepository odontologoRepository;

	@PostMapping(path = "/new")
	public @ResponseBody String nuevo(@RequestParam String nombres, @RequestParam String apellidos,
			@RequestParam String especialidad, @RequestParam String telefono, @RequestParam String email) {
		Odontologo o = new Odontologo();
		o.setNombres(nombres);
		o.setApellidos(apellidos);
		o.setEspecialidad(especialidad);
		o.setTelefono(telefono);
		o.setEmail(email);

		odontologoRepository.save(o);
		return "Listo";
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Odontologo> listarTodos() {
		return odontologoRepository.findAll();
	}

	@DeleteMapping(path = "/delete")
	public @ResponseBody String eliminar(@RequestParam int id) {
		if (odontologoRepository.existsById(id)) {
			odontologoRepository.deleteById(id);
			return "Odontólogo eliminado exitosamente";
		}

		return "El odontólogo con el ID proporcionado no existe";
	}

	@PostMapping(path = "/update")
	public @ResponseBody String actualizarDato(@RequestParam int id, @RequestParam(required = false) String nombres,
			@RequestParam(required = false) String apellidos, @RequestParam(required = false) String especialidad,
			@RequestParam(required = false) String telefono, @RequestParam(required = false) String email) {

		Optional<Odontologo> optionalOdontologo = odontologoRepository.findById(id);
		if (optionalOdontologo.isPresent()) {

			Odontologo odontologo = optionalOdontologo.get();
			odontologo.setNombres(nombres != null ? nombres : odontologo.getNombres());
			odontologo.setApellidos(apellidos != null ? apellidos : odontologo.getApellidos());
			odontologo.setEspecialidad(especialidad != null ? especialidad : odontologo.getEspecialidad());
			odontologo.setTelefono(telefono != null ? telefono : odontologo.getTelefono());
			odontologo.setEmail(email != null ? email : odontologo.getEmail());

			odontologoRepository.save(odontologo);
			return "Odontólogo actualizado exitosamente";
		} else {
			return "El odontólogo con el ID proporcionado no existe";
		}
	}

	@GetMapping(path = "/filter")
	public @ResponseBody Iterable<Odontologo> listarPorEspecialidad(@RequestParam String especialidad) {
		return odontologoRepository.findByEspecialidad(especialidad);
	}
}
