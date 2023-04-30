import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.BufferedReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class MenuTest {
    @Test
    public void testUserMode() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("1");
        assertEquals("User Mode", Main.getUserOrDemoMode(bufferedReader));
    }

    @Test
    public void testDemoMode() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("2");
        assertEquals("Demonstration Mode", Main.getUserOrDemoMode(bufferedReader));
    }

    @Test
    public void testInvalidOption() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("3");
        assertEquals("Incorrect option selected", Main.getUserOrDemoMode(bufferedReader));
    }
}