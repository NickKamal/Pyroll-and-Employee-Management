package Tests;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.beans.BeanProperty;

public class AdminTest {

    private Adminsitration admin;
    @BeforeEach
    public void runBefore()
    {
        admin = new Adminsitration();
    }

    @Test
    public void testUserInfo()
    {
        admin.setAdminId("ab");
        admin.setPassword("abc");
        admin.setAdminWage(20.4);
        assertEquals("ab", admin.getID());
        assertEquals("abc", admin.getPassword());
        assertEquals(20.4, admin.getAdminWage());

        admin.setAdminId("");
        assertEquals("", admin.getID());


    }
}
