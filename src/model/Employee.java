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
        System.out.println("Name: " + name);
    }

    public void setEmployeePos(String position)
    {
        this.position = position;
        System.out.println("Position: " + position);
    }

    public void setEmployeeId(String id)
    {
        this.id =id;
        System.out.println("ID: " + id);
    }

    public void setEmployeeWage(float wagePerHour)
    {

        this.wagePerHour = wagePerHour;
    }

    public float getEmployeeWage()
    {
        return wagePerHour;
    }
}
