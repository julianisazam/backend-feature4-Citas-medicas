package com.feature4.maintenance_alerts_service.service;

import com.feature4.maintenance_alerts_service.model.MantenimientoCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private KafkaTemplate<String, MantenimientoCreatedEvent> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, MantenimientoCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarEventoMantenimiento(MantenimientoCreatedEvent evento) {
        kafkaTemplate.send("mantenimiento-creado", evento);
    }
}

