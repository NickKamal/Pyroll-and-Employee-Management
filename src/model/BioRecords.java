package model;

import Exceptions.MinWageException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface BioRecords {
    public void setName(String name);
    public void setPosition(String position);
    public void setID(String id);
    public void setWage(double wagePerHour) throws MinWageException;
    public String getName();
    public String getID();
    public String getPosition();
    public double getWage();
    public void getInfo();

    public void setStartYear(String nextLine);
    public void write() throws IOException;
}
