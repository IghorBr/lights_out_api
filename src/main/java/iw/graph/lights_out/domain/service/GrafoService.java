package iw.graph.lights_out.domain.service;

import iw.graph.lights_out.api.model.input.GrafoInput;
import iw.graph.lights_out.domain.model.Grafo;
import iw.graph.lights_out.domain.model.Vertice;
import org.springframework.stereotype.Service;

@Service
public class GrafoService {

    public Grafo criaGrafoSimples() {
        Grafo grafo = new Grafo();

        Vertice v1 = new Vertice("a");
        Vertice v2 = new Vertice("b");
        Vertice v3 = new Vertice("c");
        Vertice v4 = new Vertice("d");

        v1.addVizinho(v2, v3, v4);
        v2.addVizinho(v1, v3);
        v3.addVizinho(v1, v2);
        v4.addVizinho(v1);

        grafo.addVertices(v1, v2, v3, v4);

        return grafo;
    }

    public Grafo criaGrafoFromMatrizAdjacencia(GrafoInput input) {
        int size = input.getNumeroVertices();
        Grafo grafo = new Grafo();

        for (int i = 0; i < size; i++) {
            Vertice v = new Vertice(String.valueOf(i+1));
            grafo.addVertices(v);
        }

        for (int i = 0; i < size; i++) {
            Vertice vertice = grafo.getVerticeByCode(String.valueOf(i + 1));
            for (int j = 0; j < size; j++) {
                Vertice vizinho = grafo.getVerticeByCode(String.valueOf(j + 1));
                if (input.getMatriz().get(i * size + j) == 1) {
                    vertice.addVizinho(vizinho);
                }
            }
        }

        return grafo;
    }
}
