package br.org.serratec.rede_social.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//import br.org.serratec.rede_social.security.JwtAuthenticationFilter;
//import br.org.serratec.rede_social.security.JwtAuthorizationFilter;
//import br.org.serratec.rede_social.security.JwtUtil;

/*
@Configuration
@EnableWebSecurity
public class ConfigSeguranca {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//desabilitamos o csrf e habilitamos o cors na camada de filtro
		http.csrf(csrf -> csrf.disable())
		.cors((cors) -> cors.configurationSource(corsConfigurationSource()))
		.authorizeHttpRequests(authorize -> 
	        authorize
	            .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
	            .anyRequest().authenticated()
	    )
	    .httpBasic(Customizer.withDefaults())
	    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		JwtAuthenticationFilter jwtAuthenticationFilter = 
				new JwtAuthenticationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class))
						, jwtUtil);
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		
		JwtAuthorizationFilter jwtAuthorizationFilter = 
				new JwtAuthorizationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)),
						jwtUtil, userDetailsService);
		
		http.addFilter(jwtAuthenticationFilter);
		http.addFilter(jwtAuthorizationFilter);
		
		return http.build();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		//dominio(s) para poder acessar a api
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		//metodos de acesso
		corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		//aplica para todos os endpoints
		source.registerCorsConfiguration("/**", corsConfiguration.applyPermitDefaultValues());
		
		return source;
	}
	
	//criptografia de senhas
	//faz com que a classe seja gerenciada pelo spring
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//permite a autenticacao personalizada que criamos no service
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) 
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
}
*/	