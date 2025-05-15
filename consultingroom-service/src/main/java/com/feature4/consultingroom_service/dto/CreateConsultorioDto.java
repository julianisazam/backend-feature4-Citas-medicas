package com.feature4.consultingroom_service.dto;

public record CreateConsultorioDto(
        String numeroConsultorio,
        Integer tipoConsultorio_id,
        Integer sede_id
) {
}
