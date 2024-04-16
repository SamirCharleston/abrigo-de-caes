package app.AbrigoCanino.configuracoes;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ObjtetoResposta <T>{
    private String mensagem;
    private T objeto;
}
