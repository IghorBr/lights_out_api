package iw.graph.lights_out.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter @Setter
public class Solucao {

    private Set<String> solucoes = new LinkedHashSet<>();

    public void addSolucao(String... s) {
        for (String str : s) {
            String[] split = str.split(" - ");
            var builder = new StringBuilder();
            for (int i = 0; i < split.length; i++) {
                if (i != split.length-1)
                    builder.append(String.format("%s, ", split[i]));
                else
                    builder.append(String.format("%s", split[i]));
            }

            this.solucoes.add(builder.toString());
        }
    }
}
