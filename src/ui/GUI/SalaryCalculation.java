package ui.GUI;

import model.Employee;
import model.Salary;
import model.Worker;
import observer.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import static ui.GUI.UI.option1sub;

class SalaryCalculation extends Subject {

    //EFFECTS: Calculates, stores and prints the salary of the given employee a
    public static void calculateSalary(Scanner kb, Map employees, Map employeeSalaryRecord, Map salaryRecord, Map<String, ArrayList<Worker>> stores)
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
            salaryFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    option1sub(kb, employees, stores, employeeSalaryRecord, salaryRecord);

                }
            });
            salaryFrame.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(salaryFrame, "ID does not exist!!! Please try again.");
            option1sub(kb, employees, stores, employeeSalaryRecord, salaryRecord);
        }
    }

}
