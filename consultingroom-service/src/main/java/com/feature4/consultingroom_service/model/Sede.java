package com.feature4.consultingroom_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Sede {
    @Id
    private Long id;
    @Column(name = "nombre_sede", length = 50)
    private String nombre;
    private String ciudad;

    // Constructor, getters, and setters
}
