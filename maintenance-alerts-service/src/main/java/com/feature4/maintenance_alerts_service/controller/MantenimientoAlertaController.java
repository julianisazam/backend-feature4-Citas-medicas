package com.feature4.maintenance_alerts_service.controller;

import com.feature4.maintenance_alerts_service.model.MantenimientoCreatedEvent;
import com.feature4.maintenance_alerts_service.service.MantenimientoAlertaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mantenimiento")
@RequiredArgsConstructor
public class MantenimientoAlertaController {

    private final MantenimientoAlertaService mantenimientoAlertaService;

    @PostMapping
    public ResponseEntity<String> programarMantenimiento(@RequestBody MantenimientoCreatedEvent evento) {
        mantenimientoAlertaService.programarMantenimiento(evento);
        return ResponseEntity.ok("Notificación de mantenimiento enviada correctamente");
    }
}