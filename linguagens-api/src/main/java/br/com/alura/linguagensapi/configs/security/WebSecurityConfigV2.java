package br.com.alura.linguagensapi.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableGlobalMethodSecurity(prePostEnabled = true) // necessario para implementar as permissoes dentro do controller
@Configuration
public class WebSecurityConfigV2 {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.httpBasic()
			.and()
			.authorizeHttpRequests()
			.anyRequest().authenticated() // todas as requisições precisam de autenticação
			.and()
			.csrf().disable(); // permite acesso a métodos POST, PUT e DELETE, usar somente em produção.
		
		return http.build();
	}
	
	// Método necessário para codificar a senha, pois o Spring precisa de um PasswordEncoder e não uma String qualquer
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
