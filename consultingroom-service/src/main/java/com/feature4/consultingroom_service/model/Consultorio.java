package com.feature4.consultingroom_service.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Consultorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "consultorio_id",
            unique = true
    )
    private Long id;

    @Column(name = "numero_consultorio", length = 10)
    private String numeroConsultorio;

    @Column(name = "tipo_consultorio", length = 20)
    @ManyToOne
    private TipoConsultorio tipoConsultorio;

    @JoinColumn(name = "sede_id")
    @ManyToOne
    private Sede sedeId;

    @Column(name = "estado")
    @ManyToOne
    private EstadoConsultorio estado;


}
