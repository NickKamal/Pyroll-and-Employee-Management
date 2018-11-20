package observer;

import java.util.*;
public class Subject {


    protected static final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    protected static void notifyObserver() {
        for (Observer observer : observers) {

            observer.update();
        }

    }


}
