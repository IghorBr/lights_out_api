package iw.graph.lights_out.domain.model;

import lombok.*;

import java.util.*;

@Getter @Setter
public class Vertice {

    private String code;
    private Boolean aceso = false;
    private Set<Vertice> vizinhos = new LinkedHashSet<>();

    public Vertice() {
        this.code = UUID.randomUUID().toString();
    }

    public Vertice(Vertice that) {
        this.code = that.getCode();
        this.aceso = that.getAceso();
        this.vizinhos = that.getVizinhos();
    }

    public Vertice(String code) {
        this.code = code;
    }

    public Vertice addVizinho(Vertice... v) {
        this.vizinhos.addAll(Arrays.asList(v));
//        v.getVizinhos().add(this);
        return this;
    }

    public void click() {
        this.aceso = !aceso;
        for (Vertice v : vizinhos) {
            v.setAceso(!v.getAceso());
        }
    }

    @Override
    public String toString() {
        return "Vertice{" +
                "code='" + code + '\'' +
                '}';
    }

    public String toString(boolean showAcesso) {
        return "Vertice {" +
                "code='" + code + '\'' +
                "aceso='" + aceso + '\'' +
                '}';
    }
}
