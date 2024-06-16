package app.AbrigoCanino.config.configuracoesEspecificas;

import app.AbrigoCanino.auth.Authorization;

public class PermissaoPara {
    public static final String RESPONSAVEL = "hasRole('ROLE_RESPONSAVEL')";
    public static final String VOLUNTARIO = "hasRole('ROLE_VOLUNTARIO')";
}
