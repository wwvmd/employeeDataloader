package org.goelas;

import org.goelas.dto.Employee;

import java.io.IOException;
import java.util.List;

/**
 * Created by ash_g on 21/05/2017.
 */
public interface HRDataSourceFeedDAO {

    public List<Employee> loadAllEmployees() throws IOException;



//


}
