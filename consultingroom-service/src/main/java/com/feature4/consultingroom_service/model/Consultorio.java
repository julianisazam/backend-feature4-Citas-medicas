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

    @JoinColumn(name = "tipo_consultorio_id")
    @ManyToOne
    private TipoConsultorio tipoConsultorio;

    @JoinColumn(name = "sede_id")
    @ManyToOne
    private Sede sedeId;

    @JoinColumn(name = "estado_id")
    @ManyToOne
    private EstadoConsultorio estado;


}
