package model;

public class Adminsitration {
    /**
     * Creates an Administration profile in the system.
     */

    private String userName;
    private String password;
    private String adminId;
    private String adminPos;
    private double adminWage;

    //MODIFIES: this
    //EFFECTS: set the name of the admin. to the given argument
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    //MODIFIES: this
    //EFFECTS: set the password to the given argument
    public void setPassword(String password)
    {
        this.password = password;
    }

    //MODIFIES: this
    //EFFECTS: set the ID of the to the given argument
    public void setAdminId(String adminId)
    {
        this.adminId = adminId;
    }

    //MODIFIES: this
    //EFFECTS: set the admin's position to the given argument
    public void setAdminPos(String adminPos)
    {
        this.adminPos = adminPos;
    }

    //REQUIRES: a number
    //MODIFIES: this
    //EFFECTS: set the wage of the admin. to the given argument
    public void setAdminWage(double adminWage)
    {
        this.adminWage = adminWage;
    }


    //EFFECTS: returns admin's wage
    public double getAdminWage()
    {
        return adminWage;
    }

    //EFFECTS: returns admin's ID
    public String getID()
    {
        return adminId;
    }

    //EFFECTS: returns password
    public String getPassword()
    {
        return password;
    }

    //EFFECTS: returns admin's full info
    public void getinfo()
    {
        System.out.println("You entered the following info:");
        System.out.println("Admin's Name: " + userName);
        System.out.println("Admin's Id: " + adminId);
        System.out.println("Admin's Position: " + adminPos);
        System.out.println("Admn's Wage: " + adminWage);
    }
}
