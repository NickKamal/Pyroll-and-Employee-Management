package ui.UI;

import model.Employee;
import model.Salary;
import observer.Subject;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class SalaryCalculation extends Subject {

    //EFFECTS: Calculates, stores and prints the salary of the given employee a
    public static void calculateSalary(Scanner kb, Map employees, Map employeeSalaryRecord, Map salaryRecord)
            throws IOException {
        Salary currentSalary;
        double currentWorkingHours = 0.0;
        System.out.println("Enter the employee's ID: ");
        String payId = kb.nextLine();
        String payPeriod;
        if (employees.containsKey(payId)) {
            System.out.println("Enter the number of hours for the pay period: ");
            boolean error;
            do {
                try {
                    currentWorkingHours = kb.nextDouble();
                    error = false;
                } catch (InputMismatchException i) {
                    kb.nextLine();
                    System.out.println("Invalid Input");
                    error = true;
                }
            } while (error);
            System.out.println("Enter the pay period (dd/mm/yyyy to dd/mm/yyyy): ");
            kb.nextLine();
            payPeriod = kb.nextLine();
            currentSalary = new Salary((Employee) employees.get(payId), currentWorkingHours,
                    payPeriod);
            currentSalary.write();
            observers.add((Employee) employees.get(payId));
            notifyObserver();
            currentSalary.earnings();
            salaryRecord.put(payPeriod, currentSalary);
            employeeSalaryRecord.put(payId, salaryRecord);

        } else {
            System.out.println("ID does not exist!!! Please try again.");
        }
    }

}
