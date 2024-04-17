package app.AbrigoCanino;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// Classe de teste para a aplicação AbrigoCaninoApplication
@SpringBootTest
class AbrigoCaninoApplicationTests {

    // Teste para verificar se o contexto da aplicação é carregado corretamente
    @Test
    void contextLoads() {
        // Testa se o contexto da aplicação é carregado corretamente
    }

    // Teste para verificar se o método main da aplicação não lança exceções
    @Test
    void testMainMethod() {
        // Assert
        Assertions.assertDoesNotThrow(() -> AbrigoCaninoApplication.main(new String[]{}));
    }

}
