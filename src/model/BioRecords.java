package model;

import Exceptions.LessThanMinWageException;

import java.io.IOException;

public interface BioRecords {
    public void setName(String name);
    public void setPosition(String position);
    public void setID(String id);
    public void setWage(double wagePerHour) throws LessThanMinWageException;
    public String getName();
    public String getID();
    public String getPosition();
    public double getWage();
    public void getInfo();

    public void setStartYear(String nextLine);
    public void write() throws IOException;

    void setStoreCode(String storeCode);
}
