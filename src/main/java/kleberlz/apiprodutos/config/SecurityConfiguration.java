package kleberlz.apiprodutos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

import kleberlz.apiprodutos.security.JwtCustomAuthenticationFilter;
import kleberlz.apiprodutos.security.LoginSocialSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {
	
	@Bean
	public SecurityFilterChain securityFilterChain(
			HttpSecurity http,
			LoginSocialSuccessHandler successHandler,
			JwtCustomAuthenticationFilter jwtCustomAuthenticationFilter) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable) //CRSF desabilitado, não é aplicação WEB.
				.formLogin(configurer -> { //Formulário de login habilitado.
					configurer.loginPage("/login");	
				})
				.authorizeHttpRequests(authorize -> {
					authorize.requestMatchers("/login/**").permitAll();
					authorize.requestMatchers(HttpMethod.POST, "/usuarios/**").permitAll();
					
					authorize.anyRequest().authenticated(); //Regra de acesso, deve estar autenticado em
															//qualquer requisição.
				})
				.oauth2Login(oauth2 -> {
					oauth2
					.loginPage("/login")
					.successHandler(successHandler); //Caso receba autenticação de um token de um lugar 
													 //diferente, ex: Login pelo Email Google.
				})
				.oauth2ResourceServer(oauth2RS -> oauth2RS.jwt(Customizer.withDefaults()))//Config Token JWT
				.addFilterAfter(jwtCustomAuthenticationFilter, BearerTokenAuthenticationFilter.class)
				.build();
	}
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() { //Ignora docs padrões do swagger na documentação.
		return web -> web.ignoring().requestMatchers(
				"/v2/api-docs/**",
				"/v3/api-docs/**",
				"/swagger-resources/**",
				"/swagger-ui.html",
				"/swagger-ui/**",
				"/webjars/**",
				"/actuator/**");
	}
	
	@Bean // Configuração do PREFIXO ROLE.
	public GrantedAuthorityDefaults grantedAuthorityDefaults() {//Obrigatoriedade de ROLE_ retirada.
		return new GrantedAuthorityDefaults("");	
	}
	
	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() { //Classe usada para converter JWT em
																	 //Authentication ja com as roles.
		var authoritiesConverter = new JwtGrantedAuthoritiesConverter();
			authoritiesConverter.setAuthorityPrefix(""); //Role removida.
			
			var converter = new JwtAuthenticationConverter(); //JwtAuthenticationConverter criado
			converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);//Use o authoritiesConverter
																			  // customizado(sem ROLE_)
			
			return converter; //Retorna já configurado.
		}
	}
	
	
	


