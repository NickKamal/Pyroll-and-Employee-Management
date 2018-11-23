
package ui.GUI;

import Exceptions.LessThanMinWageException;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.*;


import static ui.GUI.CreateNewEmployeeAndAdmin.createAnEmployee;
import static ui.GUI.CreateNewEmployeeAndAdmin.makeNewAdmin;
import static ui.GUI.ReadDataFromSource.readData;
import static ui.GUI.SalaryCalculation.calculateSalary;
import static ui.GUI.StoreEmployeeRelation.addEmployeeToStore;
import static ui.GUI.StoreEmployeeRelation.giveTheStoreEmployeeList;
import static ui.GUI.StoreEmployeeRelation.removeEmployeeFromStore;
import static ui.GUI.ViewOrModifyEmployeeRecords.showMeThePayRecords;
import static ui.GUI.ViewOrModifyEmployeeRecords.viewOrModifyEmployeeInfo;

public class UI extends JFrame {
    static boolean flag = false;


    public static void main(String[] args) throws IOException, LessThanMinWageException {


        //readWebPage();
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
        Map<String, ArrayList<Worker>> stores = new HashMap();


        // Pointers to lines for reading

        try {
            readData(employees, admins, salaryRecord, employeeSalaryRecord, stores);
        } catch (Exception e) {
        }

        //run GUI
        runGUI(kb, employees, admins, employeeSalaryRecord, salaryRecord, stores);


    }

