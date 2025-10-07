package finalBoss.evaluacionFinal.Entities;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Table(name = "PREMIOS")
public class PremiosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "premios_seq")
    @SequenceGenerator(name = "premios_seq", sequenceName = "SEQ_PREMIOS", allocationSize = 1)
    @Column(name = "ID_PREMIO")
    private Long id_premio;

    @ManyToOne
    @JoinColumn(name = "ID_PELICULA", referencedColumnName = "ID_PELICULA")
    private PeliculaEntity id_pelicula;

    @Column(name = "NOMBRE_PREMIO")
    private String nombre_premio;

    @Column(name = "CATEGORIA")
    private String categoria;

    @Column(name = "ANO_PREMIO")
    private Double ano_premio;

    @Column(name = "RESULTADO")
    private String resultado;

    @Column(name = "FECHA_REGISTRO")
    private Date fecha_registro;
}
