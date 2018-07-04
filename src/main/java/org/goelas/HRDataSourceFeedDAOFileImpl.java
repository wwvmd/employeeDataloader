package org.goelas;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.goelas.dto.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ash_g on 26/05/2017.
 */
@Component
public class HRDataSourceFeedDAOFileImpl implements HRDataSourceFeedDAO {

    @Value("${hr.source.fileName}")
    private String fileName;


    @Override
    public List<Employee> loadAllEmployees() throws IOException {

        List<String> csvEmployees  = readFileLines();

        List<Employee> employees = new ArrayList<>();

        for (String csvEmployee : csvEmployees) {

            employees.add(mapRow(csvEmployee));



        }





        return employees;
    }

    public Employee mapRow(String fileLine) {

        //csv should be name,gpn,rank,division,role
        String[] tokens = StringUtils.split(fileLine,",");

        Employee employee = new Employee(tokens[1],tokens[0],tokens[2],tokens[3],tokens[4]);
        return employee;

    }




    public List<String> readFileLines() throws IOException {

        return FileUtils.readLines(new File(fileName),"UTF-8");




    }
}
