package finalBoss.evaluacionFinal.Repositories;

import finalBoss.evaluacionFinal.Entities.PremiosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PremiosRepository extends JpaRepository<PremiosEntity, Long> {
}
