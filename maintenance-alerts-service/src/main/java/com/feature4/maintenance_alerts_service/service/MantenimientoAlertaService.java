package com.feature4.maintenance_alerts_service.service;

import com.feature4.maintenance_alerts_service.model.MantenimientoCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MantenimientoAlertaService {

    private final KafkaProducerService kafkaProducerService;

    public void programarMantenimiento(MantenimientoCreatedEvent evento) {
        // Aquí podría ir lógica adicional antes de enviar el evento
        kafkaProducerService.enviarEventoMantenimiento(evento);
    }
}