package model;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Salary {

    private final String name;
    private final String ID;
    private final double wageRate;
    private double totalHours;
    private final String payPeriod;
    private double regularHours = 0;
    private double overTimeHours = 0;

    private final double totalWage;
    private final double netWage;
    private final double EI;
    private final double CPP_QPP;
    private double overTimeWage;
    private double regularWage;

    public Salary(Employee emp, double totalHours, String payPeriod) {
        this.name = emp.getName();
        this.ID = emp.getID();
        this.wageRate = emp.getWage();
        this.totalHours = totalHours;
        if (totalHours > 80) {
            regularHours = 80;
            overTimeHours = totalHours - regularHours;
            regularWage = regularWage(regularHours);
            overTimeWage = overTimeWage(overTimeHours);
        }
        this.payPeriod = payPeriod;
        totalWage = totalWage(totalHours);
        netWage = netWage(totalWage);
        EI = EI(totalWage);
        CPP_QPP = CPP_QPP(totalWage);
    }


    public Salary(String name, String id, String wageRate, String payPeriod,
                  String totalWage, String EI, String CPP_QPP, String netWage, String totalHours) {
        this.name = name;
        this.ID = id;
        this.wageRate = Double.parseDouble(wageRate);
        this.totalHours = Double.parseDouble(totalHours);
        this.payPeriod = payPeriod;
        this.totalWage = Double.parseDouble(totalWage);
        this.EI = Double.parseDouble(EI);
        this.CPP_QPP = Double.parseDouble(CPP_QPP);
        this.netWage = Double.parseDouble(netWage);
        this.totalHours = Double.parseDouble(totalHours);

    }


    private double CPP_QPP(double wage) {
        double CPP_REDUCTION_RATE = 0.02;
        return (wage * CPP_REDUCTION_RATE);
    }

    private double EI(double wage) {
        double EI_REDUCTION_RATE = 0.025;
        return (int) (wage * EI_REDUCTION_RATE * 100) / 100.0;
    }

    private double netWage(double wage) {
        return (int) ((wage - CPP_QPP(wage) - EI(wage)) * 100) / 100.0;
    }

    private double totalWage(double hours) {
        if (totalHours <= 80) {
            return hours * wageRate;
        } else {
            return overTimeWage + regularWage;
        }
    }

    private double overTimeWage(double overTimeHours) {
        double OVERTIME_RATE = 1.5;
        return overTimeHours * OVERTIME_RATE * wageRate;
    }

    private double regularWage(double regularHours) {
        return regularHours * wageRate;
    }

    public void earnings(JFrame salaryFrame) {
        String[] columnNames = {"",
                ""};
        Object[][] data;
        if (overTimeHours > 0) {
            data = new Object[][]{
                    {"Name: ", name},
                    {"ID: ", ID},
                    {"Pay Period: ", payPeriod},
                    {"Total hours: ", totalHours},
                    {"Regular hours: ", regularHours},
                    {"Overtime hours: ", overTimeHours},
                    {"Overtime pay: ", overTimeWage},
                    {"Regular pay: ", regularWage},
                    {"Total Earnings: ", totalWage},
                    {"E.I Reduction: ", (int) (EI * 100) / 100.0},
                    {"C.P.P/Q.P.P Reduction: ", (int) (CPP_QPP * 100) / 100.0},
                    {"Total deductions: ", (int) ((EI + CPP_QPP) * 100) / 100.0},
                    {"Net Wage: ", netWage}
            };
        } else {
            data = new Object[][]{
                    {"Name: ", name},
                    {"ID: ", ID},
                    {"Pay Period: ", payPeriod},
                    {"Total hours: ", totalHours},
                    {"Total Earnings: ", totalWage},
                    {"E.I Reduction: ", (int) (EI * 100) / 100.0},
                    {"C.P.P/Q.P.P Reduction: ", (int) (CPP_QPP * 100) / 100.0},
                    {"Total deductions: ", (int) ((EI + CPP_QPP) * 100) / 100.0},
                    {"Net Wage: ", netWage}
            };
        }


        JTable table = new JTable(data, columnNames);
        table.setFont( table.getFont().deriveFont(Font.BOLD) );
        table.setRowHeight(25);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        salaryFrame.add(table);
        salaryFrame.setVisible(true);

    }

    public void earnings() {

        System.out.println("Name: " + name);

        System.out.println("ID: " + ID);

        System.out.println("Pay Period: " + payPeriod);

        if (overTimeHours > 0) {

            System.out.println("Regular hours: " + regularHours);

            System.out.println("Overtime hours: " + overTimeHours);

            System.out.println("Overtime pay: " + overTimeWage);

            System.out.println("Regular pay: " + regularWage);

        } else {

            System.out.println("Total hours: " + totalHours);

        }

        System.out.println("Total Earnings: " + totalWage);

        System.out.println("E.I Reduction: " + (int) (EI * 100) / 100.0);

        System.out.println("C.P.P/Q.P.P Reduction: " + (int) (CPP_QPP * 100) / 100.0);

        System.out.println("Total reductions: " + (int) ((EI + CPP_QPP) * 100) / 100.0);

        System.out.println("Net Wage: " + netWage);


    }

    public void write() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("WageRecord.txt", true));
        writer.println(EncryptDecrypt.encrypt(name) + "," + ID +
                "," + wageRate + "," + payPeriod + "," + totalWage
                + "," + EI + "," + CPP_QPP + "," + netWage + "," + totalHours + ",");
        writer.close();
    }
}