    private static void runGUI(Scanner kb, Map employees, Map admins, Map employeeSalaryRecord, Map salaryRecord, Map<String, ArrayList<Worker>> stores) {
        JFrame mainFrame = new JFrame("Employee Payroll and Record Management");
        mainFrame.setSize(450, 200);
        mainFrame.setLayout(new FlowLayout());
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
        mainFrame.add(controlPanel);
        // Asks for user input
        JLabel mainMenu = new JLabel("\nPlease choose one of the following options (1, 2, 3, or 4): ", JLabel.CENTER);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel.add(mainMenu);
        JButton option1 = new JButton("1: Administration login.");
        controlPanel.add(option1, JButton.LEFT_ALIGNMENT);
        JButton option2 = new JButton("2: Create an administration account.");
        controlPanel.add(option2, JButton.LEFT_ALIGNMENT);
        JButton option3 = new JButton("3: Employee login.");
        controlPanel.add(option3, JButton.LEFT_ALIGNMENT);
        JButton option4 = new JButton("4: Close the program.");
        controlPanel.add(option4, JButton.LEFT_ALIGNMENT);
        mainFrame.setVisible(true);
        //Option 1
        option1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                option1(mainFrame, admins, kb, employees, stores, employeeSalaryRecord, salaryRecord);
            }
        });
        option2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    makeNewAdmin(kb, employees, admins, stores);
                } catch (Exception ignored) {
                }
                runGUI(kb, employees, admins, employeeSalaryRecord, salaryRecord, stores);

            }
        });
        option3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Enter your id: ");
                String id = kb.nextLine();
                if (employees.containsKey(id)) {
                    Employee emp = (Employee) employees.get(id);
                    emp.getInfo();
                } else {
                    System.out.println("ID not found!!!");

                }
                runGUI(kb, employees, admins, employeeSalaryRecord, salaryRecord, stores);
            }
        });
        option4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

    private static void option1(JFrame mainFrame, Map admins, Scanner kb, Map employees, Map<String, ArrayList<Worker>> stores, Map employeeSalaryRecord, Map salaryRecord) {
        String loginID;
        JFrame adminLoginFrame = new JFrame("Admin Login");
        adminLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminLoginFrame.setSize(370, 160);
        SpringLayout springLayout = new SpringLayout();
        adminLoginFrame.setLayout(springLayout);
        JLabel idLabel = new JLabel("Admin Id:");
        adminLoginFrame.add(idLabel);
        //Creates text box for admin id
        JTextField adminId = new JTextField(24);
        String loginId = adminId.getSelectedText();
        //Positions admin id label and text field
        springLayout.putConstraint(SpringLayout.WEST, idLabel, 15, SpringLayout.WEST, adminLoginFrame);
        springLayout.putConstraint(SpringLayout.NORTH, idLabel, 20, SpringLayout.NORTH, adminLoginFrame);
        springLayout.putConstraint(SpringLayout.WEST, adminId, 70, SpringLayout.WEST, idLabel);
        springLayout.putConstraint(SpringLayout.NORTH, adminId, 20, SpringLayout.NORTH, adminLoginFrame);
        adminLoginFrame.add(adminId);
        JLabel passwordLabel = new JLabel("Password:");
        adminLoginFrame.add(passwordLabel);

        //Creates password text fiels
        JTextField password = new JPasswordField(24);
        springLayout.putConstraint(SpringLayout.WEST, passwordLabel, 15, SpringLayout.WEST, adminLoginFrame);
        springLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 30, SpringLayout.NORTH, idLabel);
        springLayout.putConstraint(SpringLayout.WEST, password, 70, SpringLayout.WEST, passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, password, 30, SpringLayout.NORTH, adminId);
        adminLoginFrame.add(password);
        JButton login = new JButton("login");
        adminLoginFrame.add(login);
        springLayout.putConstraint(SpringLayout.NORTH, login, 80, SpringLayout.NORTH, adminLoginFrame);
        springLayout.putConstraint(SpringLayout.WEST, login, 85, SpringLayout.WEST, adminLoginFrame);
        mainFrame.setVisible(false);
        adminLoginFrame.setVisible(true);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loginID = adminId.getText();
                char[] passArray = ((JPasswordField) password).getPassword();
                String loginPassword = new String(passArray);
                if (!admins.containsKey(loginID)) {
                    JOptionPane.showMessageDialog(adminLoginFrame, "Admin not found!!");
                    adminLoginFrame.setVisible(false);
                    runGUI(kb, employees, admins, employeeSalaryRecord, salaryRecord, stores);

                } else if (admins.get(loginID).equals(loginPassword)) {
                    adminLoginFrame.setVisible(false);
                    option1sub(kb, employees, stores, employeeSalaryRecord, salaryRecord);
                } else {
                    JOptionPane.showMessageDialog(adminLoginFrame, "Invalid Password!!");
                    adminLoginFrame.setVisible(false);
                    runGUI(kb, employees, admins, employeeSalaryRecord, salaryRecord, stores);
                }

            }
        });
    }


    public static void option1sub(Scanner kb, Map employees, Map<String, ArrayList<Worker>> stores, Map employeeSalaryRecord, Map salaryRecord) {
        JFrame adminFrame = new JFrame("\nWelcome!\n");
        adminFrame.setLayout(new FlowLayout());
        adminFrame.setSize(400, 380);
        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new BoxLayout(adminPanel, BoxLayout.PAGE_AXIS));
        adminFrame.add(adminPanel, CENTER_ALIGNMENT);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel menu = new JLabel("Please choose one of the following options (1, 2, 3, or 4): ");
        adminPanel.add(menu, TOP_ALIGNMENT);
        JButton op1 = new JButton("1: Add a new employee");
        adminPanel.add(op1);
        JButton op2 = new JButton("2: Payroll Calculation.");
        adminPanel.add(op2);
        JButton op3 = new JButton("3. View or Modify an employee's info.");
        adminPanel.add(op3);
        JButton op4 = new JButton("4: Payroll Records.");
        adminPanel.add(op4);
        JButton op5 = new JButton("5: Get employee list of a store.");
        adminPanel.add(op5);
        JButton op6 = new JButton("6: Add an existing employee to a store.");
        adminPanel.add(op6);
        JButton op7 = new JButton("7: Remove an existing employee from a store.");
        adminPanel.add(op7);
        JButton op8 = new JButton("8: Close the program.");
        adminPanel.add(op8);
        adminFrame.setVisible(true);

        op1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    adminFrame.setVisible(false);
                    createAnEmployee(kb, employees, stores, employeeSalaryRecord, salaryRecord, adminFrame);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(adminFrame, "Error!!");
                    actionPerformed(e);
                }

            }
        });
        op2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    adminFrame.setVisible(false);
                    calculateSalary(kb, employees, employeeSalaryRecord, salaryRecord, stores);
                } catch (Exception e1) {

                    JOptionPane.showMessageDialog(adminFrame, "Error!!");
                    actionPerformed(e);
                }
            }
        });
        op3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    adminFrame.setVisible(false);
                    viewOrModifyEmployeeInfo(kb, employees, stores, employeeSalaryRecord, salaryRecord);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(adminFrame, "Error!!");
                    actionPerformed(e);
                }
            }
        });
        op4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminFrame.setVisible(false);

                try {
                    showMeThePayRecords(kb, employeeSalaryRecord, salaryRecord, employees, stores);
                } catch (Exception e1) {
                    actionPerformed(e);
                }

            }

        });
        op5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminFrame.setVisible(false);
                giveTheStoreEmployeeList(kb, employeeSalaryRecord, salaryRecord, employees, stores);
            }
        });
        op6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    adminFrame.setVisible(false);
                    addEmployeeToStore(kb, employees, stores, employeeSalaryRecord, salaryRecord);
                } catch (Exception e1) {

                    JOptionPane.showMessageDialog(adminFrame, "Error!!");
                    actionPerformed(e);
                }
            }
        });
        op7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    adminFrame.setVisible(false);
                    removeEmployeeFromStore(kb, employees, stores, employeeSalaryRecord, salaryRecord);
                } catch (Exception e1) {

                    JOptionPane.showMessageDialog(adminFrame, "Error!!");
                    actionPerformed(e);
                }
            }
        });
        op8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


    }


}
