package ui.GUI;

import Exceptions.LessThanMinWageException;
import model.Employee;
import model.Salary;
import observer.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import static java.awt.Component.CENTER_ALIGNMENT;
import static java.awt.font.GraphicAttribute.TOP_ALIGNMENT;
import static ui.GUI.CreateNewEmployeeAndAdmin.createAnEmployee;
import static ui.behindTheScenes.CreateNewEmployeeAndAdmin.tryThrowAndHandleMinWageException;

public class ViewOrModifyEmployeeRecords extends Subject {

    //EFFECTS: prints the sa;ary records of the given employee
    public static void showMeThePayRecords(Scanner kb, Map employeeSalaryRecord, Map salaryRecord) {
        System.out.println("Enter the Employee's ID: ");
        String id = kb.nextLine();
        if (employeeSalaryRecord.containsKey(id)) {
            System.out.println("Enter the wage period(dd/mm/yyyy to dd/mm/yyyy): ");
            String wagePeriod = kb.nextLine();
            Map salaryRecords = (Map) employeeSalaryRecord.get(id);
            if (salaryRecord.containsKey(wagePeriod)) {
                Salary neededRecord = (Salary) salaryRecord.get(wagePeriod);
                neededRecord.earnings();
            } else {
                System.out.println("No record found!!!");
            }
        } else {
            System.out.println("ID not found!!");
        }
    }


    //EFFECTS: menu function to view an employee's info or to modify it
    public static void viewOrModifyEmployeeInfo(Scanner kb, Map employees)
            throws IOException, LessThanMinWageException {
        String id = JOptionPane.showInputDialog("Please enter the ID of the employee: ");
        JFrame adminFrame = new JFrame("\nWelcome!\n");
        if (employees.containsKey(id)) {
            Employee emp = (Employee) employees.get(id);

            adminFrame.setLayout(new FlowLayout());
            adminFrame.setSize(400, 200);
            JPanel adminPanel = new JPanel();
            adminPanel.setLayout(new BoxLayout(adminPanel, BoxLayout.PAGE_AXIS));
            adminFrame.add(adminPanel, CENTER_ALIGNMENT);
            adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JLabel menu = new JLabel("Choose one of the following options (1, 2, 3, or 4):");
            adminPanel.add(menu, TOP_ALIGNMENT);
            JButton op1 = new JButton("1. Change the employee's name: ");
            adminPanel.add(op1);
            JButton op2 = new JButton("2. Change the employee's position: ");
            adminPanel.add(op2);
            JButton op3 = new JButton("3. Change the employee's wage per hour; ");
            adminPanel.add(op3);
            JButton op4 = new JButton("4. View the employee's info: ");
            adminPanel.add(op4);
            adminFrame.setVisible(true);
            op1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adminFrame.setVisible(false);
                    emp.setName(JOptionPane.showInputDialog("Please enter the new name: "));
                    emp.getInfo(adminFrame);
                    observers.add(emp);
                    notifyObserver();
                    try {
                        emp.write();
                    } catch (Exception e1) {

                    }
                }
            });
            op2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adminFrame.setVisible(false);
                    emp.setPosition(JOptionPane.showInputDialog("Please enter the new position: "));
                    emp.getInfo(adminFrame);
                    observers.add(emp);
                    notifyObserver();

                    try {
                        emp.write();
                    } catch (Exception e1) {

                    }
                }
            });
            op3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        adminFrame.setVisible(false);
                        tryThrowAndHandleMinWageException(emp, Double.parseDouble(JOptionPane.showInputDialog("Please enter the new wage per hour: ")));
                    } catch (LessThanMinWageException e1) {
                        actionPerformed(e);
                    }
                    emp.getInfo(adminFrame);

                    try {
                        emp.write();
                    } catch (Exception e1) {

                    }
                }
            });
            op4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adminFrame.setVisible(false);
                    emp.getInfo(adminFrame);
                    observers.add(emp);
                    notifyObserver();

                    try {
                        emp.write();
                    } catch (Exception e1) {
                                            }
                }
            });
        } else {
            JOptionPane.showMessageDialog(adminFrame, "ID not Found!!");
            viewOrModifyEmployeeInfo(kb, employees);
        }


    }
}
