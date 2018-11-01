package model;

import java.util.ArrayList;
import java.util.Objects;

public class CompanyStore {
    private ArrayList<Employee> listOfEmployees = new ArrayList<>();
    private String myStoreCode;

    public void addEmployee(Employee emp){
        if (!listOfEmployees.contains(emp)){
            listOfEmployees.add(emp);
            emp.addStore(this);
        }
    }
    public CompanyStore(String storeCode, BioRecords employee){
        myStoreCode = storeCode;
        addEmployee((Employee) employee);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyStore that = (CompanyStore) o;
        return Objects.equals(myStoreCode, that.myStoreCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(myStoreCode);
    }

    public CompanyStore(String myStoreCode){
        this.myStoreCode = myStoreCode;
    }

    public String getStoreCode(){
        return myStoreCode;
    }

    public void removeEmployee(Employee employee) {
        if (listOfEmployees.contains(employee)){
            listOfEmployees.remove(employee);
            employee.removeStore(this);
        }
    }
}
