package iw.graph.lights_out.domain.service.solver;

import iw.graph.lights_out.domain.model.Grafo;
import org.springframework.scheduling.annotation.Async;

public interface SolverService {

    void solveLightsOut(Grafo grafo);
}
