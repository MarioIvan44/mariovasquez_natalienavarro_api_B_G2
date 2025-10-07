package finalBoss.evaluacionFinal.Controllers;

import finalBoss.evaluacionFinal.Models.DTO.DTOPremios;
import finalBoss.evaluacionFinal.Services.PremiosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiPremios")
@Slf4j
public class PremiosController {
    @Autowired
    private PremiosService service;

    @GetMapping("/obtenerPremios")
    ResponseEntity<?> obtenerTodos(){
        try {
            List<DTOPremios> premios = service.obtenerTodos();
            return ResponseEntity.ok(premios);
        }
        catch (Exception e){
           log.error("Ocurrió un error al obtener los premios", e);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                   "status", "Error",
                   "message", "Error no controlado al obtener los premios"
           ));
        }
    }


}
