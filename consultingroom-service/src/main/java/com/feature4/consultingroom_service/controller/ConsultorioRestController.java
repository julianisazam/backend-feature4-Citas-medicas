package com.feature4.consultingroom_service.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultorio")
public class ConsultorioRestController {
    // todo: Hacer los getters en graphql
    @GetMapping
    public String getConsultorio() {
        return "Todos los consultorios";
    }

    // todo: Hacer los getters en graphql
    @GetMapping("/{id}")
    public String getConsultorioById(@PathVariable("id") Long id) {
        return "Consultorio con ID: " + id;
    }

    @PostMapping
    public String createConsultorio(@RequestBody String consultorio) {
        return "Consultorio creado: " + consultorio;
    }

    @PutMapping("/{id}")
    public String updateConsultorio(@PathVariable("id") Long id, @RequestBody String consultorio) {
        return "Consultorio actualizado con ID: " + id + ", nuevo valor: " + consultorio;
    }

    @DeleteMapping("/{id}")
    public String deleteConsultorio(@PathVariable("id") Long id) {
        return "Consultorio eliminado con ID: " + id;
    }
}
