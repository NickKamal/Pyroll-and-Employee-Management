package model;

import java.io.*;

public class Employee extends Worker {
    public Employee(String s1, String s2, String s3, double d, String s4) {
        name = s1;
        position = s2;
        id = s3;
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
