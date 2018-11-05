package Tests;
import Exceptions.LessThanMinWageException;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    private Administration admin;
    @BeforeEach
    void runBefore() {
        admin = new Administration();
    }

    @Test
    void testUserInfo() throws LessThanMinWageException {
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
