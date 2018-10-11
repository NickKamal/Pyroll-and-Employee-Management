package Tests;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEncryptDecrypt {

    EncryptDecrypt ende;
    @BeforeEach
    public void doIt(){
        ende = new EncryptDecrypt();

    }

    @Test
    public void testDecrypt(){

        String s = "JGNNQ";
        String e = ende.decrypt(s);
        assertEquals("HELLO", e);

    }

    @Test
    public void testEncryptMiddle(){

        String s = "HELLO";
        String e = ende.encrypt(s);
        assertEquals("JGNNQ", e);

    }

    @Test
    public void testEncryptEnds(){

        String s = "XYZ";
        String e = ende.encrypt(s);
        assertEquals("ZAB", e);

    }

    @Test
    public void testDecryptEnds(){

        String s = "ZAB";
        String e = ende.decrypt(s);
        assertEquals("XYZ", e);

    }

    @Test
    public void testEncryptNum(){

        String s = "X123";
        String e = ende.encrypt(s);
        assertEquals("Z123", e);

    }

    @Test
    public void testDecryptNum(){

        String s = "Z123";
        String e = ende.decrypt(s);
        assertEquals("X123", e);

    }
}
