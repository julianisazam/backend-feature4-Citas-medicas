package com.feature4.consultingroom_service;

import com.feature4.consultingroom_service.dto.ConsultorioResponseDTO;
import com.feature4.consultingroom_service.dto.CreateConsultorioDto;
import com.feature4.consultingroom_service.model.*;
import com.feature4.consultingroom_service.repository.ConsultorioRepository;
import com.feature4.consultingroom_service.service.ConsultorioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultorioServiceTest {

    @Mock
    private ConsultorioRepository consultorioRepository;

    @InjectMocks
    private ConsultorioService consultorioService;

    private Consultorio consultorio;
    private CreateConsultorioDto createConsultorioDto;

    @BeforeEach
    void setUp() {
        Ciudad ciudad = new Ciudad(1, "Bogotá");
        Sede sede = new Sede(1, "Sede Principal", ciudad);
        TipoConsultorio tipoConsultorio = new TipoConsultorio(1, "General");
        EstadoConsultorio estadoConsultorio = new EstadoConsultorio(1, "Disponible");

        consultorio = new Consultorio();
        consultorio.setId(1L);
        consultorio.setNumeroConsultorio("101");
        consultorio.setTipoConsultorio(tipoConsultorio);
        consultorio.setSedeId(sede);
        consultorio.setEstado(estadoConsultorio);

        createConsultorioDto = new CreateConsultorioDto(
                "101",
                1,
                1,
                1
        );
    }

    @Test
    void testGetAllConsultorios() {
        when(consultorioRepository.findAllWithRelations()).thenReturn(Collections.singletonList(consultorio));

        List<ConsultorioResponseDTO> result = consultorioService.getAllConsultorios();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(consultorioRepository, times(1)).findAllWithRelations();
    }

    @Test
    void testGetConsultorioById() {
        when(consultorioRepository.findByIdWithRelations(1L)).thenReturn(Optional.of(consultorio));

        ConsultorioResponseDTO result = consultorioService.getConsultorioById(1L);

        assertNotNull(result);
        assertEquals(1L, result.id_consultorio());
        verify(consultorioRepository, times(1)).findByIdWithRelations(1L);
    }

    @Test
    void testGetConsultorioByIdNotFound() {
       
        when(consultorioRepository.findByIdWithRelations(1L)).thenReturn(Optional.empty());

        ConsultorioResponseDTO result = consultorioService.getConsultorioById(1L);

        assertNull(result);
        verify(consultorioRepository, times(1)).findByIdWithRelations(1L);
    }

 @Test
void testUpdateConsultorio() {

    when(consultorioRepository.findById(1L)).thenReturn(Optional.of(consultorio));
    when(consultorioRepository.save(any(Consultorio.class))).thenReturn(consultorio);


    String result = consultorioService.updateConsultorio(1L, createConsultorioDto);

    assertEquals("Consultorio actualizado con ID: 1", result);
    verify(consultorioRepository, times(1)).findById(1L);
    verify(consultorioRepository, times(1)).save(any(Consultorio.class));
}


    @Test
    void testUpdateConsultorioWhenNotExists() {
        when(consultorioRepository.findById(1L)).thenReturn(Optional.empty());

        String result = consultorioService.updateConsultorio(1L, createConsultorioDto);

        assertEquals("Consultorio no encontrado con ID: 1", result);
        verify(consultorioRepository, times(1)).findById(1L);
        verify(consultorioRepository, never()).save(any(Consultorio.class));
    }

    @Test
    void testDeleteConsultorio() {
        when(consultorioRepository.findById(1L)).thenReturn(Optional.of(consultorio));
        doNothing().when(consultorioRepository).delete(consultorio);

        String result = consultorioService.deleteConsultorio(1L);

        assertEquals("Consultorio eliminado con ID: 1", result);
        verify(consultorioRepository, times(1)).findById(1L);
        verify(consultorioRepository, times(1)).delete(consultorio);
    }

    @Test
    void testDeleteConsultorioWhenNotExists() {
        when(consultorioRepository.findById(1L)).thenReturn(Optional.empty());

        String result = consultorioService.deleteConsultorio(1L);

        assertEquals("Consultorio no encontrado con ID: 1", result);
        verify(consultorioRepository, times(1)).findById(1L);
        verify(consultorioRepository, never()).delete(any(Consultorio.class));
    }
}