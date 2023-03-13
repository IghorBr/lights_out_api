package iw.graph.lights_out.domain.service;

import iw.graph.lights_out.domain.exception.ObjectNotFoundException;
import iw.graph.lights_out.domain.model.Progresso;
import iw.graph.lights_out.domain.repository.ProgressoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProgressoService {

    private final ProgressoRepository progressoRepository;

    public Progresso findById(String id) {
        return progressoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(String.format("Grafo de id %s não encontrado", id)));
    }

    @Transactional
    public Progresso save(Progresso progresso) {
        return progressoRepository.save(progresso);
    }
}
