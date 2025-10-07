package finalBoss.evaluacionFinal.Services;

import finalBoss.evaluacionFinal.Entities.PeliculaEntity;
import finalBoss.evaluacionFinal.Entities.PremiosEntity;
import finalBoss.evaluacionFinal.Exceptions.ExceptionPeliculaNoEncontrada;
import finalBoss.evaluacionFinal.Models.DTO.DTOPremios;
import finalBoss.evaluacionFinal.Repositories.PeliculasRepository;
import finalBoss.evaluacionFinal.Repositories.PremiosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PremiosService {

    @Autowired
    private PremiosRepository repo;

    @Autowired
    private PeliculasRepository peliRepo;

    public List<DTOPremios> obtenerTodos(){
        List<PremiosEntity> premios = repo.findAll();
        return premios.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public DTOPremios convertirADTO(PremiosEntity entity){
        DTOPremios dto = new DTOPremios();
        dto.setId_premio(entity.getId_premio());
        //Llave foránea
        if(entity.getId_pelicula() != null){
            dto.setId_pelicula(entity.getId_pelicula().getId_pelicula());
            //Otros atributos de la FK ..........
        }
        else {
            dto.setId_pelicula(null);
        }
        dto.setNombre_premio(entity.getNombre_premio());
        dto.setCategoria(entity.getCategoria());
        dto.setAno_premio(entity.getAno_premio());
        dto.setResultado(entity.getResultado());
        dto.setFecha_registro(entity.getFecha_registro());
        return dto;
    }

    public DTOPremios agregar(DTOPremios json){
        if(json == null){
            throw new IllegalArgumentException("No se puede crear un registro sin datos");
        }

        //Buscar la pelicula que viene en el json
        PeliculaEntity pelicula = peliRepo.findById(json.getId_pelicula())
                .orElseThrow(()-> new ExceptionPeliculaNoEncontrada("No se encontró la película con el id brindado"));

        try{
            return null;
        } catch (E)
    }
}
