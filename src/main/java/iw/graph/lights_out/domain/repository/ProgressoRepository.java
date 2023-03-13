package iw.graph.lights_out.domain.repository;

import iw.graph.lights_out.domain.model.Progresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressoRepository extends JpaRepository<Progresso, String> {
}
