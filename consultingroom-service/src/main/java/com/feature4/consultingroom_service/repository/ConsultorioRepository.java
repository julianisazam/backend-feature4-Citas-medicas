package com.feature4.consultingroom_service.repository;

import com.feature4.consultingroom_service.model.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {
    boolean existsByNumeroConsultorioAndSedeId_Id(String numeroConsultorio, Long sedeId);
    @Query("SELECT c FROM Consultorio c " +
            "LEFT JOIN FETCH c.sedeId s " +
            "LEFT JOIN FETCH s.ciudad " +
            "LEFT JOIN FETCH c.tipoConsultorio " +
            "LEFT JOIN FETCH c.estado " +
            "WHERE c.id = :id")
    Optional<Consultorio> findByIdWithRelations(@Param("id") Long id);
}
