package iw.graph.lights_out.domain.service.solver;

import iw.graph.lights_out.domain.model.Grafo;
import iw.graph.lights_out.domain.model.Progresso;
import iw.graph.lights_out.domain.model.Vertice;
import iw.graph.lights_out.domain.service.ProgressoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class ForcaBrutaSolverService implements SolverService {

    private static final Logger logger = LoggerFactory.getLogger(ForcaBrutaSolverService.class);
    private final ProgressoService progressoService;

    @Override
    @Async
    public void solveLightsOut(Grafo grafo) {
        var count = 1 << grafo.getVertices().size();
        var stringBuilder = new StringBuilder();
        var totalIteracoes = Math.pow(2, grafo.getVertices().size());
        var contador = 0;

        Progresso progresso = new Progresso(grafo.getCode());
        progresso = progressoService.save(progresso);

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
                progresso.addSolucao(stringBuilder.toString());
                progresso.setProgresso(contador * 100 / totalIteracoes);

                progresso = progressoService.save(progresso);
            }

             if (contador % 10 == 0) {
                progresso.setProgresso(contador * 100 / totalIteracoes);
                progresso = progressoService.save(progresso);
             }

            count--;
            contador++;
        }

        progresso.setProgresso(contador * 100 / totalIteracoes);
        progresso.setDone(true);
        progresso.setSolvedAt(OffsetDateTime.now());
        progressoService.save(progresso);
    }
}
