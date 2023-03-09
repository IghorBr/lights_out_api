package iw.graph.lights_out.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter @Setter
@NoArgsConstructor
public class Grafo {

    private List<Vertice> vertices = new ArrayList<>();

    public Grafo(Grafo that) {
        this.vertices = that.getVertices();
    }

    public void addVertices(Vertice... v) {
        this.vertices.addAll(Arrays.asList(v));
    }

    public void reset() {
        for (Vertice v : vertices) {
            v.setAceso(false);
        }
    }

    public Vertice getVerticeByCode(String code) {
        for (Vertice v : vertices) {
            if (v.getCode().equals(code))
                return v;
        }

        return null;
    }

    public boolean isSolved() {
        for (Vertice v : vertices) {
            if (!v.getAceso())
                return false;
        }

        return true;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder("Grafo = (\n");

        for (Vertice v : vertices) {
            builder.append(String.format("\t %s = ( ", v.getCode()));

            for (Vertice u : v.getVizinhos()) {
                builder.append(String.format("%s, ", u.getCode()));
            }
            builder.append(")\n");
        }

        builder.append(")\n");
        return builder.toString();
    }
}
