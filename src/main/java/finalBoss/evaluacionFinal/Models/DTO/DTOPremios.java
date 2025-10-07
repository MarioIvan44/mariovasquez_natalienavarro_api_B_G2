package finalBoss.evaluacionFinal.Models.DTO;

import lombok.*;

import java.util.Date;

@Getter @Setter @EqualsAndHashCode @ToString
public class DTOPremios {

    private Long id_premio;
    private Long id_pelicula;
    private String nombre_premio;
    private String categoria;
    private Double ano_premio;
    private String resultado;
    private Date fecha_registro;
}
