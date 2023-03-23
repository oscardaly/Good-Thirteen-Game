import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class MenuTest extends Main {

    @Test
    public void testUserMode() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("1");
        assertEquals("user mode", getUserOrDemoMode(bufferedReader));
    }

    @Test
    public void testDemoMode() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("2");
        assertEquals("demonstration mode", getUserOrDemoMode(bufferedReader));
    }

    @Test
    public void testInvalidOption() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("3");
        assertEquals("not an option", getUserOrDemoMode(bufferedReader));
    }
}