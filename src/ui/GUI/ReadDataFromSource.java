package ui.GUI;

import Exceptions.LessThanMinWageException;
import model.CompanyStore;
import model.Employee;
import model.EncryptDecrypt;
import model.Salary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ReadDataFromSource {

    public static void readData(Map employees, Map admins, Map salaryRecord, Map employeeSalaryRecord,
                                Map<String, ArrayList<Employee>> stores) throws
            IOException, LessThanMinWageException {
        List<String> adm = Files.readAllLines(Paths.get("adminInfo.txt"));
        List<String> emp = Files.readAllLines(Paths.get("empInfo.txt"));
        List<String> wageRecords = Files.readAllLines(Paths.get("WageRecord.txt"));

        // Makes an array out of the read line and add key/value to admins
        for (String line : adm) {
            ArrayList<String> partsOfAdm = splitOnComma(line);
            admins.put(EncryptDecrypt.decrypt(partsOfAdm.get(1)), EncryptDecrypt.decrypt(partsOfAdm.get(5)));
        }

        // Makes an array out of the read line and add key/value to employees
        for (String line : emp) {
            ArrayList<String> partsOfEmp = splitOnComma(line);
            Employee employee = new Employee(EncryptDecrypt.decrypt(partsOfEmp.get(0)),
                    EncryptDecrypt.decrypt(partsOfEmp.get(2)),
                    EncryptDecrypt.decrypt(partsOfEmp.get(1)), Double.parseDouble(partsOfEmp.get(3)),
                    partsOfEmp.get(4), partsOfEmp.get(5), new CompanyStore(partsOfEmp.get(5)));
            for (int i = 5; i < partsOfEmp.size(); i++) {
                if (partsOfEmp.get(i) != null) {
                    if (stores.containsKey(partsOfEmp.get(i))) {
                        ArrayList<Employee> empList = stores.get(partsOfEmp.get(i));
                        empList.add(employee);
                        stores.put(partsOfEmp.get(i), empList);
                    } else {
                        ArrayList<Employee> tempList = new ArrayList<>();
                        tempList.add(employee);
                        employee.addStore(new CompanyStore(partsOfEmp.get(i)));
                        stores.put(partsOfEmp.get(i), tempList);
                    }

                }
            }
            employees.put(EncryptDecrypt.decrypt(partsOfEmp.get(1)), employee);
        }

        // Makes an array out of the read line and add key/value to salaries
        for (String line : wageRecords) {
            ArrayList<String> userData = splitOnComma(line);
            salaryRecord.put(userData.get(3)/*pay period*/,
                    new Salary(EncryptDecrypt.decrypt(userData.get(0)), userData.get(1),
                            userData.get(2), userData.get(3), userData.get(4),
                            userData.get(5), userData.get(6), userData.get(7),
                            userData.get(8)));

            employeeSalaryRecord.put(userData.get(1)/*id*/, salaryRecord);


        }

    }

    // EFFECTS: return an ArrayList out of the given line
    private static ArrayList<String> splitOnComma(String line) {
        String[] splits = line.split(",");
        return new ArrayList<>(Arrays.asList(splits));

    }

    public static void readWebPage() throws IOException {
        BufferedReader br = null;

        try {
            String theURL = "https://www.ugrad.cs.ubc.ca/~cs210/2018w1/welcomemsg.html"; //this can point to any URL
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            System.out.println(sb);
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }
}
