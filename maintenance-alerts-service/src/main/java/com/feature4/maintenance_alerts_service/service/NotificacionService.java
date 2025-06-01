package com.feature4.maintenance_alerts_service.service;

import com.feature4.maintenance_alerts_service.model.Notificacion;
import com.feature4.maintenance_alerts_service.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    public List<Notificacion> obtenerNotificacionesPorCoordinador(Long coordinadorId) {
        return notificacionRepository.findByCoordinadorUserIdOrderByFechaCreacionDesc(coordinadorId);
    }

    public List<Notificacion> obtenerNotificacionesNoLeidasPorCoordinador(Long coordinadorId) {
        return notificacionRepository.findByCoordinadorUserIdAndLeidaOrderByFechaCreacionDesc(coordinadorId, false);
    }

    @Transactional
    public void marcarComoLeida(Long notificacionId) {
        notificacionRepository.findById(notificacionId).ifPresent(notificacion -> {
            notificacion.setLeida(true);
            notificacionRepository.save(notificacion);
        });
    }
}