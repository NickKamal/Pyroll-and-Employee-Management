package Tests;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.beans.BeanProperty;

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
        emp.setEmployeePos("ab");
        emp.setEmployeeWage(20.3);

        assertEquals("ab", emp.getEmpPos());
        assertEquals(20.3, emp.getEmployeeWage());

        emp.setEmployeePos("");
        assertEquals("", emp.getEmpPos());


    }
}
