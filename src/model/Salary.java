package model;

public class Salary {

    private final double CPP_REDUCTON_RATE = 0.02;
    private final double EI_REDUCTION_RATE = 0.025;
    private String name;
    private String ID;
    private double wageRate;
    private double hours;
    public Salary(String name, String ID, double wageRate, double hours){
        this.name = name;
        this.ID = ID;
        this.wageRate = wageRate;
        this.hours = hours;
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
        System.out.println("Total Earnings: " + totalWage(hours));
        System.out.println("E.I Reduction: " + EI(totalWage(hours)));
        System.out.println("C.P.P/Q.P.P Reduction: " + CPP_QPP(totalWage(hours)));
        System.out.println("Net Wage: " + netWage(totalWage(hours)));
    }
}
