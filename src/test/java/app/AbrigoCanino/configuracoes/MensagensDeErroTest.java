package app.AbrigoCanino.configuracoes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Classe de teste para MensagensDeErro
public class MensagensDeErroTest {

    // Teste para verificar as mensagens de erro definidas na classe MensagensDeErro
    @Test
    void testMensagensDeErro() {
        // Assert
        assertEquals("Id não encontrado", MensagensDeErro.ID_NAO_ENCONTRADO);
        assertEquals("Não encontrado", MensagensDeErro.ERRO_NAO_ENCONTRADO);
        assertEquals("Lista vazia", MensagensDeErro.LISTA_VAZIA);
    }

    // Teste para verificar as constantes definidas na classe MensagensDeErro
    @Test
    void testConstantes() {
        // Assert
        assertEquals("Id não encontrado", MensagensDeErro.ID_NAO_ENCONTRADO);
        assertEquals("Não encontrado", MensagensDeErro.ERRO_NAO_ENCONTRADO);
        assertEquals("Lista vazia", MensagensDeErro.LISTA_VAZIA);
    }
}