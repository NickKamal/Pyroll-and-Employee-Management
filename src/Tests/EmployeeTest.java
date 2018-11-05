package Tests;
import Exceptions.LessThanMinWageException;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeTest {

    private Employee emp;
    @BeforeEach
    void runBefore()
    {
        emp = new Employee();
    }

    @Test
    void testUserInfo() throws LessThanMinWageException {
        emp.setPosition("ab");
        emp.setWage(20.3);

        assertEquals("ab", emp.getPosition());
        assertEquals(20.3, emp.getWage());

        emp.setPosition("");
        assertEquals("", emp.getPosition());


    }
}
