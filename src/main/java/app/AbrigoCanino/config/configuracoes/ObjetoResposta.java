package app.AbrigoCanino.config.configuracoes;

import lombok.Data;

@Data
public class ObjetoResposta<T>{
    private String mensagem;
    private T objeto;
}
