package com.feature4.maintenance_alerts_service;

import com.feature4.maintenance_alerts_service.controller.MantenimientoAlertaController;
import com.feature4.maintenance_alerts_service.model.MantenimientoCreatedEvent;
import com.feature4.maintenance_alerts_service.service.MantenimientoAlertaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ManintenanceAlertControllerTest {

    @Mock
    private MantenimientoAlertaService mantenimientoAlertaService;

    @InjectMocks
    private MantenimientoAlertaController mantenimientoAlertaController;

    @Test
    void testProgramarMantenimiento_DebeLlamarAlServicioYRetornarOk() {
        // Arrange (Preparar)
        MantenimientoCreatedEvent evento = new MantenimientoCreatedEvent(
                101L, // consultorioId
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2),
                "Mantenimiento preventivo de equipo",
                5L // coordinadorUserId
        );

        doNothing().when(mantenimientoAlertaService).programarMantenimiento(any(MantenimientoCreatedEvent.class));

        ResponseEntity<String> respuesta = mantenimientoAlertaController.programarMantenimiento(evento);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals("Notificación de mantenimiento enviada correctamente", respuesta.getBody());

        verify(mantenimientoAlertaService, times(1)).programarMantenimiento(evento);
    }
}