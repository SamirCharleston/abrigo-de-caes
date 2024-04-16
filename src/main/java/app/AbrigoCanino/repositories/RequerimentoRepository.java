package app.AbrigoCanino.repositories;

import app.AbrigoCanino.entities.RequerimentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequerimentoRepository extends JpaRepository<RequerimentoEntity, Long> {
}
