package ui;

import Exceptions.MinWageException;
import model.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Payroll {

    public static void main(String[] args) throws IOException, MinWageException {
        // Initialize a scanner object
        Scanner kb = new Scanner(System.in);

        // Creates an employee dictionary
        Map employees;
        employees = new HashMap();

        // Creates an admin dictionary
        Map admins = new HashMap();

        //Creates a employee-salary dictionary
        Map salaries = new HashMap();

        //Creates a payPeriod-Salary dictionary
        Map payPeriodSalaries = new HashMap();

        boolean flag = false;

        // Pointers to lines for reading
        ReadData(employees, admins, payPeriodSalaries, salaries);


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
                            System.out.println("4: Close the program.");
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
                                } catch (MinWageException e) {
                                    System.out.println("Entered wage is less than the minimum wage!!!");
                                    System.out.println("Wage set to default(12.65)");
                                    newEmp.setWage(12.65);
                                }

                                kb.nextLine();
                                System.out.println("Enter employee's start year: ");
                                newEmp.setStartYear(kb.nextLine());
                                employees.put(newEmp.getID(), newEmp);
                                System.out.println("\nYou entered the following information: ");
                                newEmp.getInfo();
                                newEmp.write();


                                check = true;


                            }

                            // Deals with option 2
                            else if (pick == 2) {
                                Salary currenSalary;
                                double currentWorkingHours;
                                System.out.println("Enter the employee's ID: ");
                                String payId = kb.nextLine();
                                String payPeriod;
                                if (employees.containsKey(payId)) {
                                    System.out.println("Enter the number of hours for the pay period: ");
                                    currentWorkingHours = kb.nextDouble();
                                    System.out.println("Enter the pay period: ");
                                    kb.nextLine();
                                    payPeriod = kb.nextLine();
                                    currenSalary = new Salary((Employee) employees.get(payId), currentWorkingHours,
                                            payPeriod);
                                    currenSalary.write();
                                    currenSalary.earnings();

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
                                            } catch (MinWageException e) {
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
                            }

                            // Deals with option 4
                            else if (pick == 4) {
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
                    } catch (MinWageException e) {
                        System.out.println("Entered wage is less than the minimum wage!!!");
                        System.out.println("Wage set to default(12.65)");
                        newAdmin.setWage(12.65);
                    } finally {
                        kb.nextLine();
                        System.out.println("Enter Admin's start date: ");
                        newAdmin.setStartYear(kb.nextLine());
                        admins.put(newAdmin.getID(), newAdmin.getPassword());
                        newAdmin.getInfo();
                        Employee adm = new Employee(newAdmin.getName(), newAdmin.getID(), newAdmin.getPosition(), newAdmin.getWage(), newAdmin.getStartYear());
                        employees.put(newAdmin.getID(), adm);
                        newAdmin.write();
                        adm.write();

                    }

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

    private static void ReadData(Map employees, Map admins, Map payPeriodSalaries, Map salaries) throws IOException, MinWageException {
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
            employees.put(EncryptDecrypt.decrypt(partsOfEmp.get(1)),
                    new Employee(EncryptDecrypt.decrypt(partsOfEmp.get(0)), EncryptDecrypt.decrypt(partsOfEmp.get(2)),
                            EncryptDecrypt.decrypt(partsOfEmp.get(1)), Double.parseDouble(partsOfEmp.get(3)),
                            partsOfEmp.get(4)));

        }

        // Makes an array out of the read line and add key/value to salaries
        for (String line : wageRecords) {
            ArrayList<String> partsofWageRecords = splitOnComma(line);
            Object temp = payPeriodSalaries.put(partsofWageRecords.get(3),
                    new Salary(EncryptDecrypt.decrypt(partsofWageRecords.get(0)), partsofWageRecords.get(1),
                            partsofWageRecords.get(2), partsofWageRecords.get(3), partsofWageRecords.get(4),
                            partsofWageRecords.get(5), partsofWageRecords.get(6), partsofWageRecords.get(7),
                            partsofWageRecords.get(8)));
            salaries.put(partsofWageRecords.get(1), temp);
        }

    }

    // EFFECTS: return an ArrayList out of the given line
    public static ArrayList<String> splitOnComma(String line) {
        String[] splits = line.split(",");
        return new ArrayList<>(Arrays.asList(splits));


    }
}
