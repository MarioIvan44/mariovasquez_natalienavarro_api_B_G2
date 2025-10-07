package finalBoss.evaluacionFinal.Repositories;

import finalBoss.evaluacionFinal.Entities.PeliculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculasRepository extends JpaRepository<PeliculaEntity, Long> {
}
