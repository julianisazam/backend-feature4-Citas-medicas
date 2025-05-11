package com.feature4.consultingroom_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TipoConsultorio {

    @Id
    private Long id;
    private String nombre;
}
