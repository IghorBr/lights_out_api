package iw.graph.lights_out.api.model.input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class GrafoInput {

    private Integer numeroVertices;
    private List<Integer> matriz = new ArrayList<>();
}
