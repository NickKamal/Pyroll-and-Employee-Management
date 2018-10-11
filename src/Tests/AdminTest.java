package Tests;
import Exceptions.MinWageException;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdminTest {

    private Adminsitration admin;
    @BeforeEach
    public void runBefore()
    {
        admin = new Adminsitration();
    }

    @Test
    public void testUserInfo() throws MinWageException {
        admin.setID("ab");
        admin.setPassword("abc");
        admin.setWage(20.4);
        assertEquals("ab", admin.getID());
        assertEquals("abc", admin.getPassword());
        assertEquals(20.4, admin.getWage());

        admin.setID("");
        assertEquals("", admin.getID());


    }
}
