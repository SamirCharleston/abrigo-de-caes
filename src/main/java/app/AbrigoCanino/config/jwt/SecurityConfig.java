package app.AbrigoCanino.config.jwt;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Immutable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
	/*
		*
		* Implementacao propria!!
		*
		* Estes dois atributos sao utilizados para que seja feita a criptografia do JWT
		* atraves das chaves RSA publica e privada, criadas anteriormente pelo terminal com os comandos
		*
		* openssl genrsa > app.key
		* (Para criar um arquivo contendo a chave privada)
		*
		* e
		*
		* openssl rsa -in app.key -pubout -out app.pub
		* (Para criar um arquivo apartir da chave privada contendo a chave publica)
	 */
//	@Value("${jwt.public.key}") //Utilizado para injetar o valor via application.property
//	private RSAPublicKey publicKey;
//	@Value("${jwt.private.key}") //Utilizado para injetar o valor via property
//	private RSAPrivateKey privateKey;

	//Fim da implementacao propria!!
	@Autowired
	private JwtAuthenticationFilter jwtAuthFilter;

	@Autowired
	private AuthenticationProvider authenticationProvider;
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(AbstractHttpConfigurer::disable)
				.cors(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests((requests) -> requests
						.requestMatchers("/api/login").permitAll()
						.requestMatchers("/api/register").permitAll()
						.anyRequest().authenticated())
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}
	
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:4200");
		config.setAllowedHeaders(Arrays.asList(HttpHeaders.AUTHORIZATION,HttpHeaders.CONTENT_TYPE,HttpHeaders.ACCEPT));
		config.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(),HttpMethod.POST.name(),HttpMethod.PUT.name(),HttpMethod.DELETE.name()));
		config.setMaxAge(3600L);
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
		bean.setOrder(-102);
		return bean;
	}


	/*
		*
        * Implementacao propria!!
        *
        * Implementacao do JwtEncoder e JwtDecoder
        *
     */
//	@Bean
//	public JwtEncoder jwtEncoder() {
//		//Cria uma chave RSA com as chaves publica e privada
//		JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(privateKey).build();
//
//		//Encapsula jwkset em um immutablejwkset
//		var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
//
//		//Cria uma instancia de NimbusJwtEncoder para codificar os Jwts com as chaves fornecidas
//		return new NimbusJwtEncoder(jwks);
//    }
//
//    @Bean
//    public JwtDecoder jwtDecoder() {
//		//Cria um decoder apartir da chave publica
//        return NimbusJwtDecoder.withPublicKey(publicKey).build();
//    }
}
