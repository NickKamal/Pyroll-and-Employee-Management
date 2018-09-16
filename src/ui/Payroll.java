package ui;
import model.Adminsitration;
import model.Employee;
import java.util.*;

public class Payroll {

    public static void main(String[] args) {
        // Initialize a scanner object
        Scanner kb = new Scanner(System.in);

        // Creates an employee list
        ArrayList<Employee> employees;
        employees = new ArrayList<>();

        // Creates an admin dictionary
        Map admins = new HashMap();

        boolean flag = false;

        do {

            // Asks for user input
            System.out.println("Please enter one of the following option (1, 2, or 3): ");
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
                            System.out.println("Welcome!\n");
                            System.out.println("Please enter one of the following option (1, 2, or 3): ");
                            System.out.println("1: Add a new employee");
                            System.out.println("2: Payroll Calculation.");
                            System.out.println("3: Close the program.");
                            int pick = kb.nextInt();
                            kb.nextLine();

                            // Deals with option 1
                            if (pick == 1) {
                                check = true;
                            }

                            // Deals with option 2
                            else if (pick == 2) {

                                check = true;
                            }

                            // Deals with option 3
                            else if (pick == 3) {
                                check = false;
                            }

                            // Deals with invalid input
                            else {
                                System.out.println("Invalid input!!!\n");
                                check = true;

                            }
                        }while(check == true);
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
                    newAdmin.setUserName(kb.nextLine());
                    System.out.println("Choose a password: ");
                    newAdmin.setPassword(kb.nextLine());
                    System.out.println("Enter your ID: ");
                    newAdmin.setAdminId(kb.nextLine());
                    System.out.println("Enter Admin's Position: ");
                    newAdmin.setAdminPos(kb.nextLine());
                    System.out.println("Enter Admin's wage per hour: ");
                    newAdmin.setAdminWage(kb.nextFloat());
                    kb.nextLine();
                    admins.put(newAdmin.getID(), newAdmin.getPassword());
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

        }while(flag == true);


    }


}
