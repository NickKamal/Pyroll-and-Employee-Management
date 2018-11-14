package model;

import Exceptions.LessThanMinWageException;
import observer.Observer;

public abstract class Worker extends Observer {

    String name;
    String position;
    String id;
    double wagePerHour;
    String startYear;


    String storeCode;

    //EFFECTS: return the store code associated with the employee
    String getStoreCode() {
        return storeCode;
    }

    //MODIFIES: this
    //EFFECTS: sets the storeCode to the given argument
    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

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
    public void setWage(double wagePerHour) throws LessThanMinWageException {
        double MINWAGE = 12.65;
        if (wagePerHour < MINWAGE){
            throw new LessThanMinWageException();
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
    String getStartYear(){
        return startYear;
    }


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
