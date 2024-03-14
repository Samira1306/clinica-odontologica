package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Paciente;
import com.example.demo.entity.TipoTratamiento;
import com.example.demo.entity.Tratamiento;
import com.example.demo.entity.TratamientoPorPaciente;
import com.example.demo.repository.PacienteRepository;
import com.example.demo.repository.TratamientoPorPacienteRepository;
import com.example.demo.repository.TratamientoRepository;

@Controller
@RequestMapping(path = "/tratamiento")
public class TratamientoController {
    @Autowired
    private TratamientoRepository tratamientorepository;
    
    @Autowired
    private TratamientoPorPacienteRepository tratamientoPorPacienteRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    
    @PostMapping(path = "/new")
    public @ResponseBody String nuevoTratamiento(@RequestParam LocalDate fecha,
                                                 @RequestParam String Procedimientos,
                                                 @RequestParam String Resultados,
                                                 @RequestParam String Medicamentos,
                                                 @RequestParam String Instrucciones_postoperatorias,
                                                 @RequestParam TipoTratamiento TipoTratamiento){
        Tratamiento t = new Tratamiento();
        t.setFecha(fecha);
        t.setProcedimientos(Procedimientos);
        t.setResultados(Resultados);
        t.setMedicamentos(Medicamentos);
        t.setInstrucciones_postoperatorias(Instrucciones_postoperatorias);
        t.setTipoTratamiento(TipoTratamiento);

        tratamientorepository.save(t);
		return "Listo";

    }

    @PostMapping(path = "/update")
	public @ResponseBody String actualizarDato(@RequestParam int id,
                                                @RequestParam(required = false) LocalDate fecha,
                                                 @RequestParam(required = false) String Procedimientos,
                                                 @RequestParam(required = false) String Resultados,
                                                 @RequestParam(required = false) String Medicamentos,
                                                 @RequestParam(required = false) String Instrucciones_postoperatorias,
                                                 @RequestParam(required = false) TipoTratamiento TipoTratamiento) {

		Optional<Tratamiento> optionalTratamiento = tratamientorepository.findById(id);
		if (optionalTratamiento.isPresent()) {

			Tratamiento tratamiento = optionalTratamiento.get();
			tratamiento.setFecha(fecha != null ? fecha : tratamiento.getFecha());
			tratamiento.setProcedimientos(Procedimientos != null ? Procedimientos : tratamiento.getProcedimientos());
			tratamiento.setResultados(Resultados != null ? Resultados : tratamiento.getResultados());
			tratamiento.setMedicamentos(Medicamentos != null ? Medicamentos : tratamiento.getMedicamentos());
			tratamiento.setInstrucciones_postoperatorias(Instrucciones_postoperatorias != null ? Instrucciones_postoperatorias : tratamiento.getInstrucciones_postoperatorias());
            tratamiento.setTipoTratamiento(TipoTratamiento != null? TipoTratamiento: tratamiento.getTipoTratamiento());

			tratamientorepository.save(tratamiento);
			return "Odontólogo actualizado exitosamente";
		} else {
			return "El odontólogo con el ID proporcionado no existe";
		}
	}

}
