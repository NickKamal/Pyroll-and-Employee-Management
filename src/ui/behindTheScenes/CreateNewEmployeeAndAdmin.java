package ui.behindTheScenes;

import Exceptions.LessThanMinWageException;
import model.Administration;
import model.CompanyStore;
import model.Employee;
import model.Worker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class CreateNewEmployeeAndAdmin {

    //EFFECTS: Creates a new Admin
    public static void makeNewAdmin(Scanner kb, Map employees, Map admins, Map<String, ArrayList<Employee>> stores)
            throws LessThanMinWageException, IOException {
        System.out.println("Enter Authorization Key: ");
        String authKey = kb.nextLine();

        // If authorized then creates a new admin profile
        if (authKey.equals("SuperStore1517")) {
            String worker = "Admin's";
            Administration newAdmin = new Administration();
            String storeCode = getDataFromUser(kb, worker, newAdmin);
            CompanyStore companyStore = new CompanyStore(storeCode);
            admins.put(newAdmin.getID(), newAdmin.getPassword());
            newAdmin.getInfo();
            newAdmin.setEmployee(companyStore);
            Employee adm = newAdmin.getEmp();
            addToStore(stores, newAdmin, storeCode);
            employees.put(newAdmin.getID(), adm);
            newAdmin.write();

        } else {
            System.out.println("Wrong Authorization Key!!");
        }
    }


    //EFFECTS: Adds a new Employee
    public static void createAnEmployee(Scanner kb, Map employees, Map<String, ArrayList<Employee>> stores)
            throws LessThanMinWageException, IOException {
        Employee newEmp = new Employee();
        String worker = "employee's";
        String storeCode = getDataFromUser(kb, worker, newEmp);
        CompanyStore companyStore = new CompanyStore(storeCode, newEmp);
        employees.put(newEmp.getID(), newEmp);
        addToStore(stores, newEmp, storeCode);
        System.out.println("\nYou entered the following information: ");
        newEmp.getInfo();
        newEmp.write();

    }


    //EFFECTS: Adds a given employee to sore if not already there
    private static void addToStore(Map<String, ArrayList<Employee>> stores, Worker newEmp, String storeCode) {
        if (stores.containsKey(storeCode)) {
            ArrayList<Employee> tempEmp = stores.get(storeCode);
            tempEmp.add((Employee) newEmp);
            stores.put(storeCode, tempEmp);

        } else {
            ArrayList<Employee> tempEmp = new ArrayList<>();
            tempEmp.add((Employee) newEmp);
            stores.put(storeCode, tempEmp);
        }
    }

    public static void tryThrowAndHandleMinWageException(Worker x, double wage) throws LessThanMinWageException {
        try {
            x.setWage(wage);
        } catch (LessThanMinWageException e) {
            System.out.println("Entered wage is less than the minimum wage!!!");
            System.out.println("Wage set to default(12.65)");
            x.setWage(12.66);
        }
    }


    //EFFECTS: prompts user for new profile data and returns the storeCode
    private static String getDataFromUser(Scanner kb, String worker, Worker x) throws LessThanMinWageException {
        System.out.println("Enter " + worker + " Name: ");
        x.setName(kb.nextLine());
        System.out.println("Enter " + worker + " ID: ");
        x.setID(kb.nextLine());
        System.out.println("Enter " + worker + " position: ");
        x.setPosition(kb.nextLine());
        System.out.println("Enter " + worker + " wage: ");
        double wage = kb.nextFloat();
        tryThrowAndHandleMinWageException(x, wage);
        kb.nextLine();
        System.out.println("Enter " + worker + " start year: ");
        x.setStartYear(kb.nextLine());
        System.out.println("Enter the store code: ");
        String storeCode = kb.nextLine();
        x.setStoreCode(storeCode);
        return storeCode;
    }
}
