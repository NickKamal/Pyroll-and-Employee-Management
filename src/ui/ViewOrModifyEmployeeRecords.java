package ui;

import Exceptions.LessThanMinWageException;
import model.Employee;
import model.Salary;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import static ui.CreateNewEmployeeAndAdmin.tryThrowAndHandleMinWageException;

class ViewOrModifyEmployeeRecords {

    //EFFECTS: prints the sa;ary records of the given employee
    public static void showMeThePayRecords(Scanner kb, Map employeeSalaryRecord, Map salaryRecord) {
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
    public static void viewOrModifyEmployeeInfo(Scanner kb, Map employees)
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
                    tryThrowAndHandleMinWageException(emp, kb.nextFloat());
                    emp.getInfo();
                    break;
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
}
