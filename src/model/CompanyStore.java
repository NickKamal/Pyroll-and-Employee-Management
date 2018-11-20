package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public class CompanyStore {
    private final Collection<Employee> listOfEmployees = new ArrayList<>();
    private final String myStoreCode;

    public void addEmployee(Employee emp) {
        for (Employee e : listOfEmployees) {
            if (!e.getStorList().contains(emp.getStorList())) {

                emp.addStore(this);
            } else {
                listOfEmployees.remove(emp);
                listOfEmployees.add(emp);

            }
        }
//        if (!listOfEmployees.contains(emp)){
//            listOfEmployees.add(emp);
//        }

    }

    public CompanyStore(String storeCode, Employee employee) {
        myStoreCode = storeCode;
        addEmployee(employee);
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

    public CompanyStore(String myStoreCode) {
        this.myStoreCode = myStoreCode;
    }

    public String getStoreCode() {
        return myStoreCode;
    }

    public void removeEmployee(Employee employee) {
        if (listOfEmployees.contains(employee)) {
            listOfEmployees.remove(employee);
            employee.removeStore(this);
        }
    }
}
