package Tests;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestEncryptDecrypt {

    @BeforeEach
    void doIt(){
        EncryptDecrypt ende = new EncryptDecrypt();

    }

    @Test
    void testDecrypt(){

        String s = "JGNNQ";
        String e = EncryptDecrypt.decrypt(s);
        assertEquals("HELLO", e);

    }

    @Test
    void testEncryptMiddle(){

        String s = "HELLO";
        String e = EncryptDecrypt.encrypt(s);
        assertEquals("JGNNQ", e);

    }

    @Test
    void testEncryptEnds(){

        String s = "XYZ";
        String e = EncryptDecrypt.encrypt(s);
        assertEquals("ZAB", e);

    }

    @Test
    void testDecryptEnds(){

        String s = "ZAB";
        String e = EncryptDecrypt.decrypt(s);
        assertEquals("XYZ", e);

    }

    @Test
    void testEncryptNum(){

        String s = "X123";
        String e = EncryptDecrypt.encrypt(s);
        assertEquals("Z123", e);

    }

    @Test
    void testDecryptNum(){

        String s = "Z123";
        String e = EncryptDecrypt.decrypt(s);
        assertEquals("X123", e);

    }
}
