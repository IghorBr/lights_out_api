package iw.graph.lights_out.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Schema(name = "GrafoInput")
public class GrafoInput {

    @Schema(example = "4")
    private Integer numeroVertices;

    @Schema(example = "[0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0]")
    private List<Integer> matriz = new ArrayList<>();
}
