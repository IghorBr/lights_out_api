package iw.graph.lights_out.api.model.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter @Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProgressoDTO {

    private String code;
    private OffsetDateTime createdAt;
    private OffsetDateTime solvedAt;
    private Double progresso;
    private Boolean done;
    private List<String> solucoes;
}
