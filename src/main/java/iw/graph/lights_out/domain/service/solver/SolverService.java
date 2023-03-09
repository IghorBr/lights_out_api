package iw.graph.lights_out.domain.service.solver;

import iw.graph.lights_out.domain.model.Grafo;
import iw.graph.lights_out.domain.model.Solucao;

import java.util.Set;

public interface SolverService {

    Solucao solveLightsOut(Grafo grafo);
}
