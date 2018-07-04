package org.goelas;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.goelas.dto.Employee;
import org.goelas.util.DTOHelper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value="classpath:application.properties")
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        System.exit(1);

    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {


            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        Employee employee = new Employee("Ash Goel","123456","ED", "IB", "eCoommerce" );
        DTOHelper<Employee> employeeDTOHelper = new DTOHelper<>(Employee.class);

        String json = employeeDTOHelper.toJson(employee);

        Employee employee1 = employeeDTOHelper.toObject(json);
        System.out.println(employee1);
        System.out.println(employee.equals(employee1));

        Base64.Encoder encoder = Base64.getEncoder();
        String base64String = encoder.encodeToString(json.getBytes());
        System.out.println(base64String);
        CassandraConnection cassandraConnection = (CassandraConnection)  ctx.getBean(CassandraConnection.class);
        HRDataSourceFeedDAO hrDataSourceFeedDAO = (HRDataSourceFeedDAOFileImpl) ctx.getBean(HRDataSourceFeedDAOFileImpl.class);

        List<Employee> employeeList = null;
        try {
            employeeList = hrDataSourceFeedDAO.loadAllEmployees();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Employee employee2 : employeeList) {
            cassandraConnection.insertEmployee(employee2);

        }


        return null;
    }

}