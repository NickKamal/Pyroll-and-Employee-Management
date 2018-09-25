package model;

public class Employee {
    /**
     * Creates an Employee's profile in the system.
     */

    private String name;
    private String position;
    private String id;
    private double wagePerHour;

    //MODIFIES: this
    //EFFECTS: set the name of the employee to the given argument
    public void setEmployeeName(String name)
    {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: set the position of the admin. to the given argument
    public void setEmployeePos(String position)
    {
        this.position = position;
    }

    //MODIFIES: this
    //EFFECTS: set the ID of the admin. to the given argument
    public void setEmployeeId(String id)
    {
        this.id =id;
    }

    //REQUIRES: a number
    //MODIFIES: this
    //EFFECTS: set the wage of the admin. to the given argument
    public void setEmployeeWage(double wagePerHour)
    {

        this.wagePerHour = wagePerHour;
    }

    //EFFECTS: returns the name of the employee
    public String getEmpName()
    {
        return name;
    }


    //EFFECTS: returns the ID of the employee
    public String getEmpId()
    {
        return id;
    }

    //EFFECTS: returns the position of the employee
    public String getEmpPos()
    {
        return position;
    }

    //EFFECTS: returns the wage of the employee
    public double getEmployeeWage()
    {
        return wagePerHour;
    }

    //EFFECTS: returns full info of the employee
    public void getEmpInfo()
    {
        System.out.println("Name: " + name);
        System.out.println("Position: " + position);
        System.out.println("ID: " + id);
        System.out.println("Wage per hour: " + wagePerHour);
    }
}
