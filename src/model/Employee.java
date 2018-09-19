package model;

public class Employee {
    /**
     * Creates an Employee's profile in the system.
     */

    private String name;
    private String position;
    private String id;
    private float wagePerHour;

    public void setEmployeeName(String name)
    {
        this.name = name;
    }

    public void setEmployeePos(String position)
    {
        this.position = position;
    }

    public void setEmployeeId(String id)
    {
        this.id =id;
    }

    public void setEmployeeWage(float wagePerHour)
    {

        this.wagePerHour = wagePerHour;
    }

    public String getEmpName()
    {
        return name;
    }

    public String getEmpId()
    {
        return id;
    }

    public String getEmpPos()
    {
        return position;
    }

    public float getEmployeeWage()
    {
        return wagePerHour;
    }

    public void getEmpInfo()
    {
        System.out.println("Name: " + name);
        System.out.println("Position: " + position);
        System.out.println("ID: " + id);
        System.out.println("Wage per hour: " + wagePerHour);
    }
}
