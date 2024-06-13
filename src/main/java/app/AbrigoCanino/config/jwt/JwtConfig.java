package app.AbrigoCanino.config.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

public class JwtConfig {

	//Parâmetros para geração do token
	public static String SECRET_KEY = "tokenencodedkgnerjfkfjeirjdnjnddlgjndlgjndlgjndlg";
	public static final SignatureAlgorithm ALGORITMO_ASSINATURA = SignatureAlgorithm.HS256;
	public static final int HORAS_EXPIRACAO_TOKEN = 1;

}
