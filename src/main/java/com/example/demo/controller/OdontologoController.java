package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/new_frontend")
	public String mostrarFormularioNuevo() {
		return "nuevoOdontologo";
	}

	@PostMapping("/new_frontend")
	public String nuevo(@RequestParam("nombres") String nombres,
			@RequestParam("apellidos") String apellidos,
			@RequestParam("especialidad") String especialidad,
			@RequestParam("telefono") String telefono,
			@RequestParam("email") String email,
			Model model) {
		Odontologo o = new Odontologo();
		o.setNombres(nombres);
		o.setApellidos(apellidos);
		o.setEspecialidad(especialidad);
		o.setTelefono(telefono);
		o.setEmail(email);

		odontologoRepository.save(o);

		model.addAttribute("mensaje", "Odontólogo creado correctamente");
		return "creaciónOdontologo";
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Odontologo> listarTodos() {
		return odontologoRepository.findAll();
	}

	@GetMapping(path = "/all_Odontologo")
	public String listarTodos_frontend(Model modelo) {
		ArrayList<Odontologo> lista = (ArrayList<Odontologo>) odontologoRepository.findAll();
		modelo.addAttribute("odontologos", lista);
		return "listarOdontologo";
	}

	@GetMapping("/delete/{id}")
	public String mostrarConfirmacionEliminar(@PathVariable int id, Model model) {
		Odontologo odontologo = odontologoRepository.findById(id).get();
		model.addAttribute("odontologo", odontologo);
		return "EliminarOdontologo";
	}

	@PostMapping("/delete")
	public String eliminar(@RequestParam int id) {
		odontologoRepository.deleteById(id);
		return "redirect:/odontologo/all_frontend";
	}

	@GetMapping("/update/{id}")
	public String mostrarFormularioActualizar(@PathVariable int id, Model model) {
		Optional<Odontologo> optionalOdontologo = odontologoRepository.findById(id);
		if (optionalOdontologo.isPresent()) {
			Odontologo odontologo = optionalOdontologo.get();
			model.addAttribute("odontologo", odontologo);
			return "actualizarOdontologo";
		} else {
			return "redirect:/odontologo/all_frontend";
		}
	}

	@PostMapping("/update")
	public String actualizar(@RequestParam int id, @RequestParam String nombres,
			@RequestParam String apellidos, @RequestParam String especialidad,
			@RequestParam String telefono, @RequestParam String email) {
		Optional<Odontologo> optionalOdontologo = odontologoRepository.findById(id);
		if (optionalOdontologo.isPresent()) {
			Odontologo odontologo = optionalOdontologo.get();
			odontologo.setNombres(nombres);
			odontologo.setApellidos(apellidos);
			odontologo.setEspecialidad(especialidad);
			odontologo.setTelefono(telefono);
			odontologo.setEmail(email);

			odontologoRepository.save(odontologo);
			return "redirect:/odontologo/all_Odontologo";
		} else {
			return "redirect:/odontologo/all_Odontologo";
		}
	}

	@GetMapping(path = "/filter")
	public @ResponseBody Iterable<Odontologo> listarPorEspecialidad(@RequestParam String especialidad) {
		return odontologoRepository.findByEspecialidad(especialidad);
	}
}
