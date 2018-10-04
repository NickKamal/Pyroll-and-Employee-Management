package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Employee extends Worker {
    /**
     * Creates an Employee's profile in the system.
     */
    public void write() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("empInfo.txt","UTF-8");
        writer.println(getName() + "," + getID() + "," + getPosition() + "," + getWage() + "," + getStartDate());
        writer.close();
    }

}
