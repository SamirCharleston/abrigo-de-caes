package app.AbrigoCanino.repositories;

import app.AbrigoCanino.entities.AdocaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

public interface AdocaoRepository extends JpaRepository<AdocaoEntity, Long> {


}
