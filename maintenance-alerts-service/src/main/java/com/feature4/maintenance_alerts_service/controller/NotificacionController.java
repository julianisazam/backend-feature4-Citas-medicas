package com.feature4.maintenance_alerts_service.controller;

import com.feature4.maintenance_alerts_service.model.Notificacion;
import com.feature4.maintenance_alerts_service.service.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    @GetMapping("/coordinador/{coordinadorId}")
    public ResponseEntity<List<Notificacion>> obtenerNotificacionesPorCoordinador(@PathVariable Long coordinadorId) {
        List<Notificacion> notificaciones = notificacionService.obtenerNotificacionesPorCoordinador(coordinadorId);
        return ResponseEntity.ok(notificaciones);
    }

    @PutMapping("/{id}/marcar-leida")
    public ResponseEntity<Void> marcarComoLeida(@PathVariable Long id) {
        notificacionService.marcarComoLeida(id);
        return ResponseEntity.ok().build();
    }
}