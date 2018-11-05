
package ui;

import Exceptions.LessThanMinWageException;
import model.*;
import java.io.IOException;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;


class Payroll {

    public static void main(String[] args) throws IOException, LessThanMinWageException {
        // Initialize a scanner object
        Scanner kb = new Scanner(System.in);

        // Creates an employee dictionary
        Map employees = new HashMap();

        // Creates an admin dictionary
        Map admins = new HashMap();

        //Creates an employee-salary dictionary
        Map employeeSalaryRecord = new HashMap<>();

        //Creates a payPeriod-Salary dictionary
        Map salaryRecord = new HashMap();

        //Creates a storeNo-EmployeeList dictionary
        Map<String, ArrayList<Employee>> stores = new HashMap();

        //flag for the main menu
        boolean flag = false;


        // Pointers to lines for reading
        readData(employees, admins, salaryRecord, employeeSalaryRecord, stores);


        do {

            // Asks for user input
            System.out.println("\nPlease enter one of the following options (1, 2, or 3): ");
            System.out.println("1: Administration login.");
            System.out.println("2: Create an administration account.");
            System.out.println("3: Close the program.");
            int option = kb.nextInt();
            kb.nextLine();

            // Execution if user enters option 1
            if (option == 1) {
                System.out.println("Enter Admin's ID: ");
                String loginID = kb.nextLine();

                // Checks if the admin is in the list
                if (admins.containsKey(loginID)) {
                    System.out.println("Enter password: ");
                    String loginPassword = kb.nextLine();

                    // Password authentication
                    if (admins.get(loginID).equals(loginPassword)) {
                        boolean check;
                        do {
                            // Asks for admin option input
                            System.out.println("\nWelcome!\n");
                            System.out.println("Please enter one of the following options (1, 2, 3, or 4): ");
                            System.out.println("1: Add a new employee");
                            System.out.println("2: Payroll Calculation.");
                            System.out.println("3. View or Modify an employee's info.");
                            System.out.println("4: Payroll Records.");
                            System.out.println("5: Get employee list of a store.");
                            System.out.println("6: Add an existing employee to a store.");
                            System.out.println("7: Remove an existing employee from a store.");
                            System.out.println("8: Close the program.");
                            int pick = kb.nextInt();
                            kb.nextLine();


                            if (pick == 1) {
                                createAnEmployee(kb, employees, stores);
                                check = true;
                            } else if (pick == 2) {
                                calculateSalary(kb, employees, employeeSalaryRecord, salaryRecord);
                                check = true;
                            } else if (pick == 3) {
                                viewOrModifyEmployeeInfo(kb, employees);
                                check = true;
                            } else if (pick == 4) {
                                showMeThePayRecords(kb, employeeSalaryRecord, salaryRecord);
                                check = true;
                            } else if (pick == 5) {
                                giveTheStoreEmployeeList(kb, stores);
                                check = true;

                            } else if (pick == 6) {
                                addEmployeeToStore(kb, employees);
                                check = true;
                            } else if (pick == 7) {
                                removeEmployeeFromStore(kb, employees);
                                check = true;
                            } else if (pick == 8) {
                                check = false;
                            }

                            // Deals with invalid input
                            else {
                                System.out.println("Invalid input!!!\n");
                                check = true;

                            }
                        } while (check);
                    }

                    // Deals with wrong password
                    else {
                        System.out.println("Wrong Password!!\n");
                        flag = true;
                    }
                }

                // Deals with admin not in the list
                else {
                    System.out.println("Admin not found!!\n");
                    flag = true;
                }
            }


            // Execution if user enters option.
            else if (option == 2) {
                makeNewAdmin(kb, employees, admins, stores);
                flag = true;

            }

            // Execution if user chooses option 3
            else if (option == 3) {
                flag = false;
            }

            // Deals with invalid option
            else {
                System.out.println("Invalid Option!");
                flag = true;
            }


        } while (flag);


    }

    //EFFECTS: Creates a new Admin
    private static void makeNewAdmin(Scanner kb, Map employees, Map admins, Map<String, ArrayList<Employee>> stores)
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

