package iw.graph.lights_out.api.model.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter @Setter
@Builder
public class VerticeDTO {

    private String code;
    private Boolean aceso;
    private Set<String> vizinhos = new LinkedHashSet<>();

}
