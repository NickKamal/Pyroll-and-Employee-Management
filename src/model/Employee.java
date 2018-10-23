package model;

import Exceptions.MinWageException;

import java.io.*;

public class Employee extends Worker {
    public Employee(String s1, String s2, String s3, double d, String s4) throws MinWageException {
        name = s1;
        position = s2;
        id = s3;
        if (d < 12.65){
            throw new MinWageException();
        }
        wagePerHour = d;
        startYear = s4;
    }

    /**
     * Creates an Employee's profile in the system.
     */

    public Employee()
    {}


    public void write() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("empInfo.txt", true));
        writer.println(EncryptDecrypt.encrypt(getName()) + "," + EncryptDecrypt.encrypt(getID()) +
                "," + EncryptDecrypt.encrypt(getPosition()) + "," + getWage()
                + "," + getStartYear() + ",");
        writer.close();
    }

}
