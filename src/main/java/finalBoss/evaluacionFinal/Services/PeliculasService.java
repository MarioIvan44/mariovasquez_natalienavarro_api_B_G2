package finalBoss.evaluacionFinal.Services;


import finalBoss.evaluacionFinal.Entities.PeliculaEntity;
import finalBoss.evaluacionFinal.Models.DTO.DTOPeliculas;
import finalBoss.evaluacionFinal.Repositories.PeliculasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeliculasService {

    @Autowired
    private PeliculasRepository repo;

    public List<DTOPeliculas> obtenerTodos(){
        List<PeliculaEntity> pelicula = repo.findAll();
        return pelicula.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public DTOPeliculas convertirADTO(PeliculaEntity entity){
        DTOPeliculas dto = new DTOPeliculas();
        dto.setId_pelicula(entity.getId_pelicula());
        dto.setTitulo(entity.getTitulo());
        dto.setDirector(entity.getDirector());
        dto.setGenero(entity.getGenero());
        dto.setAno_estreno(entity.getAno_estreno());
        dto.setDuracion_min(entity.getDuracion_min());
        dto.setFecha_creacion(entity.getFecha_creacion());
        return dto;
    }
}
