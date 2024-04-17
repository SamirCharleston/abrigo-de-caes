package app.AbrigoCanino;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AbrigoCaninoApplicationTests {

    @Test
    void contextLoads() {
        // Testa se o contexto da aplicação é carregado corretamente
    }

    @Test
    void testMainMethod() {
        // Assert
        Assertions.assertDoesNotThrow(() -> AbrigoCaninoApplication.main(new String[]{}));
    }

}
