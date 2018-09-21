package model;

public class Adminsitration {
    /**
     * Creates an Administration profile in the system.
     */

    private String userName;
    private String password;
    private String adminId;
    private String adminPos;
    private float adminWage;

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setAdminId(String adminId)
    {
        this.adminId = adminId;
    }

    public void setAdminPos(String adminPos)
    {
        this.adminPos = adminPos;
    }

    public void setAdminWage(float adminWage)
    {
        this.adminWage = adminWage;
    }

    public float getAdminWage()
    {
        return adminWage;
    }
    public String getID()
    {
        return adminId;
    }

    public String getPassword()
    {
        return password;
    }
    public void getinfo()
    {
        System.out.println("You entered the following info:");
        System.out.println("Admin's Name: " + userName);
        System.out.println("Admin's Id: " + adminId);
        System.out.println("Admin's Position: " + adminPos);
        System.out.println("Admn's Wage: " + adminWage);
    }
}
