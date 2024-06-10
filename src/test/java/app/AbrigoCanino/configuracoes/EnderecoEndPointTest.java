package app.AbrigoCanino.configuracoes;

import app.AbrigoCanino.config.configuracoes.EnderecoEndPoint;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Classe de teste para EnderecoEndPoint
@SpringBootTest
public class EnderecoEndPointTest {

    // Teste para verificar os endpoints da classe EnderecoEndPoint
    @Test
    void testEndpoints() {
        // Assert
        assertEquals("/cadastrar", EnderecoEndPoint.CADASTRAR);
        assertEquals("/buscar-id", EnderecoEndPoint.BUSCAR_ID);
        assertEquals("/listar", EnderecoEndPoint.LISTAR);
        assertEquals("/atualizar", EnderecoEndPoint.ATUALIZAR);
        assertEquals("/deletar", EnderecoEndPoint.DELETAR);
    }

    // Teste para verificar as constantes da classe EnderecoEndPoint
    @Test
    void testConstantes() {
        // Assert
        assertEquals("/cadastrar", EnderecoEndPoint.CADASTRAR);
        assertEquals("/buscar-id", EnderecoEndPoint.BUSCAR_ID);
        assertEquals("/listar", EnderecoEndPoint.LISTAR);
        assertEquals("/atualizar", EnderecoEndPoint.ATUALIZAR);
        assertEquals("/deletar", EnderecoEndPoint.DELETAR);
    }
}


