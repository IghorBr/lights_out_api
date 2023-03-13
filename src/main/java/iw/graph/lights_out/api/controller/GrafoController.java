package iw.graph.lights_out.api.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import iw.graph.lights_out.api.mapper.GrafoMapper;
import iw.graph.lights_out.api.model.input.GrafoInput;
import iw.graph.lights_out.api.model.out.GrafoDTO;
import iw.graph.lights_out.api.model.out.ProgressoDTO;
import iw.graph.lights_out.api.model.out.ResumoGrafoDTO;
import iw.graph.lights_out.domain.model.Grafo;
import iw.graph.lights_out.domain.service.GrafoService;
import iw.graph.lights_out.domain.service.solver.ForcaBrutaSolverService;
import iw.graph.lights_out.domain.service.solver.SolverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = GrafoDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Usuário não está logado", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)) }),
            @ApiResponse(responseCode = "403", description = "Usuário não tem autorização", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)) })
    })
    public ResponseEntity<GrafoDTO> getGrafo() {
        Grafo grafo = grafoService.criaGrafoSimples();
        GrafoDTO dto = grafoMapper.convertGrafoToDTO(grafo);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/solved")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProgressoDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Grafo não encontrado", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)) }),
            @ApiResponse(responseCode = "401", description = "Usuário não está logado", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)) }),
            @ApiResponse(responseCode = "403", description = "Usuário não tem autorização", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)) })
    })
    public ResponseEntity<ProgressoDTO> getGrafoSolved() {
        Grafo grafo = grafoService.criaGrafoSimples();
        solverService.solveLightsOut(grafo);

        ProgressoDTO dto = ProgressoDTO.builder()
                .code(grafo.getCode())
                .createdAt(OffsetDateTime.now())
                .build();

        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity<?> solveGraph(
        @RequestBody GrafoInput input,
        @RequestParam(value = "solve", required = false, defaultValue = "true") Boolean solve
    ) {
        Grafo grafo = grafoService.criaGrafoFromMatrizAdjacencia(input);


        if (solve) {
            solverService.solveLightsOut(grafo);

            ResumoGrafoDTO dto = ResumoGrafoDTO.builder()
                    .code(grafo.getCode())
                    .createdAt(OffsetDateTime.now())
                    .build();

            return ResponseEntity.ok(dto);
        }
        else
            return ResponseEntity.ok(grafoMapper.convertGrafoToDTO(grafo));
    }
}
