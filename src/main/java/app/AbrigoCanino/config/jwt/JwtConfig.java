package app.AbrigoCanino.config.jwt;

import io.jsonwebtoken.SignatureAlgorithm;

public class JwtConfig {

	//Parâmetros para geração do token
	public static final String SECRET_KEY = "kjniuheiuwrhewuqg^$&zlzpeohruhauhsfiudhsaifnjemnmfjabsbdyuhfuaskhdfishiue12333jkjbnfkjf#$$kdkfn3#4";
	public static final SignatureAlgorithm ALGORITMO_ASSINATURA = SignatureAlgorithm.HS256;
	public static final int HORAS_EXPIRACAO_TOKEN = 1;

}
