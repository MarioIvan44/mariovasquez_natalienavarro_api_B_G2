package finalBoss.evaluacionFinal.Controllers;

import finalBoss.evaluacionFinal.Exceptions.ExceptionPeliculaNoEncontrada;
import finalBoss.evaluacionFinal.Exceptions.ExceptionPremioConRegistrosRelacionados;
import finalBoss.evaluacionFinal.Exceptions.ExceptionPremioNoAgregado;
import finalBoss.evaluacionFinal.Exceptions.ExceptionPremioNoEncontrado;
import finalBoss.evaluacionFinal.Models.DTO.DTOPremios;
import finalBoss.evaluacionFinal.Services.PremiosService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiPremios")
@CrossOrigin
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

    // MÉTODO POST
    @PostMapping("/agregarPremio")
    public ResponseEntity<?> agregar(@Valid @RequestBody DTOPremios dto) {
        try {
            DTOPremios respuesta = service.agregar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "Éxito",
                    "data", respuesta,
                    "message", "Premio creado correctamente"
            ));
        } catch (ExceptionPeliculaNoEncontrada e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "Error",
                    "message", e.getMessage()
            ));
        } catch (ExceptionPremioNoAgregado e) {
            log.error("Error inesperado al agregar el premio", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error interno",
                    "error", e.getMessage(),
                    "message", "Error inesperado al registrar el premio"
            ));
        }
    }

    // MÉTODO PUT
    @PutMapping("/actualizarPremio/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,@Valid @RequestBody DTOPremios dto) {
        try {
            DTOPremios actualizado = service.actualizar(id, dto);
            return ResponseEntity.ok(Map.of(
                    "status", "Éxito",
                    "data", actualizado
            ));
        } catch (ExceptionPremioNoAgregado e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "Error",
                    "message", e.getMessage()
            ));
        }
    }

    // MÉTODO DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            service.eliminar(id);
            return ResponseEntity.ok(Map.of(
                    "status", "Éxito",
                    "message", "Premio eliminado correctamente"
            ));
        } catch (ExceptionPremioNoEncontrado e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "Error",
                    "message", e.getMessage()
            ));
        } catch (ExceptionPremioConRegistrosRelacionados e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "status", "Error",
                    "message", "El premio no se pudo eliminar porque tiene registros relacionados"
            ));
        } catch (Exception e) {
            log.error("Error inesperado al eliminar el premio", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error no controlado",
                    "message", "Error inesperado al eliminar el premio"
            ));
        }
    }
}
