package Tests;

import Exceptions.LessThanMinWageException;
import model.CompanyStore;
import model.Employee;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.fail;

public class TestLessThanMinWageException {
    Scanner kb = new Scanner(System.in);
    private Employee newEmp;
    private final CompanyStore companyStore = new CompanyStore("2247");

    @Test
    public void TestLessThanMinWageExpectException() {
        try {
            newEmp = new Employee("a", "b", "AD", 10, "2015", "2247", companyStore);
            fail("Failed to catch exception");
        } catch (LessThanMinWageException m) {
            System.out.println("Exception caught");
        }

    }
    @Test
    public void TestMoreThanMinWageNoException() {
        try {
            newEmp = new Employee("a", "b", "AD", 20, "2015", "2247", companyStore);

        } catch (LessThanMinWageException m) {
            fail("Failed to catch exception");
        }

    }
}
