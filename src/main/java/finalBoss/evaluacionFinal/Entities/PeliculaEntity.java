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
@Table(name = "PELICULAS")
public class PeliculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "peliculas_seq")
    @SequenceGenerator(name = "peliculas_seq", sequenceName = "SEQ_PELICULAS", allocationSize = 1)
    @Column(name = "ID_PELICULA")
    private Long id_pelicula;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DIRECTOR")
    private String director;

    @Column(name = "GENERO")
    private String genero;

    @Column(name = "ANO_ESTRENO")
    private Double ano_estreno;

    @Column(name = "DURACION_MIN")
    private Double duracion_min;

    @Column(name = "FECHA_CREACION")
    private Date fecha_creacion;
}
