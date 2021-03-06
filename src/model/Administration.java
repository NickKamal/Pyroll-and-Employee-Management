package model;

import Exceptions.LessThanMinWageException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Administration extends Worker {
    /**
     * Creates an Administration profile in the system.
     */


    private String password;
    private Employee employee;



    public void setEmployee(CompanyStore companyStore){
        try {
            employee = new Employee(name, position, id, wagePerHour, startYear, storeCode, companyStore);
            employee.write();
        }  catch (LessThanMinWageException ignored) {
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


    public void write() throws IOException {
            PrintWriter writer = new PrintWriter(new FileWriter("adminInfo.txt", true));
            writer.println(EncryptDecrypt.encrypt(getName()) + ","
                    + EncryptDecrypt.encrypt(getID()) + "," + EncryptDecrypt.encrypt(getPosition())
                    + "," + getWage() + "," + getStartYear() + "," + EncryptDecrypt.encrypt(getPassword()) + "," + getStoreCode() + ",");
            writer.close();
        }


    @Override
    public void update() {

    }
}

