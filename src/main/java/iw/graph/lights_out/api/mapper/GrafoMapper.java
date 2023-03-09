package iw.graph.lights_out.api.mapper;

import iw.graph.lights_out.api.model.out.GrafoDTO;
import iw.graph.lights_out.api.model.out.VerticeDTO;
import iw.graph.lights_out.domain.model.Grafo;
import iw.graph.lights_out.domain.model.Vertice;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GrafoMapper {

    public GrafoDTO convertGrafoToDTO(Grafo grafo) {
        GrafoDTO dto = new GrafoDTO();

        for (Vertice v : grafo.getVertices()) {
            Set<String> codeVizinhos = new LinkedHashSet<>();
            for (Vertice u : v.getVizinhos())
                codeVizinhos.add(u.getCode());

            VerticeDTO verticeDTO = VerticeDTO.builder()
                    .aceso(v.getAceso())
                    .code(v.getCode())
                    .vizinhos(codeVizinhos)
                    .build();

            dto.addVertices(verticeDTO);
        }

        return dto;
    }

    public Set<GrafoDTO> convertList(Set<Grafo> grafos) {
        return grafos.stream().map(g -> this.convertGrafoToDTO(g)).collect(Collectors.toSet());
    }

}