    //EFFECTS: Removes employee from the  given store
    private static void removeEmployeeFromStore(Scanner kb, Map employees) throws IOException {
        System.out.println("Enter the employee's id: ");
        String id = kb.nextLine();
        if (employees.containsKey(id)) {
            Employee emp = (Employee) employees.get(id);
            System.out.println("Enter the store number: ");
            String store = kb.nextLine();
            CompanyStore comp = new CompanyStore(store);
            emp.removeStore(comp);
            emp.write();
            emp.getInfo();
        } else {
            System.out.println("ID not found!!!");
        }
    }

    //EFFECTS: Adds employee to the given store
    private static void addEmployeeToStore(Scanner kb, Map employees) throws IOException {
        System.out.println("Enter the employee's id: ");
        String id = kb.nextLine();
        if (employees.containsKey(id)) {
            Employee emp = (Employee) employees.get(id);
            System.out.println("Enter the store number: ");
            String store = kb.nextLine();
            CompanyStore comp = new CompanyStore(store);
            emp.addStore(comp);
            emp.write();
            emp.getInfo();
        } else {
            System.out.println("ID not found!!!");
        }
    }

    //EFFECTS: returns the list of employees working in the given store
    private static void giveTheStoreEmployeeList(Scanner kb, Map<String, ArrayList<Employee>> stores) {
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

    //EFFECTS: prints the sa;ary records of the given employee
    private static void showMeThePayRecords(Scanner kb, Map employeeSalaryRecord, Map salaryRecord) {
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
    private static void viewOrModifyEmployeeInfo(Scanner kb, Map employees)
            throws IOException, LessThanMinWageException {
        System.out.println("Please enter the ID of the employee: ");
        String id = kb.nextLine();
        if (employees.containsKey(id)) {
            Employee emp = (Employee) employees.get(id);
            System.out.println("Choose one of the following options (1, 2, 3, or 4):");
            System.out.println("1. Change the employee's name: ");
            System.out.println("2. Change the employee's position: ");
            System.out.println("3. Change the employee's wage per hour; ");
            System.out.println("4. View the employee's info: ");
            int input = kb.nextInt();
            kb.nextLine();
            switch (input) {
                case 1: {
                    System.out.println("Please enter the new name: ");
                    emp.setName(kb.nextLine());
                    emp.getInfo();
                    break;
                }
                case 2: {
                    System.out.println("Please enter the new position: ");
                    emp.setPosition(kb.nextLine());
                    emp.getInfo();
                    break;
                }
                case 3: {
                    System.out.println("Please enter the new wage per hour: ");
                    try {
                        emp.setWage(kb.nextFloat());
                    } catch (LessThanMinWageException e) {
                        System.out.println("Entered wage is less than the minimum wage!!!");
                        System.out.println("Wage set to default(12.65)");
                        emp.setWage(12.66);
                    } finally {
                        emp.getInfo();
                        break;
                    }
                }
                case 4: {
                    emp.getInfo();
                    break;
                }
                default: {
                    System.out.println("\nInvalid entry!!\n");
                }
            }
            emp.write();
        } else {
            System.out.println("ID not Found!!");
        }
    }

    //EFFECTS: Calculates, stores and prints the salary of the given employee a
    private static void calculateSalary(Scanner kb, Map employees, Map employeeSalaryRecord, Map salaryRecord)
            throws IOException {
        Salary currentSalary;
        double currentWorkingHours = 0.0;
        System.out.println("Enter the employee's ID: ");
        String payId = kb.nextLine();
        String payPeriod;
        if (employees.containsKey(payId)) {
            System.out.println("Enter the number of hours for the pay period: ");
            boolean error;
            do {
                try {
                    currentWorkingHours = kb.nextDouble();
                    error = false;
                } catch (InputMismatchException i) {
                    kb.nextLine();
                    System.out.println("Invalid Input");
                    error = true;
                }
            } while (error);
            System.out.println("Enter the pay period (dd/mm/yyyy to dd/mm/yyyy): ");
            kb.nextLine();
            payPeriod = kb.nextLine();
            currentSalary = new Salary((Employee) employees.get(payId), currentWorkingHours,
                    payPeriod);
            currentSalary.write();
            currentSalary.earnings();
            salaryRecord.put(payPeriod, currentSalary);
            employeeSalaryRecord.put(payId, salaryRecord);

        } else {
            System.out.println("ID does not exist!!! Please try again.");
        }
    }

    //EFFECTS: Adds a new Employee
    private static void createAnEmployee(Scanner kb, Map employees, Map<String, ArrayList<Employee>> stores)
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
        try {
            x.setWage(wage);
        } catch (LessThanMinWageException e) {
            System.out.println("Entered wage is less than the minimum wage!!!");
            System.out.println("Wage set to default(12.65)");
            x.setWage(12.65);
        }
        kb.nextLine();
        System.out.println("Enter " + worker + " start year: ");
        x.setStartYear(kb.nextLine());
        System.out.println("Enter the store code: ");
        String storeCode = kb.nextLine();
        x.setStoreCode(storeCode);
        return storeCode;
    }

    private static void readData(Map employees, Map admins, Map salaryRecord, Map employeeSalaryRecord,
                                 Map<String, ArrayList<Employee>> stores) throws
            IOException, LessThanMinWageException {
        List<String> adm = Files.readAllLines(Paths.get("adminInfo.txt"));
        List<String> emp = Files.readAllLines(Paths.get("empInfo.txt"));
        List<String> wageRecords = Files.readAllLines(Paths.get("WageRecord.txt"));

        // Makes an array out of the read line and add key/value to admins
        for (String line : adm) {
            ArrayList<String> partsOfAdm = splitOnComma(line);
            admins.put(EncryptDecrypt.decrypt(partsOfAdm.get(1)), EncryptDecrypt.decrypt(partsOfAdm.get(5)));
        }

        // Makes an array out of the read line and add key/value to employees
        for (String line : emp) {
            ArrayList<String> partsOfEmp = splitOnComma(line);
            Employee employee = new Employee(EncryptDecrypt.decrypt(partsOfEmp.get(0)),
                    EncryptDecrypt.decrypt(partsOfEmp.get(2)),
                    EncryptDecrypt.decrypt(partsOfEmp.get(1)), Double.parseDouble(partsOfEmp.get(3)),
                    partsOfEmp.get(4), partsOfEmp.get(5), new CompanyStore(partsOfEmp.get(5)));
            for (int i = 5; i < partsOfEmp.size(); i++) {
                if (partsOfEmp.get(i) != null) {
                    if (stores.containsKey(partsOfEmp.get(i))) {
                        ArrayList<Employee> empList = stores.get(partsOfEmp.get(i));
                        empList.add(employee);
                        stores.put(partsOfEmp.get(i), empList);
                    } else {
                        ArrayList<Employee> tempList = new ArrayList<>();
                        tempList.add(employee);
                        employee.addStore(new CompanyStore(partsOfEmp.get(i)));
                        stores.put(partsOfEmp.get(i), tempList);
                    }

                }
            }
            employees.put(EncryptDecrypt.decrypt(partsOfEmp.get(1)), employee);
        }

        // Makes an array out of the read line and add key/value to salaries
        for (String line : wageRecords) {
            ArrayList<String> userData = splitOnComma(line);
            salaryRecord.put(userData.get(3)/*pay period*/,
                    new Salary(EncryptDecrypt.decrypt(userData.get(0)), userData.get(1),
                            userData.get(2), userData.get(3), userData.get(4),
                            userData.get(5), userData.get(6), userData.get(7),
                            userData.get(8)));

            employeeSalaryRecord.put(userData.get(1)/*id*/, salaryRecord);


        }

    }

    // EFFECTS: return an ArrayList out of the given line
    private static ArrayList<String> splitOnComma(String line) {
        String[] splits = line.split(",");
        return new ArrayList<>(Arrays.asList(splits));


    }
}
