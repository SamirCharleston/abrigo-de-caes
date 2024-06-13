package app.AbrigoCanino.configuracoes;

import app.AbrigoCanino.config.configuracoesEspecificas.MensagensDeSucesso;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Classe de teste para MensagensDeSucesso
public class MensagensDeSucessoTest {

    // Teste para verificar as mensagens de sucesso definidas na classe MensagensDeSucesso
    @Test
    void testMensagensDeSucesso() {
        // Assert
        assertEquals("Cadastro realizado com sucesso!", MensagensDeSucesso.CADASTRO_SUCESSO);
        assertEquals("Alteração realizada com sucesso!", MensagensDeSucesso.ALTERACAO_SUCESSO);
        assertEquals("Exclusão realizada com sucesso!", MensagensDeSucesso.EXCLUSAO_SUCESSO);
    }

    // Teste para verificar as constantes definidas na classe MensagensDeSucesso
    @Test
    void testConstantes() {
        // Assert
        assertEquals("Cadastro realizado com sucesso!", MensagensDeSucesso.CADASTRO_SUCESSO);
        assertEquals("Alteração realizada com sucesso!", MensagensDeSucesso.ALTERACAO_SUCESSO);
        assertEquals("Exclusão realizada com sucesso!", MensagensDeSucesso.EXCLUSAO_SUCESSO);
    }
}