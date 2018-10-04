package ui;
import model.*;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.*;


public class Payroll{

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        // Initialize a scanner object
        Scanner kb = new Scanner(System.in);

        // Creates an employee dictionary
        Map employees;
        employees = new HashMap();

        // Creates an admin dictionary
        Map admins = new HashMap();

        boolean flag = false;


        do {

            // Asks for user input
            System.out.println("\nPlease enter one of the following options (1, 2, or 3): ");
            System.out.println("1: Administration login.");
            System.out.println("2: Create an administration account.");
            System.out.println("3: Close the program.");
            int option = kb.nextInt();
            kb.nextLine();

            // Execution if user enters option 1
            if (option == 1)
            {
                System.out.println("Enter Admin's ID: ");
                String loginID = kb.nextLine();

                // Checks if the admin is in the list
                if (admins.containsKey(loginID))
                {
                    System.out.println("Enter password: ");
                    String loginPassword = kb.nextLine();

                    // Password authentication
                    if (admins.get(loginID).equals(loginPassword))
                    {
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
                                newEmp.setWage(kb.nextFloat());
                                kb.nextLine();
                                System.out.println("Enter employee's start date: ");
                                newEmp.setStartDate(kb.nextLine());
                                employees.put(newEmp.getID(), newEmp);
                                System.out.println("\nYou entered the following information: ");
                                newEmp.getInfo();
                                newEmp.write();


                                check = true;
                            }

                            // Deals with option 2
                            else if (pick == 2) {
                                Salary.payroll();
                                check = true;
                            }

                            // Deals with option 3
                            else if (pick == 3) {
                                System.out.println("Please enter the ID of the employee: ");
                                String id = kb.nextLine();
                                Employee emp = (Employee) employees.get(id);
                                System.out.println("Choose one of the following options (1, 2, or 3):");
                                System.out.println("1. Change the employee's name: ");
                                System.out.println("2. Change the employee's position: ");
                                System.out.println("3. Change the employee's wage per hour; ");
                                int input = kb.nextInt();
                                kb.nextLine();
                                switch(input)
                                {
                                    case 1:
                                    {
                                        System.out.println("Please enter the new name: ");
                                        emp.setName(kb.nextLine());
                                        emp.getInfo();
                                        break;

                                    }
                                    case 2:
                                    {
                                        System.out.println("Please enter the new position: ");
                                        emp.setPosition(kb.nextLine());
                                        emp.getInfo();
                                        break;
                                    }
                                    case 3:
                                    {
                                        System.out.println("Please enter the new wage per hour: ");
                                        emp.setWage(kb.nextFloat());
                                        emp.getInfo();
                                        break;
                                    }
                                    default:
                                    {
                                        System.out.println("\nInvalid entry!!\n");
                                    }
                                }
                                check = true;
                            }

                            // Deals with option 4
                            else if (pick == 4)
                            {
                                check = false;
                            }

                            // Deals with invalid input
                            else {
                                System.out.println("Invalid input!!!\n");
                                check = true;

                            }
                        }while(check);
                    }

                    // Deals with wrong password
                    else
                    {
                        System.out.println("Wrong Password!!\n");
                    }
                }

                // Deals with admin not in the list
                else
                {
                    System.out.println("Admin not found!!\n");
                    flag = true;
                }
            }


            // Execution if user enters option.
            else if (option == 2)
            {
                System.out.println("Enter Authorization Key: ");
                String authKey = kb.nextLine();

                // If authorized then creates a new admin profile
                if (authKey.equals("SuperStore1517"))
                {
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
                    newAdmin.setWage(kb.nextFloat());
                    kb.nextLine();
                    System.out.println("Enter Admin's start date: ");
                    newAdmin.setStartDate(kb.nextLine());
                    admins.put(newAdmin.getID(), newAdmin.getPassword());
                    newAdmin.getInfo();
                    newAdmin.write();
                }
                else
                {
                    System.out.println("Wrong Authorization Key!!");
                }
                flag = true;

            }

            // Execution if user chooses option 3
            else if (option == 3)
            {
                flag = false;
            }

            // Deals with invalid option
            else
            {
                System.out.println("Invalid Option!");
                flag = true;
            }



        }while(flag);


    }


}
