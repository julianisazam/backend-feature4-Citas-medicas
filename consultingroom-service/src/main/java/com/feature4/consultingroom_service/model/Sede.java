package com.feature4.consultingroom_service.model;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre_sede", length = 50)
    private String nombre;
    @JoinColumn(name = "ciudad_id")
    @ManyToOne
    private Ciudad ciudad;

    // Constructor, getters, and setters
}
