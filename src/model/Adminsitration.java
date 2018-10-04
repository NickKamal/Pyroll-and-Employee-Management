package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Adminsitration extends Worker{
    /**
     * Creates an Administration profile in the system.
     */


    private String password;

    //MODIFIES: this
    //EFFECTS: set the password to the given argument
    public void setPassword(String password)
    {
        this.password = password;
    }

    //EFFECTS: returns password
    public String getPassword()
    {
        return password;
    }

    //EFFECTS: returns full info
    public void getInfo()
    {
        System.out.println("You entered the following info:");
        System.out.println("Admin's Name: " + name);
        System.out.println("Admin's Id: " + id);
        System.out.println("Admin's Position: " + position);
        System.out.println("Admn's Wage: " + wagePerHour);
        System.out.println("Start Date: " + startDate);
    }

    @Override
    public void write() throws FileNotFoundException, UnsupportedEncodingException {
            PrintWriter writer = new PrintWriter("adminInfo.txt","UTF-8");
            writer.println(getName() + "," + getID() + "," + getPosition() + "," + getWage() + "," + getStartDate() + "," + getPassword());
            writer.close();
        }

    }



