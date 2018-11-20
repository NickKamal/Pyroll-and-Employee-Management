package model;

import java.util.ArrayList;
import java.util.Date;

public class Log {
    private static final ArrayList<String> log = new ArrayList<>();


    public static void addToLog(String id){
        log.add(id + "->" + new Date());

    }

    public static void printLog(){
        System.out.println("Employee Record update Log (ID->Date): ");
        for(String s: log){
            System.out.println(s);
        }


    }
}
