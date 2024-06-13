package app.AbrigoCanino.config.configuracoesEspecificas;

import lombok.Data;

@Data
public class ObjetoResposta<T>{
    private String mensagem;
    private T objeto;
}
