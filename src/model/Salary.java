package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Salary {

    private final double CPP_REDUCTON_RATE = 0.02;
    private final double EI_REDUCTION_RATE = 0.025;
    private final double OVERTIME_RATE = 1.5;
    private String name;
    private String ID;
    private double wageRate;
    private double totalHours;
    private String payPeriod;
    private double regularHours = 0;
    private double overTimeHours = 0;

    private double totalWage;
    private double netWage;
    private double EI;
    private double CPP_QPP;
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


    public double CPP_QPP(double wage) {
        return (wage * CPP_REDUCTON_RATE);
    }

    public double EI(double wage) {
        return (int)(wage * EI_REDUCTION_RATE * 100) / 100.0;
    }

    public double netWage(double wage) {
        return (int)((wage - CPP_QPP(wage) - EI(wage)) * 100) / 100.0;
    }

    public double totalWage(double hours) {
        if (totalHours <= 80){
        return hours * wageRate;}
        else {
            return overTimeWage + regularWage;
        }
    }

    public double overTimeWage(double overTimeHours) {
        return overTimeHours * 1.5 * wageRate;
    }

    public double regularWage(double regularHours) {
        return regularHours * wageRate;
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
        System.out.println("E.I Reduction: " + EI);
        System.out.println("C.P.P/Q.P.P Reduction: " + CPP_QPP);
        System.out.println("Total reductions: " + EI + CPP_QPP);
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
