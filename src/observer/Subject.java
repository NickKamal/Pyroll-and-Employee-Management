package observer;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update();
        }

    }

}
