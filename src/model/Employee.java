package model;

import Exceptions.LessThanMinWageException;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Employee extends Worker {


    private final ArrayList<CompanyStore> stores = new ArrayList<>();
    private CompanyStore com;

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

    public void getInfo() {
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
}
