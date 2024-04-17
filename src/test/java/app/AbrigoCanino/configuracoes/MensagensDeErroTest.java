package app.AbrigoCanino.configuracoes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MensagensDeErroTest {

    @Test
    void testMensagensDeErro() {
        assertEquals("Id não encontrado", MensagensDeErro.ID_NAO_ENCONTRADO);
        assertEquals("Não encontrado", MensagensDeErro.ERRO_NAO_ENCONTRADO);
        assertEquals("Lista vazia", MensagensDeErro.LISTA_VAZIA);
    }

    @Test
    void testConstantes() {
        // Assert
        assertEquals("Id não encontrado", MensagensDeErro.ID_NAO_ENCONTRADO);
        assertEquals("Não encontrado", MensagensDeErro.ERRO_NAO_ENCONTRADO);
        assertEquals("Lista vazia", MensagensDeErro.LISTA_VAZIA);
    }

}