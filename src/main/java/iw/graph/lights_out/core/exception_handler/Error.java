package iw.graph.lights_out.core.exception_handler;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter @Setter
@Builder
@Schema(name = "Erro")
public class Error implements Serializable {

    private Integer status;
    private String descricao;
    private OffsetDateTime timestamp;
    private String path;

}
