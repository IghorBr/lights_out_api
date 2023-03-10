package iw.graph.lights_out.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import iw.graph.lights_out.api.mapper.GrafoMapper;
import iw.graph.lights_out.api.model.input.GrafoInput;
import iw.graph.lights_out.api.model.out.GrafoDTO;
import iw.graph.lights_out.domain.model.Grafo;
import iw.graph.lights_out.domain.model.Solucao;
import iw.graph.lights_out.domain.service.GrafoService;
import iw.graph.lights_out.domain.service.solver.ForcaBrutaSolverService;
import iw.graph.lights_out.domain.service.solver.SolverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grafos")
@SecurityRequirement(name = "security")
@Tag(name = "Grafo")
public class GrafoController {

    private final GrafoService grafoService;
    private final GrafoMapper grafoMapper;
    private final SolverService solverService;

    public GrafoController(GrafoService grafoService, GrafoMapper grafoMapper, ForcaBrutaSolverService solverService) {
        this.grafoService = grafoService;
        this.grafoMapper = grafoMapper;
        this.solverService = solverService;
    }

    @GetMapping
    public ResponseEntity<GrafoDTO> getGrafo() {
        Grafo grafo = grafoService.criaGrafoSimples();
        GrafoDTO dto = grafoMapper.convertGrafoToDTO(grafo);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/solved")
    public ResponseEntity<Solucao> getGrafoSolved() {
        Grafo grafo = grafoService.criaGrafoSimples();
        Solucao solucoes = solverService.solveLightsOut(grafo);

        return ResponseEntity.ok(solucoes);
    }

    @PutMapping
    public ResponseEntity<Solucao> solveGraph(@RequestBody GrafoInput input) {
        Grafo grafo = grafoService.criaGrafoFromMatrizAdjacencia(input);
        Solucao s = solverService.solveLightsOut(grafo);

        return ResponseEntity.ok(s);
    }
}
