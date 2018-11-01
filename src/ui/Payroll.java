
package ui;

import Exceptions.LessThanMinWageException;
import model.*;


import java.io.IOException;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Payroll {

    public static void main(String[] args) throws IOException, LessThanMinWageException {
        // Initialize a scanner object
        Scanner kb = new Scanner(System.in);

        // Creates an employee dictionary
        Map employees;


        employees = new HashMap();

        // Creates an admin dictionary
        Map admins = new HashMap();

        //Creates a employee-salary dictionary
        Map employeeSalaryRecord = new HashMap<>();

        //Creates a payPeriod-Salary dictionary
        Map salaryRecord = new HashMap();

        Map<String, ArrayList<Employee>> stores = new HashMap();

        ArrayList<Employee> listOfEmplyees = null;

        boolean flag = false;


        // Pointers to lines for reading
        readData(employees, admins, salaryRecord, employeeSalaryRecord, stores, listOfEmplyees);


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
                        boolean check = true;
                        do {
                            // Asks for admin option input
                            System.out.println("\nWelcome!\n");
                            System.out.println("Please enter one of the following options (1, 2, 3, or 4): ");
                            System.out.println("1: Add a new employee");
                            System.out.println("2: Payroll Calculation.");
                            System.out.println("3. Modify an employee's info.");
                            System.out.println("4: Payroll Records.");
                            System.out.println("5: Get employee list of a store.");
                            System.out.println("6: Add an existing employee to a store.");
                            System.out.println("7: Remove an existing employee from a store.");
                            System.out.println("8: Close the program.");
                            int pick = kb.nextInt();
                            kb.nextLine();

                            // Deals with option 1
                            if (pick == 1) {
                                BioRecords newEmp = new Employee();
                                System.out.println("Enter employee's Name: ");
                                newEmp.setName(kb.nextLine());
                                System.out.println("Enter employee's ID: ");
                                newEmp.setID(kb.nextLine());
                                System.out.println("Enter employee's position: ");
                                newEmp.setPosition(kb.nextLine());
                                System.out.println("Enter employee's wage: ");
                                double wage = kb.nextFloat();

                                try {
                                    newEmp.setWage(wage);
                                } catch (LessThanMinWageException e) {
                                    System.out.println("Entered wage is less than the minimum wage!!!");
                                    System.out.println("Wage set to default(12.65)");
                                    newEmp.setWage(12.65);
                                } finally {

                                    kb.nextLine();
                                    System.out.println("Enter employee's start year: ");
                                    newEmp.setStartYear(kb.nextLine());
                                    System.out.println("Enter the store code: ");
                                    String storeCode = kb.nextLine();
                                    newEmp.setStoreCode(storeCode);
                                    CompanyStore companyStore = new CompanyStore(storeCode, newEmp);
                                    employees.put(newEmp.getID(), newEmp);

                                    if (stores.containsKey(storeCode)) {
                                        ArrayList<Employee> tempEmp = stores.get(storeCode);
                                        tempEmp.add((Employee) newEmp);
                                        stores.put(storeCode, tempEmp);

                                    } else {
                                        ArrayList<Employee> tempEmp = new ArrayList<>();
                                        tempEmp.add((Employee) newEmp);
                                        stores.put(storeCode, tempEmp);
                                    }
                                    System.out.println("\nYou entered the following information: ");
                                    newEmp.getInfo();
                                    newEmp.write();
                                }


                                check = true;


                            }

                            // Deals with option 2
                            else if (pick == 2) {
                                Salary currenSalary;
                                double currentWorkingHours = 0.0;
                                System.out.println("Enter the employee's ID: ");
                                String payId = kb.nextLine();
                                String payPeriod;
                                if (employees.containsKey(payId)) {
                                    System.out.println("Enter the number of hours for the pay period: ");
                                    boolean error = false;
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
                                    currenSalary = new Salary((Employee) employees.get(payId), currentWorkingHours,
                                            payPeriod);
                                    currenSalary.write();
                                    currenSalary.earnings();

                                    salaryRecord.put(payPeriod, currenSalary);
                                    employeeSalaryRecord.put(payId, salaryRecord);

                                } else {
                                    System.out.println("ID does not exist!!! Please try again.");
                                }
                                check = true;
                            }

                            // Deals with option 3
                            else if (pick == 3) {
                                System.out.println("Please enter the ID of the employee: ");
                                String id = kb.nextLine();
                                if (employees.containsKey(id)) {
                                    Employee emp = (Employee) employees.get(id);
                                    System.out.println("Choose one of the following options (1, 2, or 3):");
                                    System.out.println("1. Change the employee's name: ");
                                    System.out.println("2. Change the employee's position: ");
                                    System.out.println("3. Change the employee's wage per hour; ");
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
                                                emp.setWage(12.65);
                                            } finally {
                                                emp.getInfo();
                                                break;
                                            }

                                        }
                                        default: {
                                            System.out.println("\nInvalid entry!!\n");
                                        }
                                    }
                                    emp.write();
                                } else {
                                    System.out.println("ID not Found!!");
                                }
                                check = true;
                            } else if (pick == 4) {

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

                                check = true;

                            }

                            // Deals with option 4
                            else if (pick == 5) {
                                System.out.println("Enter the store code: ");
                                String storeCode = kb.nextLine();
                                if(stores.containsKey(storeCode)){
                                System.out.println("List of employees: ");
                                if (stores.containsKey(storeCode)) {
                                    ArrayList<Employee> emp = stores.get(storeCode);
                                    for (Employee e : emp) {
                                        System.out.println("Name: " + e.getName() + "     ID: " + e.getID());
                                    }
                                }}else {
                                    System.out.println("Store not found!!!");
                                }
                                check = true;
                            } else if (pick == 6) {
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
                                check = true;
                            } else if (pick == 7) {
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
                System.out.println("Enter Authorization Key: ");
                String authKey = kb.nextLine();

                // If authorized then creates a new admin profile
                if (authKey.equals("SuperStore1517")) {
                    Adminsitration newAdmin = new Adminsitration();
                    System.out.println("Choose a User Name: ");
                    newAdmin.setName(kb.nextLine());
                    System.out.println("Choose a password: ");
                    newAdmin.setPassword(kb.nextLine());
                    System.out.println("Enter your ID: ");
                    newAdmin.setID(kb.nextLine());
                    System.out.println("Enter Admin's Position: ");
                    newAdmin.setPosition(kb.nextLine());
                    System.out.println("Enter Admin's wage per hour: ");
                    double wageAdm = kb.nextFloat();
                    try {
                        newAdmin.setWage(wageAdm);
                    } catch (LessThanMinWageException e) {
                        System.out.println("Entered wage is less than the minimum wage!!!");
                        System.out.println("Wage set to default(12.65)");
                        newAdmin.setWage(12.65);
                    }

                    kb.nextLine();
                    System.out.println("Enter Admin's start year: ");
                    newAdmin.setStartYear(kb.nextLine());
                    System.out.println("Enter the store code: ");
                    String storeCode = kb.nextLine();
                    newAdmin.setStoreCode(storeCode);
                    CompanyStore companyStore = new CompanyStore(storeCode);

                    admins.put(newAdmin.getID(), newAdmin.getPassword());

                    newAdmin.getInfo();
                    newAdmin.setEmployee(companyStore);
                    Employee adm = newAdmin.getEmp();
                    if (stores.containsKey(storeCode)) {
                        ArrayList<Employee> tempEmp = stores.get(storeCode);
                        tempEmp.add(adm);
                        stores.put(storeCode, tempEmp);

                    } else {
                        ArrayList<Employee> tempEmp = new ArrayList<>();
                        tempEmp.add(adm);
                        stores.put(storeCode, tempEmp);
                    }
                    employees.put(newAdmin.getID(), adm);
                    newAdmin.write();

                } else {
                    System.out.println("Wrong Authorization Key!!");
                }
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

    private static void readData(Map employees, Map admins, Map salaryRecord, Map employeeSalaryRecord, Map<String, ArrayList<Employee>> stores, ArrayList listOfEmployees) throws
            IOException, LessThanMinWageException {
        List<String> adm = Files.readAllLines(Paths.get("adminInfo.txt"));
        List<String> emps = Files.readAllLines(Paths.get("empInfo.txt"));
        List<String> wageRecords = Files.readAllLines(Paths.get("WageRecord.txt"));


        // Makes an array out of the read line and add key/value to admins
        for (String line : adm) {
            ArrayList<String> partsOfAdm = splitOnComma(line);
            admins.put(EncryptDecrypt.decrypt(partsOfAdm.get(1)), EncryptDecrypt.decrypt(partsOfAdm.get(5)));


        }

        // Makes an array out of the read line and add key/value to employees
        for (String line : emps) {
            ArrayList<String> partsOfEmp = splitOnComma(line);
            Employee employee = new Employee(EncryptDecrypt.decrypt(partsOfEmp.get(0)), EncryptDecrypt.decrypt(partsOfEmp.get(2)),
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
            employees.put(EncryptDecrypt.decrypt(partsOfEmp.get(1)), employee
            );


        }

        // Makes an array out of the read line and add key/value to salaries
        for (String line : wageRecords) {
            ArrayList<String> partsofWageRecords = splitOnComma(line);
            salaryRecord.put(partsofWageRecords.get(3)/*pay period*/,
                    new Salary(EncryptDecrypt.decrypt(partsofWageRecords.get(0)), partsofWageRecords.get(1),
                            partsofWageRecords.get(2), partsofWageRecords.get(3), partsofWageRecords.get(4),
                            partsofWageRecords.get(5), partsofWageRecords.get(6), partsofWageRecords.get(7),
                            partsofWageRecords.get(8)));

            employeeSalaryRecord.put(partsofWageRecords.get(1)/*id*/, salaryRecord);


        }

    }

    // EFFECTS: return an ArrayList out of the given line
    public static ArrayList<String> splitOnComma(String line) {
        String[] splits = line.split(",");
        return new ArrayList<>(Arrays.asList(splits));


    }
}
