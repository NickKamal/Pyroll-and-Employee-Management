package ui.GUI;

import Exceptions.LessThanMinWageException;
import model.Employee;
import model.Salary;
import observer.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import static java.awt.Component.CENTER_ALIGNMENT;
import static java.awt.font.GraphicAttribute.TOP_ALIGNMENT;
import static ui.GUI.UI.option1sub;
import static ui.behindTheScenes.CreateNewEmployeeAndAdmin.tryThrowAndHandleMinWageException;

class ViewOrModifyEmployeeRecords extends Subject {

    //EFFECTS: prints the sa;ary records of the given employee
    public static void showMeThePayRecords(Scanner kb, Map employeeSalaryRecord, Map salaryRecord, Map employees, Map<String, ArrayList<Employee>> stores) {
        JFrame frame = new JFrame();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                option1sub(kb, employees, stores, employeeSalaryRecord, salaryRecord);

            }
        });
        frame.setSize(320, 380);
        String id = JOptionPane.showInputDialog("Enter the Employee's ID: ");
        if (employeeSalaryRecord.containsKey(id)) {
            String wagePeriod = JOptionPane.showInputDialog("Enter the wage period(dd/mm/yyyy to dd/mm/yyyy): ");
            Map salaryRecords = (Map) employeeSalaryRecord.get(id);
            if (salaryRecord.containsKey(wagePeriod)) {
                Salary neededRecord = (Salary) salaryRecord.get(wagePeriod);
                neededRecord.earnings(frame);
            } else {
                JOptionPane.showMessageDialog(frame, "No record found!!!");

            }
        } else {
            JOptionPane.showMessageDialog(frame, "ID not found!!");
        }
    }


    //EFFECTS: menu function to view an employee's info or to modify it
    public static void viewOrModifyEmployeeInfo(Scanner kb, Map employees, Map<String, ArrayList<Employee>> stores, Map employeeSalaryRecord, Map salaryRecord) {
        String id = JOptionPane.showInputDialog("Please enter the ID of the employee: ");
        JFrame adminFrame = new JFrame("\nWelcome!\n");
        if (employees.containsKey(id)) {
            Employee emp = (Employee) employees.get(id);

            adminFrame.setLayout(new FlowLayout());
            adminFrame.setSize(400, 200);
            JPanel adminPanel = new JPanel();
            adminPanel.setLayout(new BoxLayout(adminPanel, BoxLayout.PAGE_AXIS));
            adminFrame.add(adminPanel, CENTER_ALIGNMENT);
            adminFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent windowEvent) {
                    try {
                        option1sub(kb, employees, stores, employeeSalaryRecord, salaryRecord);
                    } catch (Exception ignored) {

                    }
                }
            });
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
                    emp.getInfo(adminFrame, kb, employees, stores, employeeSalaryRecord, salaryRecord);
                    observers.add(emp);
                    notifyObserver();
                    try {
                        emp.write();
                    } catch (Exception ignored) {

                    }
                }
            });
            op2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adminFrame.setVisible(false);
                    emp.setPosition(JOptionPane.showInputDialog("Please enter the new position: "));
                    emp.getInfo(adminFrame, kb, employees, stores, employeeSalaryRecord, salaryRecord);
                    observers.add(emp);
                    notifyObserver();

                    try {
                        emp.write();
                    } catch (Exception ignored) {

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
                    emp.getInfo(adminFrame, kb, employees, stores, employeeSalaryRecord, salaryRecord);

                    try {
                        emp.write();
                    } catch (Exception ignored) {

                    }
                }
            });
            op4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adminFrame.setVisible(false);
                    emp.getInfo(adminFrame, kb, employees, stores, employeeSalaryRecord, salaryRecord);
                    observers.add(emp);
                    notifyObserver();

                    try {
                        emp.write();
                    } catch (Exception ignored) {
                    }
                }
            });
        } else {
            JOptionPane.showMessageDialog(adminFrame, "ID not Found!!");
            viewOrModifyEmployeeInfo(kb, employees, stores, employeeSalaryRecord, salaryRecord);
        }


    }
}
