package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.TipoTratamiento;
import com.example.demo.entity.Tratamiento;
import com.example.demo.repository.TratamientoRepository;

@Controller
@RequestMapping(path = "/tratamiento")
public class TratamientoController {
    @Autowired
    private TratamientoRepository tratamientorepository;
    
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

}
