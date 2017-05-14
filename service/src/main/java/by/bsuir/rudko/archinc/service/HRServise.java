package by.bsuir.rudko.archinc.service;

import by.bsuir.rudko.archinc.builder.BuilderException;
import by.bsuir.rudko.archinc.builder.EmployeeBuilder;
import by.bsuir.rudko.archinc.dao.EmployeeDAO;
import by.bsuir.rudko.archinc.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jack on 01/05/17.
 *
 * @author Jack Veromeyev
 */
public class HRServise extends AbstractService{

    private final Logger LOG = LoggerFactory.getLogger(HRServise.class);

    private static HRServise instanse = null;

    private HRServise() {}

    public static synchronized HRServise getInstanse() {
        if (instanse == null) {
            instanse = new HRServise();
        }
        return instanse;
    }

    public List<EmployeeBuilder> getQualificatedEmployees()
            throws ServiceException {
        List<EmployeeBuilder> resultList = new LinkedList<>();
        List<Employee> employees = new LinkedList<>();
        runReadingTransaction("HRService.getQualifiedEmployees()",
                connection -> {
                    for (Employee employee : EmployeeDAO.getInstance()
                            .readAll(connection)) {
                        employees.add(employee);
                    }
        });
        for (Employee employee : employees) {
            try {
                resultList.add(new EmployeeBuilder(employee).qualification());
            } catch (BuilderException e) {
                throw new ServiceException("in builder", e);
            }
        }
        return resultList;
    }
}
