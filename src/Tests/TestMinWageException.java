package Tests;

import Exceptions.MinWageException;
import model.BioRecords;
import model.Employee;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.fail;

public class TestMinWageException {
    Scanner kb = new Scanner(System.in);
    BioRecords newEmp;

    @Test
    public void TestLessThanMinWageExpectException() {
        try {
            newEmp = new Employee("a", "b", "AD", 10, "2015");
            fail("Failed to catch exception");
        } catch (MinWageException m) {
            System.out.println("Exception caught");
        }

    }
    @Test
    public void TestMoreThanMinWageNoException() {
        try {
            newEmp = new Employee("a", "b", "AD", 20, "2015");

        } catch (MinWageException m) {
            fail("Failed to catch exception");
        }

    }
}
