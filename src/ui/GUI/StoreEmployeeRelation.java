package ui.GUI;

import model.CompanyStore;
import model.Employee;
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

import static ui.GUI.UI.option1sub;

class StoreEmployeeRelation extends Subject {

    //EFFECTS: Removes employee from the  given store
    public static void removeEmployeeFromStore(Scanner kb, Map employees) throws IOException {
        System.out.println("Enter the employee's id: ");
        String id = kb.nextLine();
        if (employees.containsKey(id)) {
            Employee emp = (Employee) employees.get(id);
            System.out.println("Enter the store number: ");
            String store = kb.nextLine();
            CompanyStore comp = new CompanyStore(store);
            emp.removeStore(comp);
            observers.add(emp);
            notifyObserver();
            emp.write();
            emp.getInfo();
        } else {
            System.out.println("ID not found!!!");
        }
    }

    //EFFECTS: Adds employee to the given store
    public static void addEmployeeToStore(Scanner kb, Map employees) throws IOException {
        System.out.println("Enter the employee's id: ");
        String id = kb.nextLine();
        if (employees.containsKey(id)) {
            Employee emp = (Employee) employees.get(id);
            System.out.println("Enter the store number: ");
            String store = kb.nextLine();
            CompanyStore comp = new CompanyStore(store);
            emp.addStore(comp);
            observers.add(emp);
            notifyObserver();
            emp.write();
            emp.getInfo();
        } else {
            System.out.println("ID not found!!!");
        }
    }

    //EFFECTS: returns the list of employees working in the given store
    public static void giveTheStoreEmployeeList(Scanner kb, Map employeeSalaryRecord, Map salaryRecord, Map employees, Map<String, ArrayList<Employee>> stores) {

        String storeCode = JOptionPane.showInputDialog("Enter the store code: ");
        JFrame storeFrame = new JFrame("List of employees in " + storeCode);
        storeFrame.setSize(400, 400);
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
                    option1sub(kb, employees, stores, employeeSalaryRecord, salaryRecord);

                }
            });
            storeFrame.setVisible(true);
            if (stores.containsKey(storeCode)) {
                ArrayList<Employee> emp = stores.get(storeCode);
                for (Employee e : emp) {
                    tableModel.addRow(new Object[]{e.getName(), e.getID()});
                }
            }
        } else {
            JOptionPane.showMessageDialog(storeFrame, "ID not found!!!");
            option1sub(kb, employees, stores, employeeSalaryRecord, salaryRecord);

        }
    }
}
