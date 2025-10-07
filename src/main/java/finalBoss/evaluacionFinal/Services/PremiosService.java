package finalBoss.evaluacionFinal.Services;

import finalBoss.evaluacionFinal.Entities.PeliculaEntity;
import finalBoss.evaluacionFinal.Entities.PremiosEntity;
import finalBoss.evaluacionFinal.Exceptions.ExceptionPeliculaNoEncontrada;
import finalBoss.evaluacionFinal.Exceptions.ExceptionPremioConRegistrosRelacionados;
import finalBoss.evaluacionFinal.Exceptions.ExceptionPremioNoAgregado;
import finalBoss.evaluacionFinal.Exceptions.ExceptionPremioNoEncontrado;
import finalBoss.evaluacionFinal.Models.DTO.DTOPremios;
import finalBoss.evaluacionFinal.Repositories.PeliculasRepository;
import finalBoss.evaluacionFinal.Repositories.PremiosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
            //Otros atributos de la FK .........
            dto.setTitulo(entity.getId_pelicula().getTitulo());
            dto.setDirector(entity.getId_pelicula().getDirector());
            dto.setGenero(entity.getId_pelicula().getGenero());
            dto.setAno_estreno(entity.getId_pelicula().getAno_estreno());
            dto.setDuracion_min(entity.getId_pelicula().getDuracion_min());
            dto.setFecha_creacion(entity.getId_pelicula().getFecha_creacion());
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
            PremiosEntity entity = new PremiosEntity();
            entity.setId_premio(json.getId_premio());
            entity.setId_pelicula(pelicula);
            entity.setNombre_premio(json.getNombre_premio());
            entity.setCategoria(json.getCategoria());
            entity.setAno_premio(json.getAno_premio());
            entity.setResultado(json.getResultado());
            entity.setFecha_registro(json.getFecha_registro());

            PremiosEntity premioCreado = repo.save(entity);
            return convertirADTO(premioCreado);
        } catch (Exception e){
            throw new ExceptionPremioNoAgregado(e.getMessage());
        }
    }

    public DTOPremios actualizar(Long id, DTOPremios json){
        //Buscar la pelicula que viene en el json
        PremiosEntity premios = repo.findById(id)
                .orElseThrow(()-> new ExceptionPremioNoEncontrado("No se encontró el premio con el id brindado"));

        if (json.getId_pelicula() != null) {
                PeliculaEntity pelicula = peliRepo.findById(json.getId_pelicula())
                        .orElseThrow(() -> new ExceptionPeliculaNoEncontrada("Pelicula no encontrada con id " + json.getId_pelicula()));
                premios.setId_pelicula(pelicula);
        }

        premios.setNombre_premio(json.getNombre_premio());
        premios.setCategoria(json.getCategoria());
        premios.setAno_premio(json.getAno_premio());
        premios.setResultado(json.getResultado());
        premios.setFecha_registro(json.getFecha_registro());
        return convertirADTO(repo.save(premios));
    }

    public String eliminar(Long id) {
        PremiosEntity premio = repo.findById(id)
                .orElseThrow(() -> new ExceptionPremioNoEncontrado("No se encontró el premio con el id brindado"));
        try {
            repo.deleteById(id);
            return "Premio eliminado correctamente";
        } catch (DataIntegrityViolationException e) {
            throw new ExceptionPremioConRegistrosRelacionados("No se pudo eliminar el premio debido a que tiene registros relacionados");
        }
    }
}