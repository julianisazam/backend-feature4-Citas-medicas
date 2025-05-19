package com.feature4.consultingroom_service.controller;

import com.feature4.consultingroom_service.dto.ConsultorioResponseDTO;
import com.feature4.consultingroom_service.model.Consultorio;
import com.feature4.consultingroom_service.service.ConsultorioService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class ConsultorioGraphController {
    private final ConsultorioService consultorioService;

    @QueryMapping("getAllConsultorios")
    public List<ConsultorioResponseDTO> getAllConsultorios() {
        return consultorioService.getAllConsultorios();
    }

    @QueryMapping("getConsultorioById")
    public ConsultorioResponseDTO getConsultorioById(@Argument Long id) {
        return consultorioService.getConsultorioById(id);
    }

}
