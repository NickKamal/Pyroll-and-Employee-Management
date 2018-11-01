package model;

import Exceptions.LessThanMinWageException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Adminsitration extends Worker{
    /**
     * Creates an Administration profile in the system.
     */


    private String password;
    private Employee employee;



    public void setEmployee(CompanyStore companyStore){
        try {
            employee = new Employee(name, position, id, wagePerHour, startYear, storeCode, companyStore);
            employee.write();
        }  catch (LessThanMinWageException e) {
        } catch (IOException e) {
            System.out.println("Unable to write in the file!!!\nPlease restart the program and try again!!");
        }
    }


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

    public Employee getEmp(){
        return employee;
    }

    //EFFECTS: returns full info
    public void getInfo()
    {
        System.out.println("You entered the following info:");
        System.out.println("Admin's Name: " + name);
        System.out.println("Admin's Id: " + id);
        System.out.println("Admin's Position: " + position);
        System.out.println("Admn's Wage: " + wagePerHour);
        System.out.println("Start Date: " + startYear);

    }

    @Override
    public void write() throws FileNotFoundException, UnsupportedEncodingException {
            PrintWriter writer = new PrintWriter("adminInfo.txt","UTF-8");
            writer.println(EncryptDecrypt.encrypt(getName()) + ","
                    + EncryptDecrypt.encrypt(getID()) + "," + EncryptDecrypt.encrypt(getPosition())
                    + "," + getWage() + "," + getStartYear() + "," + EncryptDecrypt.encrypt(getPassword()) + "," + getStoreCode() + ",");
            writer.close();
        }

    @Override
    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;

    }


}

