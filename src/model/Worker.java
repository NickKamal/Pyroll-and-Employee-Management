package model;

import Exceptions.MinWageException;

public abstract class Worker implements BioRecords, WageRecords {

    protected String name;
    protected String position;
    protected String id;
    protected double wagePerHour;
    protected String startYear;
    private double minWage = 12.65;


    //MODIFIES: this
    //EFFECTS: sets the name to the given argument
    public void setName(String name)
    {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: set the position to the given argument
    public void setPosition(String position)
    {
        this.position = position;
    }

    //MODIFIES: this
    //EFFECTS: set the ID to the given argument
    public void setID(String id)
    {
        this.id =id;
    }

    //REQUIRES: a number
    //MODIFIES: this
    //EFFECTS: set the wage to the given argument
    public void setWage(double wagePerHour) throws MinWageException {
        if (wagePerHour < minWage){
            throw new MinWageException();
        }
        this.wagePerHour = wagePerHour;
    }

    //EFFECTS: returns the name
    public String getName()
    {
        return name;
    }


    //EFFECTS: returns the ID
    public String getID()
    {
        return id;
    }

    //EFFECTS: returns the position
    public String getPosition()
    {
        return position;
    }

    //EFFECTS: returns the wage
    public double getWage()
    {
        return wagePerHour;
    }

    public void setStartYear(String date){
        this.startYear = date;
    }
    public String getStartYear(){
        return startYear;
    }
    public void wageRecord(){}
    public void getCurrentPeriodWage(){}


    //EFFECTS: returns full
    public void getInfo()
    {
        System.out.println("Name: " + name);
        System.out.println("Position: " + position);
        System.out.println("ID: " + id);
        System.out.println("Wage per hour: " + wagePerHour);
        System.out.println("Start Date: " + startYear);
    }

}
