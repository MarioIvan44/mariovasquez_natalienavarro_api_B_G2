package finalBoss.evaluacionFinal.Controllers;

import finalBoss.evaluacionFinal.Models.DTO.DTOPeliculas;
import finalBoss.evaluacionFinal.Models.DTO.DTOPremios;
import finalBoss.evaluacionFinal.Services.PeliculasService;
import finalBoss.evaluacionFinal.Services.PremiosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/apiPeliculas")
public class PeliculasController {

    @Autowired
    private PeliculasService service;

    @GetMapping("/obtenerPeliculas")
    ResponseEntity<?> obtenerTodos(){
        try {
            List<DTOPeliculas> peliculas = service.obtenerTodos();
            return ResponseEntity.ok(peliculas);
        }
        catch (Exception e){
            log.error("Ocurrió un error al obtener las películas", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error",
                    "message", "Error no controlado al obtener las películas"
            ));
        }
    }
}
