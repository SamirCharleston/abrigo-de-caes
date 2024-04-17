package app.AbrigoCanino.configuracoes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MensagensDeSucessoTest {

    @Test
    void testMensagensDeSucesso() {
        assertEquals("Cadastro realizado com sucesso!", MensagensDeSucesso.CADASTRO_SUCESSO);
        assertEquals("Alteração realizada com sucesso!", MensagensDeSucesso.ALTERACAO_SUCESSO);
        assertEquals("Exclusão realizada com sucesso!", MensagensDeSucesso.EXCLUSAO_SUCESSO);
    }
}