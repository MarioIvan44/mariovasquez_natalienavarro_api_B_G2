package finalBoss.evaluacionFinal.Models.DTO;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter @Setter @EqualsAndHashCode @ToString
public class DTOPremios {

    private Long id_premio;

    @Positive(message = "El id de la película no debe ser negativo")
    private Long id_pelicula;
    //otros campos de pk
    private String titulo;
    private String director;
    private String genero;
    private Double ano_estreno;
    private Double duracion_min;
    private Date fecha_creacion;


    @Size(max = 120,message = "El nombre premio no puede superar los 120 caracteres")
    private String nombre_premio;

    @Size(max = 120,message = "La categoria no puede superar los 120 caracteres")
    private String categoria;

    @Positive(message = "El año del premio no debe ser negativo y debe tener solo 4 caracteres")
    private Double ano_premio;

    @Size(max = 120,message = "La categoria no puede superar los 120 caracteres")
    private String resultado;

    private Date fecha_registro;
}
