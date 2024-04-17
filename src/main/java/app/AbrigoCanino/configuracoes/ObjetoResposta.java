package app.AbrigoCanino.configuracoes;

import lombok.Data;

@Data
public class ObjetoResposta<T>{
    private String mensagem;
    private T objeto;
}
