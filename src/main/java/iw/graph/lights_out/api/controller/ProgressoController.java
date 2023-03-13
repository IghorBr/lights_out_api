package iw.graph.lights_out.api.controller;

import iw.graph.lights_out.api.mapper.ProgressoMapper;
import iw.graph.lights_out.api.model.out.ProgressoDTO;
import iw.graph.lights_out.domain.model.Progresso;
import iw.graph.lights_out.domain.service.ProgressoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/progressos")
@RequiredArgsConstructor
public class ProgressoController {

    private final ProgressoService progressoService;
    private final ProgressoMapper mapper;

    @GetMapping("/{code}")
    public ResponseEntity<ProgressoDTO> getProgresso(@PathVariable("code") String code) {
        Progresso progresso = progressoService.findById(code);
        ProgressoDTO dto = mapper.entityToDTO(progresso);

        return ResponseEntity.ok(dto);
    }
}
