package app.AbrigoCanino.repositories;

import app.AbrigoCanino.entities.ColaboradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<ColaboradorEntity, Long> {
}