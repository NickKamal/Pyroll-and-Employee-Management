package ui.behindTheScenes;
import observer.*;
import model.CompanyStore;
import model.Employee;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class StoreEmployeeRelation extends Subject {

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
    public static void giveTheStoreEmployeeList(Scanner kb, Map<String, ArrayList<Employee>> stores) {
        System.out.println("Enter the store code: ");
        String storeCode = kb.nextLine();
        if (stores.containsKey(storeCode)) {
            System.out.println("List of employees: ");
            if (stores.containsKey(storeCode)) {
                ArrayList<Employee> emp = stores.get(storeCode);
                for (Employee e : emp) {
                    System.out.println("Name: " + e.getName() + "     ID: " + e.getID());
                }
            }
        } else {
            System.out.println("Store not found!!!");
        }
    }
}
