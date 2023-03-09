package iw.graph.lights_out.api.model.out;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter @Setter
public class GrafoDTO {

    private Set<VerticeDTO> vertices = new LinkedHashSet<>();

    public void addVertices(VerticeDTO... v) {
        this.vertices.addAll(Arrays.asList(v));
    }
}
