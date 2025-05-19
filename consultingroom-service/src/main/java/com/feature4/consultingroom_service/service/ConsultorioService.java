package com.feature4.consultingroom_service.service;

import com.feature4.consultingroom_service.dto.ConsultorioResponseDTO;
import com.feature4.consultingroom_service.dto.CreateConsultorioDto;
import com.feature4.consultingroom_service.model.Consultorio;
import com.feature4.consultingroom_service.model.EstadoConsultorio;
import com.feature4.consultingroom_service.model.Sede;
import com.feature4.consultingroom_service.model.TipoConsultorio;
import com.feature4.consultingroom_service.repository.ConsultorioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConsultorioService {
    private final ConsultorioRepository consultorioRepository;

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

    private Consultorio toEntity(CreateConsultorioDto consultorioDto) {
        Consultorio nuevoConsultorio = new Consultorio();
        nuevoConsultorio.setNumeroConsultorio(consultorioDto.numeroConsultorio());

        return nuevoConsultorio;
    }

    public List<ConsultorioResponseDTO> getAllConsultorios() {
        List<Consultorio> consultorios = consultorioRepository.findAll();

        return consultorios.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }



    public ConsultorioResponseDTO createConsultorio(CreateConsultorioDto consultorio) {
        TipoConsultorio tipoConsultorio = new TipoConsultorio();
        tipoConsultorio.setId(consultorio.tipoConsultorio_id());
        EstadoConsultorio estadoConsultorio = new EstadoConsultorio();
        estadoConsultorio.setId(consultorio.estado_id());
        Sede sede = new Sede();
        sede.setId(consultorio.sede_id());
        Consultorio nuevoConsultorio = new Consultorio();
        nuevoConsultorio.setNumeroConsultorio(consultorio.numeroConsultorio());
        nuevoConsultorio.setTipoConsultorio(tipoConsultorio);
        nuevoConsultorio.setEstado(estadoConsultorio);
        nuevoConsultorio.setSedeId(sede);
        Consultorio consultorioGuardado = consultorioRepository.save(nuevoConsultorio);
        return toResponseDto(consultorioGuardado);
    }

    public String updateConsultorio(Long id, CreateConsultorioDto consultorio) {
        Consultorio consultorioExistente = consultorioRepository.findById(id).orElse(null);
        if (consultorioExistente != null) {
            TipoConsultorio tipoConsultorio = new TipoConsultorio();
            tipoConsultorio.setId(consultorio.tipoConsultorio_id());
            EstadoConsultorio estadoConsultorio = new EstadoConsultorio();
            estadoConsultorio.setId(consultorio.estado_id());
            Sede sede = new Sede();
            sede.setId(consultorio.sede_id());
            consultorioExistente.setNumeroConsultorio(consultorio.numeroConsultorio());
            consultorioExistente.setTipoConsultorio(tipoConsultorio);
            consultorioExistente.setEstado(estadoConsultorio);
            consultorioExistente.setSedeId(sede);
            consultorioRepository.save(consultorioExistente);
            return "Consultorio actualizado con ID: " + id;
        } else {
            return "Consultorio no encontrado con ID: " + id;
        }
    }

    public String deleteConsultorio(Long id) {
        Consultorio consultorioExistente = consultorioRepository.findById(id).orElse(null);
        if (consultorioExistente != null) {
            consultorioRepository.delete(consultorioExistente);
            return "Consultorio eliminado con ID: " + id;
        } else {
            return "Consultorio no encontrado con ID: " + id;
        }
    }

    public ConsultorioResponseDTO getConsultorioById(Long id) {

        Consultorio consultorio = consultorioRepository.findById(id).orElse(null);
        if (consultorio != null) {
            return toResponseDto(consultorio);
        } else {
            return null; // O lanzar una excepción si lo prefieres
        }
    }
}
