package kleberlz.apiprodutos.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition( // Aqui estão as informações que aparecerão ao acessar a OpenApi(Swagger) 
					// informações de contato, etc.
		info = @Info(
				title = "Produtos API",
				version = "v1",
				contact = @Contact(
						name = "Kleber Luiz",
						email = "kleberluizf17@gmail.com",
						url = "produtosapi.com")
				),
				security = {
						@SecurityRequirement(name = "bearerAuth" )
				}
		)
@SecurityScheme( // Aqui está o tipo de token que dá acesso a API.
		name = "bearerAuth",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer",
		in = SecuritySchemeIn.HEADER
		)
public class OpenApiConfiguration {

}
