package app.AbrigoCanino.configuracoes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnderecoEndPointTest {

    @Test
    void testEndpoints() {
        assertEquals("/cadastrar", EnderecoEndPoint.CADASTRAR);
        assertEquals("/buscar-id", EnderecoEndPoint.BUSCAR_ID);
        assertEquals("/listar", EnderecoEndPoint.LISTAR);
        assertEquals("/atualizar", EnderecoEndPoint.ATUALIZAR);
        assertEquals("/deletar", EnderecoEndPoint.DELETAR);
    }

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


