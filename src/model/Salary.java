package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Salary {

    private final double CPP_REDUCTON_RATE = 0.02;
    private final double EI_REDUCTION_RATE = 0.025;
    private String name;
    private String ID;
    private double wageRate;
    private double hours;
    private String payPeriod;

    double totalWage;
    double netWage;
    double EI;
    double CPP_QPP;

    public Salary(Employee emp, double hours, String payPeriod){
        this.name = emp.getName();
        this.ID = emp.getID();
        this.wageRate = emp.getWage();
        this.hours = hours;
        this.payPeriod = payPeriod;
        totalWage = totalWage(hours);
        netWage = netWage(totalWage);
        EI = EI(totalWage);
        CPP_QPP = CPP_QPP(totalWage);
    }



    public Salary(String name, String id, String wageRate, String payPeriod,
                  String totalWage, String EI, String CPP_QPP, String netWage, String hours) {
        this.name = name;
        this.ID = id;
        this.wageRate = Double.parseDouble(wageRate);
        this.hours = Double.parseDouble(hours);
        this.payPeriod = payPeriod;
        this.totalWage = Double.parseDouble(totalWage);
        this.EI = Double.parseDouble(EI);
        this.CPP_QPP = Double.parseDouble(CPP_QPP);
        this.netWage = Double.parseDouble(netWage);
        this.hours = Double.parseDouble(hours);

    }


    public double CPP_QPP(double wage)
    {
        return (wage * 0.02 / 100);
    }

    public double EI(double wage){
        return (wage * 0.025 /100);
    }

    public double netWage(double wage){
        return wage - CPP_QPP(wage) - EI(wage);
    }

    public double totalWage(double hours){
        return hours * wageRate;
    }

    public void earnings(){
        System.out.println("Name: " + name);
        System.out.println("ID: " + ID);
        System.out.println("Pay Period: " + payPeriod);
        System.out.println("Total Earnings: " + totalWage);
        System.out.println("E.I Reduction: " + EI);
        System.out.println("C.P.P/Q.P.P Reduction: " + CPP_QPP);
        System.out.println("Net Wage: " + netWage);
    }
    public void write() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("WageRecord.txt", true));
        writer.println(EncryptDecrypt.encrypt(name) + "," + ID +
                "," + wageRate + "," + payPeriod + "," + totalWage
                + "," + EI + "," + CPP_QPP + "," + netWage + "," + hours + ",");
        writer.close();
    }
}
