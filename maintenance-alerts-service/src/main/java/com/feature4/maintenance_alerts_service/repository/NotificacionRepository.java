package com.feature4.maintenance_alerts_service.repository;


import com.feature4.maintenance_alerts_service.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    List<Notificacion> findByCoordinadorUserIdOrderByFechaCreacionDesc(Long coordinadorUserId);

    List<Notificacion> findByCoordinadorUserIdAndLeidaOrderByFechaCreacionDesc(Long coordinadorUserId, boolean leida);
}