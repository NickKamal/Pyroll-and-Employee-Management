
package ui;

import Exceptions.LessThanMinWageException;
import model.*;
import observer.Subject;

import java.io.IOException;
import java.util.*;

import static ui.behindTheScenes.CreateNewEmployeeAndAdmin.*;
import static ui.behindTheScenes.ReadDataFromSource.*;
import static ui.behindTheScenes.SalaryCalculation.calculateSalary;
import static ui.behindTheScenes.StoreEmployeeRelation.*;
import static ui.behindTheScenes.ViewOrModifyEmployeeRecords.*;

class UserInteraction {

    public static void main(String[] args) throws IOException, LessThanMinWageException {
        readWebPage();
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
        boolean flag = true;

        // Pointers to lines for reading
        readData(employees, admins, salaryRecord, employeeSalaryRecord, stores);


        do {

            // Asks for user input
            System.out.println("\nPlease enter one of the following options (1, 2, or 3): ");
            System.out.println("1: Administration login.");
            System.out.println("2: Create an administration account.");
            System.out.println("3: Employee login.");
            System.out.println("4: Close the program.");
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
                System.out.println("Enter your id: ");
                String id = kb.nextLine();
                if (employees.containsKey(id)) {
                    Employee emp = (Employee) employees.get(id);
                    emp.getInfo();
                } else {
                    System.out.println("ID not found!!!");
                }
                flag = true;
            }
            // Execution if user chooses option 4
            else if (option == 4) {
                flag = false;
            }

            // Deals with invalid option
            else {
                System.out.println("Invalid Option!");
                flag = true;
            }
        } while (flag);
        Log.printLog();
    }
}
