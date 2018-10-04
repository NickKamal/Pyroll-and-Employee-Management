package Tests;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeTest {

    private Employee emp;
    @BeforeEach
    public void runBefore()
    {
        emp = new Employee();
    }

    @Test
    public void testUserInfo()
    {
        emp.setPosition("ab");
        emp.setWage(20.3);

        assertEquals("ab", emp.getPosition());
        assertEquals(20.3, emp.getWage());

        emp.setPosition("");
        assertEquals("", emp.getPosition());


    }
}
