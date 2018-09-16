package model;

public class Adminsitration {

    private String userName;
    private String password;
    private String adminId;
    private String amdinPos;
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
        this.amdinPos = adminPos;
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
}
