package iw.graph.lights_out.api.mapper;

import iw.graph.lights_out.api.model.out.ProgressoDTO;
import iw.graph.lights_out.domain.model.Progresso;
import org.springframework.stereotype.Component;

@Component
public class ProgressoMapper {

    public ProgressoDTO entityToDTO(Progresso progresso) {
        return ProgressoDTO.builder()
                .done(progresso.getDone())
                .solucoes(progresso.getSolucoes())
                .code(progresso.getCode())
                .createdAt(progresso.getCreatedAt())
                .solvedAt(progresso.getSolvedAt())
                .progresso(progresso.getProgresso())
                .build();
    }
}
