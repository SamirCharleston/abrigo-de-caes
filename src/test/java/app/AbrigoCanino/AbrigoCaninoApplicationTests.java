package app.AbrigoCanino;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class AbrigoCaninoApplicationTests {

    @Test
    void main_StartApplication_NoExceptions() {
        // Testa se o método main pode ser executado sem lançar exceções
        assertDoesNotThrow(() -> AbrigoCaninoApplication.main(new String[]{}));
    }
}