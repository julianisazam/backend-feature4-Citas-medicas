package com.feature4.consultingroom_service.model;

import com.feature4.consultingroom_service.enums.EstadoConsultorio;
import com.feature4.consultingroom_service.enums.TipoConsultorio;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_consultorio", length = 20)
    private TipoConsultorio tipoConsultorio;

    @JoinColumn(name = "sede_id")
    @ManyToOne
    private Sede sedeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoConsultorio estado;


}
