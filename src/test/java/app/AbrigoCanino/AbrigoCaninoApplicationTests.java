package app.AbrigoCanino;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class AbrigoCaninoApplicationTests {

    @Test
    void main_StartApplication_NoExceptions() {
        assertDoesNotThrow(() -> {
            AbrigoCaninoApplication.main(new String[0]);
        });
    }
}