package ui.GUI;

import Exceptions.LessThanMinWageException;
import model.Administration;
import model.CompanyStore;
import model.Employee;
import model.Worker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import static ui.GUI.UI.runGUI;
import static ui.behindTheScenes.CreateNewEmployeeAndAdmin.getDataFromUser;

class CreateNewEmployeeAndAdmin {

    //EFFECTS: Creates a new Admin
    public static void makeNewAdmin(Scanner kb, Map employees, Map admins, Map<String, ArrayList<Worker>> stores, Map employeeSalaryRecord, Map salaryRecord) throws IOException, LessThanMinWageException {

        String authKey = JOptionPane.showInputDialog("Enter Authorization Key: ");
        JFrame getDataFrame = new JFrame("Administration");
        getDataFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                runGUI(kb, employees, admins, employeeSalaryRecord, salaryRecord, stores);
            }
        });
        // If authorized then creates a new admin profile
        if (authKey.equals("SuperStore1517")) {
            String worker = "Admin's";
            Administration newAdmin = new Administration();

            String password = JOptionPane.showInputDialog("Enter new password: ");
            newAdmin.setPassword(password);
            SpringLayout springLayout = new SpringLayout();
            getDataFrame.setLayout(springLayout);
            getDataFrame.setSize(400, 250);
            JLabel nameLabel = new JLabel("Enter " + worker + " Name: ");
            getDataFrame.add(nameLabel);
            springLayout.putConstraint(SpringLayout.WEST, nameLabel, 20, SpringLayout.WEST, getDataFrame);
            springLayout.putConstraint(SpringLayout.NORTH, nameLabel, 20, SpringLayout.NORTH, getDataFrame);
            JTextField nameField = new JTextField(12);
            getDataFrame.add(nameField);
            springLayout.putConstraint(SpringLayout.WEST, nameField, 200, SpringLayout.WEST, nameLabel);
            springLayout.putConstraint(SpringLayout.NORTH, nameField, 20, SpringLayout.NORTH, getDataFrame);
            JLabel idLabel = new JLabel("Enter " + worker + " ID: ");
            getDataFrame.add(idLabel);
            springLayout.putConstraint(SpringLayout.WEST, idLabel, 20, SpringLayout.WEST, getDataFrame);
            springLayout.putConstraint(SpringLayout.NORTH, idLabel, 20, SpringLayout.NORTH, nameLabel);
            JTextField idField = new JTextField(12);
            getDataFrame.add(idField);
            springLayout.putConstraint(SpringLayout.WEST, idField, 200, SpringLayout.WEST, idLabel);
            springLayout.putConstraint(SpringLayout.NORTH, idField, 20, SpringLayout.NORTH, nameField);
            JLabel posLabel = new JLabel("Enter " + worker + " position: ");
            getDataFrame.add(posLabel);
            springLayout.putConstraint(SpringLayout.WEST, posLabel, 20, SpringLayout.WEST, getDataFrame);
            springLayout.putConstraint(SpringLayout.NORTH, posLabel, 20, SpringLayout.NORTH, idLabel);
            JTextField posField = new JTextField(12);
            getDataFrame.add(posField);
            springLayout.putConstraint(SpringLayout.WEST, posField, 200, SpringLayout.WEST, posLabel);
            springLayout.putConstraint(SpringLayout.NORTH, posField, 20, SpringLayout.NORTH, idField);
            JLabel wageLabel = new JLabel("Enter " + worker + " wage: ");
            getDataFrame.add(wageLabel);
            springLayout.putConstraint(SpringLayout.WEST, wageLabel, 20, SpringLayout.WEST, getDataFrame);
            springLayout.putConstraint(SpringLayout.NORTH, wageLabel, 20, SpringLayout.NORTH, posLabel);
            JTextField wageField = new JTextField(12);
            getDataFrame.add(wageField);
            springLayout.putConstraint(SpringLayout.WEST, wageField, 200, SpringLayout.WEST, wageLabel);
            springLayout.putConstraint(SpringLayout.NORTH, wageField, 20, SpringLayout.NORTH, posField);
            JLabel yearLabel = new JLabel("Enter " + worker + " start year: ");
            getDataFrame.add(yearLabel);
            springLayout.putConstraint(SpringLayout.WEST, yearLabel, 20, SpringLayout.WEST, getDataFrame);
            springLayout.putConstraint(SpringLayout.NORTH, yearLabel, 20, SpringLayout.NORTH, wageLabel);
            JTextField yearField = new JTextField(12);
            getDataFrame.add(yearField);
            springLayout.putConstraint(SpringLayout.WEST, yearField, 200, SpringLayout.WEST, yearLabel);
            springLayout.putConstraint(SpringLayout.NORTH, yearField, 20, SpringLayout.NORTH, wageField);
            JLabel storeLabel = new JLabel("Enter the store code: ");
            getDataFrame.add(storeLabel);
            springLayout.putConstraint(SpringLayout.WEST, storeLabel, 20, SpringLayout.WEST, getDataFrame);
            springLayout.putConstraint(SpringLayout.NORTH, storeLabel, 20, SpringLayout.NORTH, yearLabel);
            JTextField storeField = new JTextField(12);
            getDataFrame.add(storeField);
            springLayout.putConstraint(SpringLayout.WEST, storeField, 200, SpringLayout.WEST, storeLabel);
            springLayout.putConstraint(SpringLayout.NORTH, storeField, 20, SpringLayout.NORTH, yearField);
            JButton submit = new JButton("Submit");
            getDataFrame.add(submit);
            springLayout.putConstraint(SpringLayout.WEST, submit, 200, SpringLayout.WEST, storeLabel);
            springLayout.putConstraint(SpringLayout.NORTH, submit, 40, SpringLayout.NORTH, storeLabel);
            getDataFrame.setVisible(true);

            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    newAdmin.setName(nameField.getText());
                    newAdmin.setID(idField.getText());
                    newAdmin.setPosition(posField.getText());
                    double wage = Double.parseDouble(wageField.getText());
                    try {
                        tryThrowAndHandleMinWageException(newAdmin, wage);
                    } catch (LessThanMinWageException ignored) {
                        getDataFrame.setVisible(false);
                        actionPerformed(e);
                    }
                    newAdmin.setStartYear(yearField.getText());
                    String store = storeField.getText();
                    newAdmin.setStoreCode(store);
                    CompanyStore companyStore = new CompanyStore(store);
                    admins.put(newAdmin.getID(), newAdmin.getPassword());

                    newAdmin.setEmployee(companyStore);
                    Employee adm = newAdmin.getEmp();
                    adm.getInfoAdm(kb, employees, stores, employeeSalaryRecord, salaryRecord, admins);
                    try {
                        newAdmin.write();
                    } catch (Exception e1) {
                        actionPerformed(e);
                    }
                    addToStore(stores, newAdmin, store);
                    employees.put(newAdmin.getID(), adm);
                    getDataFrame.setVisible(false);


                }
            });


        } else {
            JOptionPane.showMessageDialog(getDataFrame, "Wrong Authorization Key!!");
            runGUI(kb, employees, admins, employeeSalaryRecord, salaryRecord, stores);
        }
    }


    //EFFECTS: Adds a new Employee
    public static void createAnEmployee(Scanner kb, Map employees, Map<String, ArrayList<Worker>> stores, Map employeeSalaryRecord, Map salaryRecord, JFrame adminFrame) {

        Employee newEmp = new Employee();
        String worker = "employee's";
        JFrame getDataFrame = new JFrame("User Data");
        SpringLayout springLayout = new SpringLayout();
        getDataFrame.setLayout(springLayout);
        getDataFrame.setSize(400, 250);
        JLabel nameLabel = new JLabel("Enter " + worker + " Name: ");
        getDataFrame.add(nameLabel);
        springLayout.putConstraint(SpringLayout.WEST, nameLabel, 20, SpringLayout.WEST, getDataFrame);
        springLayout.putConstraint(SpringLayout.NORTH, nameLabel, 20, SpringLayout.NORTH, getDataFrame);
        JTextField nameField = new JTextField(12);
        getDataFrame.add(nameField);
        springLayout.putConstraint(SpringLayout.WEST, nameField, 200, SpringLayout.WEST, nameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, nameField, 20, SpringLayout.NORTH, getDataFrame);
        JLabel idLabel = new JLabel("Enter " + worker + " ID: ");
        getDataFrame.add(idLabel);
        springLayout.putConstraint(SpringLayout.WEST, idLabel, 20, SpringLayout.WEST, getDataFrame);
        springLayout.putConstraint(SpringLayout.NORTH, idLabel, 20, SpringLayout.NORTH, nameLabel);
        JTextField idField = new JTextField(12);
        getDataFrame.add(idField);
        springLayout.putConstraint(SpringLayout.WEST, idField, 200, SpringLayout.WEST, idLabel);
        springLayout.putConstraint(SpringLayout.NORTH, idField, 20, SpringLayout.NORTH, nameField);
        JLabel posLabel = new JLabel("Enter " + worker + " position: ");
        getDataFrame.add(posLabel);
        springLayout.putConstraint(SpringLayout.WEST, posLabel, 20, SpringLayout.WEST, getDataFrame);
        springLayout.putConstraint(SpringLayout.NORTH, posLabel, 20, SpringLayout.NORTH, idLabel);
        JTextField posField = new JTextField(12);
        getDataFrame.add(posField);
        springLayout.putConstraint(SpringLayout.WEST, posField, 200, SpringLayout.WEST, posLabel);
        springLayout.putConstraint(SpringLayout.NORTH, posField, 20, SpringLayout.NORTH, idField);
        JLabel wageLabel = new JLabel("Enter " + worker + " wage: ");
        getDataFrame.add(wageLabel);
        springLayout.putConstraint(SpringLayout.WEST, wageLabel, 20, SpringLayout.WEST, getDataFrame);
        springLayout.putConstraint(SpringLayout.NORTH, wageLabel, 20, SpringLayout.NORTH, posLabel);
        JTextField wageField = new JTextField(12);
        getDataFrame.add(wageField);
        springLayout.putConstraint(SpringLayout.WEST, wageField, 200, SpringLayout.WEST, wageLabel);
        springLayout.putConstraint(SpringLayout.NORTH, wageField, 20, SpringLayout.NORTH, posField);
        JLabel yearLabel = new JLabel("Enter " + worker + " start year: ");
        getDataFrame.add(yearLabel);
        springLayout.putConstraint(SpringLayout.WEST, yearLabel, 20, SpringLayout.WEST, getDataFrame);
        springLayout.putConstraint(SpringLayout.NORTH, yearLabel, 20, SpringLayout.NORTH, wageLabel);
        JTextField yearField = new JTextField(12);
        getDataFrame.add(yearField);
        springLayout.putConstraint(SpringLayout.WEST, yearField, 200, SpringLayout.WEST, yearLabel);
        springLayout.putConstraint(SpringLayout.NORTH, yearField, 20, SpringLayout.NORTH, wageField);
        JLabel storeLabel = new JLabel("Enter the store code: ");
        getDataFrame.add(storeLabel);
        springLayout.putConstraint(SpringLayout.WEST, storeLabel, 20, SpringLayout.WEST, getDataFrame);
        springLayout.putConstraint(SpringLayout.NORTH, storeLabel, 20, SpringLayout.NORTH, yearLabel);
        JTextField storeField = new JTextField(12);
        getDataFrame.add(storeField);
        springLayout.putConstraint(SpringLayout.WEST, storeField, 200, SpringLayout.WEST, storeLabel);
        springLayout.putConstraint(SpringLayout.NORTH, storeField, 20, SpringLayout.NORTH, yearField);
        JButton submit = new JButton("Submit");
        getDataFrame.add(submit);
        springLayout.putConstraint(SpringLayout.WEST, submit, 200, SpringLayout.WEST, storeLabel);
        springLayout.putConstraint(SpringLayout.NORTH, submit, 40, SpringLayout.NORTH, storeLabel);
        getDataFrame.setVisible(true);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newEmp.setName(nameField.getText());
                newEmp.setID(idField.getText());
                newEmp.setPosition(posField.getText());
                double wage = Double.parseDouble(wageField.getText());
                try {
                    tryThrowAndHandleMinWageException(newEmp, wage);
                } catch (LessThanMinWageException ignored) {
                    getDataFrame.setVisible(false);
                    actionPerformed(e);
                }
                newEmp.setStartYear(yearField.getText());
                String store = storeField.getText();
                newEmp.setStoreCode(store);
                CompanyStore companyStore = new CompanyStore(storeField.getText(), newEmp);
                employees.put(newEmp.getID(), newEmp);
                addToStore(stores, newEmp, store);


                try {
                    newEmp.write();
                } catch (IOException ignored) {
                }
                newEmp.getInfo(kb, employees, stores, employeeSalaryRecord, salaryRecord);
                getDataFrame.setVisible(false);


            }
        });
    }


    //EFFECTS: Adds a given employee to sore if not already there
    private static void addToStore(Map<String, ArrayList<Worker>> stores, Worker newEmp, String storeCode) {
        if (stores.containsKey(storeCode)) {
            ArrayList<Worker> tempEmp = stores.get(storeCode);
            tempEmp.add(newEmp);
            stores.put(storeCode, tempEmp);

        } else {
            ArrayList<Worker> tempEmp = new ArrayList<>();
            tempEmp.add(newEmp);
            stores.put(storeCode, tempEmp);
        }
    }

    private static void tryThrowAndHandleMinWageException(Worker x, double wage) throws LessThanMinWageException {
        try {
            x.setWage(wage);
        } catch (LessThanMinWageException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Entered wage is less than the minimum wage!!!\nWage set to default(12.65)");
            x.setWage(12.66);
        }
    }


}
