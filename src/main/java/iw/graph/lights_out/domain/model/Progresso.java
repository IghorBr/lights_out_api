package iw.graph.lights_out.domain.model;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Progresso {

    @Id
    private String code;
    private OffsetDateTime createdAt;
    private OffsetDateTime solvedAt;
    private Double progresso = 0.;
    private Boolean done = false;

    @ElementCollection
    private List<String> solucoes = new ArrayList<>();

    public Progresso() {
    }

    public Progresso(String code) {
        this.code = code;
        this.createdAt = OffsetDateTime.now();
    }

    public void addSolucao(String solucao) {
        String[] strings = solucao.split(" - ");
        var builder = new StringBuffer();

        for (int i = 0; i < strings.length; i++) {
            builder.append(strings[i]);

            if (i < strings.length-1)
                builder.append(" - ");
        }

        this.solucoes.add(builder.toString());
    }

    public String getCode() {
        return code;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getSolvedAt() {
        return solvedAt;
    }

    public void setSolvedAt(OffsetDateTime solvedAt) {
        this.solvedAt = solvedAt;
    }

    public Double getProgresso() {
        return progresso;
    }

    public void setProgresso(Double progresso) {
        this.progresso = progresso;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public List<String> getSolucoes() {
        return solucoes;
    }
}
