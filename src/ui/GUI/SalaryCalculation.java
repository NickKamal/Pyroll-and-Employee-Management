package ui.GUI;

import model.Employee;
import model.Salary;
import observer.Subject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class SalaryCalculation extends Subject {

    //EFFECTS: Calculates, stores and prints the salary of the given employee a
    public static void calculateSalary(Scanner kb, Map employees, Map employeeSalaryRecord, Map salaryRecord)
            throws IOException {
        JFrame salaryFrame = new JFrame("Salay Record");
        Salary currentSalary;
        double currentWorkingHours = 0.0;
        salaryFrame.setSize(320, 380);
        salaryFrame.setLayout(new FlowLayout());
        salaryFrame.setBackground(Color.white);
        String payId = JOptionPane.showInputDialog("Enter the employee's ID: ");
        String payPeriod;
        if (employees.containsKey(payId)) {

            boolean error;
            do {
                try {
                    currentWorkingHours = Double.parseDouble(JOptionPane.showInputDialog("Enter the number of hours for the pay period: "));
                    error = false;
                } catch (Exception i) {
                    JOptionPane.showMessageDialog(salaryFrame, "Invalid Input");
                    error = true;
                }
            } while (error);

            payPeriod = JOptionPane.showInputDialog("Enter the pay period (dd/mm/yyyy to dd/mm/yyyy): ");
            currentSalary = new Salary((Employee) employees.get(payId), currentWorkingHours,
                    payPeriod);
            currentSalary.write();
            observers.add((Employee) employees.get(payId));
            notifyObserver();
            currentSalary.earnings(salaryFrame);
            salaryRecord.put(payPeriod, currentSalary);
            employeeSalaryRecord.put(payId, salaryRecord);

            salaryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            salaryFrame.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(salaryFrame, "ID does not exist!!! Please try again.");
        }
    }

}
