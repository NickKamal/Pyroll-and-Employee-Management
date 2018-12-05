package ui.GUI;

import model.CompanyStore;
import model.Employee;
import model.Worker;
import observer.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import static ui.GUI.CreateNewEmployeeAndAdmin.addToStore;
import static ui.GUI.UI.option1sub;

class StoreEmployeeRelation extends Subject {

    //EFFECTS: Removes employee from the  given store
    public static void removeEmployeeFromStore(Scanner kb, Map employees, Map<String, ArrayList<Worker>> stores, Map employeeSalaryRecord, Map salaryRecord, Map admins) throws IOException {

        String id = JOptionPane.showInputDialog("Enter the employee's id: ");
        if (employees.containsKey(id)) {
            Employee emp = (Employee) employees.get(id);
            String store = JOptionPane.showInputDialog(new JFrame(), "Enter the store number: ");
            CompanyStore comp = new CompanyStore(store);
            emp.removeStore(comp);
            comp.removeEmployee(emp);
            stores.get(store).remove(emp);
            observers.add(emp);
            notifyObserver();
            emp.write();
            emp.getInfo(kb, employees, stores, employeeSalaryRecord, salaryRecord, admins);
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "ID not found!!!");
            option1sub(kb, employees, stores, employeeSalaryRecord, salaryRecord, admins);
        }
    }

    //EFFECTS: Adds employee to the given store
    public static void addEmployeeToStore(Scanner kb, Map employees, Map<String, ArrayList<Worker>> stores, Map employeeSalaryRecord, Map salaryRecord, Map admins) throws IOException {
        String id = JOptionPane.showInputDialog("Enter the employee's id: ");
        if (employees.containsKey(id)) {
            Employee emp = (Employee) employees.get(id);
            String store = JOptionPane.showInputDialog(new JFrame(), "Enter the store number: ");
            CompanyStore comp = new CompanyStore(store);
            emp.addStore(comp);
            addToStore(stores, emp, store);
            observers.add(emp);
            notifyObserver();
            emp.write();
            emp.getInfo(kb, employees, stores, employeeSalaryRecord, salaryRecord, admins);
        } else {
            System.out.println();
            JOptionPane.showMessageDialog(new JFrame(), "ID not found!!!");
            option1sub(kb, employees, stores, employeeSalaryRecord, salaryRecord, admins);
        }
    }

    //EFFECTS: returns the list of employees working in the given store
    public static void giveTheStoreEmployeeList(Scanner kb, Map employeeSalaryRecord, Map salaryRecord, Map employees, Map<String, ArrayList<Worker>> stores, Map admins) {

        String storeCode = JOptionPane.showInputDialog("Enter the store code: ");
        JFrame storeFrame = new JFrame("List of employees in " + storeCode);
        storeFrame.setSize(500, 400);
        if (stores.containsKey(storeCode)) {

            storeFrame.setLayout(new FlowLayout());
            DefaultTableModel tableModel = new DefaultTableModel();
            JTable table = new JTable(tableModel);
            tableModel.addColumn("Employee Name");
            tableModel.addColumn("Employee ID");
            storeFrame.add(new JScrollPane(table));
            storeFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    option1sub(kb, employees, stores, employeeSalaryRecord, salaryRecord, admins);

                }
            });
            storeFrame.setVisible(true);
            if (stores.containsKey(storeCode)) {
                ArrayList<Worker> emp = stores.get(storeCode);
                for (Worker e : emp) {
                    tableModel.addRow(new Object[]{e.getName(), e.getID()});
                }
            }
        } else {
            JOptionPane.showMessageDialog(storeFrame, "ID not found!!!");
            option1sub(kb, employees, stores, employeeSalaryRecord, salaryRecord, admins);

        }
    }
}
