package iw.graph.lights_out.api.model.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter @Setter
@Builder
public class ResumoGrafoDTO {

    private String code;
    private OffsetDateTime createdAt;
}
