package com.feature4.consultingroom_service.service;

import com.feature4.consultingroom_service.dto.ConsultorioResponseDTO;
import com.feature4.consultingroom_service.model.Consultorio;
import com.feature4.consultingroom_service.model.EstadoConsultorio;
import com.feature4.consultingroom_service.model.TipoConsultorio;
import com.feature4.consultingroom_service.repository.ConsultorioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ConsultorioService {
    private final ConsultorioRepository consultorioRepository;
    public List<ConsultorioResponseDTO> getAllConsultorios() {
        List<Consultorio> consultorios = consultorioRepository.findAll();

        return consultorios.stream()
                .map(this::toResponseDto)
                .toList();
    }

    private ConsultorioResponseDTO toResponseDto(Consultorio consultorio) {
            TipoConsultorio tipoConsultorio = consultorio.getTipoConsultorio();
            EstadoConsultorio estadoConsultorio = consultorio.getEstado();
        return new ConsultorioResponseDTO(
                consultorio.getId(),
                consultorio.getNumeroConsultorio(),
                tipoConsultorio.getNombre(),
                estadoConsultorio.getEstado(),
                consultorio.getSedeId().getNombre(),
                consultorio.getSedeId().getCiudad().getNombre_ciudad()
        );
    }

}
