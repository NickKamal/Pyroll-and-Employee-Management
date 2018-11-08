package observer;

public class FieldObserver extends Observer {

    FieldObserver(Subject subject){
        this.subject = subject;
        this.subject.addObserver(this);
    }

    public void update(){
        System.out.println("Administrator modified your record!!");
    }
}
