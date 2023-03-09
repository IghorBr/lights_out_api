package iw.graph.lights_out.domain.service.solver;

import iw.graph.lights_out.domain.model.Grafo;
import iw.graph.lights_out.domain.model.Solucao;
import iw.graph.lights_out.domain.model.Vertice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ForcaBrutaSolverService implements SolverService {

    private static final Logger logger = LoggerFactory.getLogger(ForcaBrutaSolverService.class);

    @Override
    public Solucao solveLightsOut(Grafo grafo) {
        var count = 1 << grafo.getVertices().size();
        var stringBuilder = new StringBuilder();
        Solucao solucao = new Solucao();

        while (count > 0) {
            grafo.reset();
            stringBuilder.delete(0, stringBuilder.length());

            for (int i = 0; i < grafo.getVertices().size(); i++) {
                if ((count & (1 << i)) != 0) {
                    Vertice vertice = grafo.getVertices().get(i);
                    stringBuilder.append(vertice.getCode() + " - ");
                    vertice.click();
                }
            }

            if (grafo.isSolved()) {
                logger.info(stringBuilder.toString());
                solucao.addSolucao(stringBuilder.toString());
            }

            count --;
        }

        return solucao;
    }
}
