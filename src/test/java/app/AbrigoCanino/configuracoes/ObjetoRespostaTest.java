package app.AbrigoCanino.configuracoes;

import app.AbrigoCanino.config.configuracoes.ObjetoResposta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Classe de teste para ObjetoResposta
public class ObjetoRespostaTest {

    // Teste para verificar o construtor e os getters da classe ObjetoResposta
    @Test
    void testConstructorAndGetters() {
        // Arrange
        String mensagem = "Mensagem de teste";
        Integer objeto = 42;

        // Act
        ObjetoResposta<Integer> resposta = new ObjetoResposta<>();
        resposta.setMensagem(mensagem);
        resposta.setObjeto(objeto);

        // Assert
        assertEquals(mensagem, resposta.getMensagem());
        assertEquals(objeto, resposta.getObjeto());
    }

    // Teste para verificar o método toString da classe ObjetoResposta
    @Test
    void testToString() {
        // Arrange
        String mensagem = "Mensagem de teste";
        Integer objeto = 42;
        ObjetoResposta<Integer> resposta = new ObjetoResposta<>();
        resposta.setMensagem(mensagem);
        resposta.setObjeto(objeto);

        // Act
        String toString = resposta.toString();

        // Assert
        assertEquals("ObjetoResposta(mensagem=Mensagem de teste, objeto=42)", toString);
    }

    // Teste para verificar o método equals da classe ObjetoResposta
    @Test
    void testEquals() {
        // Arrange
        ObjetoResposta<Integer> resposta1 = new ObjetoResposta<>();
        resposta1.setMensagem("Mensagem");
        resposta1.setObjeto(42);

        ObjetoResposta<Integer> resposta2 = new ObjetoResposta<>();
        resposta2.setMensagem("Mensagem");
        resposta2.setObjeto(42);

        ObjetoResposta<Integer> resposta3 = new ObjetoResposta<>();
        resposta3.setMensagem("Outra mensagem");
        resposta3.setObjeto(42);

        // Assert
        assertEquals(resposta1, resposta2); // resposta1.equals(resposta2)
        assertNotEquals(resposta1, resposta3); // resposta1.equals(resposta3)
        assertNotEquals(resposta2, resposta3); // resposta2.equals(resposta3)
    }

    // Teste para verificar os setters da classe ObjetoResposta
    @Test
    void testObjetoRespostaSetter() {
        // Arrange
        ObjetoResposta<String> resposta = new ObjetoResposta<>();
        String mensagem = "Sucesso";
        String objeto = "Teste";

        // Act
        resposta.setMensagem(mensagem);
        resposta.setObjeto(objeto);

        // Assert
        assertNotNull(resposta, "O objeto de resposta não deve ser nulo.");
        assertEquals(mensagem, resposta.getMensagem(), "A mensagem deve ser igual à mensagem fornecida.");
        assertEquals(objeto, resposta.getObjeto(), "O objeto deve ser igual ao objeto fornecido.");
    }

    // Teste para verificar os getters e setters da classe ObjetoResposta
    @Test
    void testObjetoRespostaGettersAndSetters() {
        // Arrange
        String mensagem = "Erro";
        String objeto = "Exemplo";

        // Act
        ObjetoResposta<String> resposta = new ObjetoResposta<>();
        resposta.setMensagem(mensagem);
        resposta.setObjeto(objeto);

        // Assert
        assertNotNull(resposta);
        assertEquals(mensagem, resposta.getMensagem());
        assertEquals(objeto, resposta.getObjeto());
    }

    // Teste para verificar o construtor padrão da classe ObjetoResposta
    @Test
    void testObjetoRespostaDefaultConstructor() {
        // Arrange
        ObjetoResposta<String> resposta;

        // Act
        resposta = new ObjetoResposta<>();

        // Assert
        assertNotNull(resposta);
        assertEquals(null, resposta.getMensagem());
        assertEquals(null, resposta.getObjeto());
    }

}