package br.org.serratec.rede_social.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

	@Value("${dominio.openapi.dev-url}")
	private String devUrl;

	@Value("${dominio.openapi.prod-url}")
	private String prodUrl;

	@Bean // indica que o objeto será gerenciado pelo Spring(vai fazer a instancia do
			// objeto openAPI)
	OpenAPI myOpenAPI() { // essas nomenclaturas são próprias dos pacotes importados
		Server devServer = new Server();
		devServer.setUrl(devUrl);
		devServer.setDescription("URL do servidor de desenvolvimento");

		Server prodServer = new Server();
		prodServer.setUrl(prodUrl);
		prodServer.setDescription("URL do servidor de produção");

		Contact contact = new Contact();
		contact.setEmail("contato@group6.com.br");
		contact.setName("Group6");
		contact.setUrl("https://www.group6.com.br");

		License apacheLicense = new License().name("Apache License").url("https://www.apache.org/license/LICENSE-2.0");

		Info info = new Info().title("API de Testes - Rede Social").version("1.0").contact(contact)
				.description("API testes Rede Social Group6").termsOfService("https://www.group6.com.br/termos")
				.license(apacheLicense);

		return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
	}

}
