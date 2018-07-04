package org.goelas;

import com.datastax.driver.core.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.goelas.dto.Employee;
import org.goelas.util.DTOHelper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * Created by ash_g on 07/03/2017.
 */
@Component
public class CassandraConnection {

    org.slf4j.Logger logger = LoggerFactory.getLogger(CassandraConnection.class);

    private Cluster cluster;
    private Session session;

    @Value("${clusterName}")
    String clusterName;


    @Value("${clusterIP}")
    String clusterIP;


    @PostConstruct
    private void initializeConnnection() {
        logger.info("initialising connection");
        cluster = Cluster.builder()
                .withClusterName(clusterName)
                .addContactPoint(clusterIP)
                .build();

        session = cluster.connect();                                           // (2)

         executeQuery("select * from hr.employees");    // (3)
        //Row row = rs.one();
       //logger.info(row.getString("attributes"));

    }


    public void executeQuery(String query) {

        ResultSet rs = session.execute(query);
        logger.info("Query {} status {}", query, String.valueOf(rs.wasApplied()));

    }

/*
CREATE TABLE hr.employees (
    empid text PRIMARY KEY,
    attributes text,
    checksum bigint,
    last_modified timeuuid

    "insert into product (sku, description) values (:s, :d)");
 */
    public void insertEmployee(Employee employee) {

        DTOHelper<Employee> employeeDTOHelper = new DTOHelper<>(Employee.class);
        //Cassandra update is an insert
        PreparedStatement ps = session.prepare("update hr.employees set  attributes= :a, checksum=:c, last_modified_timeuuid=now(), last_modified_timestamp=toTimestamp(now()) where empId=:e if checksum != :c");
        BoundStatement boundStatement = ps.bind()
                .setString("e",employee.getGpn())
                .setString("a",employeeDTOHelper.toJson(employee))
                .setString("c",employeeDTOHelper.getCheckSum(employee));


        session.execute(boundStatement);





    }


    @PreDestroy
    private void closeConnection() {
        logger.info("closing connection");

        session.close();
        cluster.close();
    }






}
