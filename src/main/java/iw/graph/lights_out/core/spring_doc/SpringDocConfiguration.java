package iw.graph.lights_out.core.spring_doc;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@SecurityScheme(
        name = "security",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(clientCredentials = @OAuthFlow(
            tokenUrl = "http://localhost:8080/lightsout/oauth2/token",
            scopes = {
                @OAuthScope(name = "READ", description = "Escopo de Leitura"),
                @OAuthScope(name = "WRITE", description = "Escopo de Escrita")
            }
        ))
)
public class SpringDocConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Lights Out API")
                                .version("Alpha")
                                .description("REST API para resolução do jogo Lights Out para grafos")
                                .license(
                                        new License()
                                                .name("Lights-Out")
                                )
                )
                .tags(Arrays.asList(
                        new Tag().name("Grafo").description("Gerencia e soluciona grafos")
                ))
                ;
    }
}
