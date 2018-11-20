package model;

import Exceptions.LessThanMinWageException;
import observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Employee extends Worker {


    private final ArrayList<CompanyStore> stores = new ArrayList<>();
    private CompanyStore com;
    private String update = "";


    public Employee(String s1, String s2, String s3, double d, String s4, String s5, CompanyStore companyStore) throws LessThanMinWageException {
        name = s1;
        position = s2;
        id = s3;
        if (d < 12.65) {
            throw new LessThanMinWageException();
        }
        wagePerHour = d;
        startYear = s4;
        storeCode = s5;
        com = companyStore;
        addStore(companyStore);

    }

    /**
     * Creates an Employee's profile in the system.
     */

    public Employee() {
    }


    public void write() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("empInfo.txt", true));
        writer.print(EncryptDecrypt.encrypt(getName()) + "," + EncryptDecrypt.encrypt(getID()) +
                "," + EncryptDecrypt.encrypt(getPosition()) + "," + getWage()
                + "," + getStartYear() + "," + getStoreCode() + ",");
        for (CompanyStore companyStore : stores) {
            if (!Objects.equals(companyStore.getStoreCode(), storeCode)) {
                writer.print(companyStore.getStoreCode() + ",");

            }
        }
        writer.println();

        writer.close();

    }


    @Override
    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public void addStore(CompanyStore companyStore) {
        if (!stores.contains(companyStore)) {
            stores.add(companyStore);
            companyStore.addEmployee(this);
        }
    }

    public void removeStore(CompanyStore companyStore) {
        if (stores.contains(companyStore)) {
            stores.remove(companyStore);
            companyStore.removeEmployee(this);
        }
    }


    private void getStore() {

        for (CompanyStore companyStore : stores) {
            System.out.println("Store no. : " + companyStore.getStoreCode());
        }
    }

    private String getStore(JFrame frame) {
        String[] columnNames = {"",
                ""};
        Object[][] data;
        String storeList = "";
        for (CompanyStore companyStore : stores) {
            storeList += companyStore.getStoreCode() + "\n";
        }
        return storeList;
    }

    public void getInfo(JFrame frame) {
        JFrame thisFrame = new JFrame("Info");
        thisFrame.setSize(300,300);
        thisFrame.setVisible(true);
        String[] columnNames = {"",
                ""};
        Object[][] data;

        data = new Object[][]{
                {"", "Updated Info"},
                {"Name: ", name},
                {"Position: ", position},
                {"ID: ",id},
                {"Wage per hour: ", wagePerHour},
                {"Start Date: ", startYear},
                {"Store no.: ", getStore(frame)}
        };


        JTable table = new JTable(data, columnNames);
        table.setFont(table.getFont().deriveFont(Font.BOLD));
        table.setRowHeight(25);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        thisFrame.add(table, BorderLayout.CENTER);

    }

    public void getInfo() {
        System.out.println(update);
        System.out.println("Name: " + name);
        System.out.println("Position: " + position);
        System.out.println("ID: " + id);
        System.out.println("Wage per hour: " + wagePerHour);
        System.out.println("Start Date: " + startYear);
        getStore();
    }

    public CompanyStore getCompanyStore() {
        return com;
    }

    @Override
    public void update() {
        update = "There has been an update in employee records!!";
        Log.addToLog(id, new Date());
    }

}
