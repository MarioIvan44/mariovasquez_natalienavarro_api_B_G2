package finalBoss.evaluacionFinal.Models.DTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DTOPeliculas {

    private Long id_pelicula;
    private String titulo;
    private String director;
    private String genero;
    private Double ano_estreno;
    private Double duracion_min;
    private Date fecha_creacion;
}
