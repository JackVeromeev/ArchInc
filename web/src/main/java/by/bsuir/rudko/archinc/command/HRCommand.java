package by.bsuir.rudko.archinc.command;

import by.bsuir.rudko.archinc.entity.User;
import by.bsuir.rudko.archinc.builder.BuilderException;
import by.bsuir.rudko.archinc.builder.EmployeeBuilder;
import by.bsuir.rudko.archinc.enumeration.Education;
import by.bsuir.rudko.archinc.enumeration.Gender;
import by.bsuir.rudko.archinc.jspname.JSPName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jack on 28/04/17.
 *
 * @author Jack Veromeyev
 */
public class HRCommand extends Command {

    private final Logger LOG = LoggerFactory.getLogger(HRCommand.class);

    private static HRCommand instance = null;

    private HRCommand() {}

    public static synchronized HRCommand getInstance() {
        if (instance == null) {
            instance = new HRCommand();
        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response,
                          User user) {
        String jspName = JSPName.LOGIN;
        String action = request.getParameter("action");
        if ("create_employee".equals(action)) {
            jspName = addEmployee(request);
        } else if ("submit_add".equals(action)) {
            jspName = submitAdd(request);
        } else if ("open_edit_employee".equals(action)) {
            jspName = editEmployee(request);
        } else if ("submit_edit".equals(action)) {
            jspName = submitEdit(request);
        } else if ("cancel".equals(action)) {
            return JSPName.HR;
        }
        return jspName;
    }

    private String submitAdd(HttpServletRequest request) {
        try {

            EmployeeBuilder employeeBuilder = new EmployeeBuilder(0).init();
            fillEmployeeBuilderFromRequest(employeeBuilder, request);
            employeeBuilder.synchronizeWithDB();

        } catch (BuilderException e) {
            LOG.error("in creating employee", e);
        }
        return JSPName.HR;
    }

    private void fillEmployeeBuilderFromRequest(EmployeeBuilder employeeBuilder,
                                                HttpServletRequest request)
            throws BuilderException {
        employeeBuilder.setFirstName(request.getParameter("first_name"));
        employeeBuilder.setSecondName(request.getParameter("second_name"));
        employeeBuilder.setLastName(request.getParameter("last_name"));

        employeeBuilder.setAge(
                Integer.parseInt(request.getParameter("age")));

        employeeBuilder.setGender(
                Gender.valueOf(request.getParameter("sex").toUpperCase()));

        employeeBuilder.setQualification(
                request.getParameter("qualification"));

        employeeBuilder.setExperienceYears(
                Integer.parseInt(request.getParameter("experience")));

        String edu = request.getParameter("education").toUpperCase();
        if (edu.equals("PROFESSIONAL-TECHNICAL")) {
            employeeBuilder.setEducation(Education.PROFESSIONAL_TECH);
        } else if (edu.equals("SPECIAL-AVERAGE")) {
            employeeBuilder.setEducation(Education.SPECIAL_AVERAGE);
        } else {
            employeeBuilder.setEducation(Education.valueOf(edu));
        }

        employeeBuilder.setPhoneNumber(
                request.getParameter("phone_number"));

        employeeBuilder.setSalaryPerDay(
                Integer.parseInt(request.getParameter("salary")));

        employeeBuilder.setCountry(request.getParameter("country"));
        employeeBuilder.setRegion(request.getParameter("region"));
        employeeBuilder.setTown(request.getParameter("town"));
        employeeBuilder.setStreet(request.getParameter("street"));
        employeeBuilder.setHouse(Integer.parseInt(
                request.getParameter("house")));
    }

    private String addEmployee(HttpServletRequest request) {
        return JSPName.ADD_EMPLOYEE;
    }

    private String editEmployee(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        EmployeeBuilder employeeBuilder;
        try {
            employeeBuilder = new EmployeeBuilder(id).init();
            request.setAttribute("employee", employeeBuilder);
        } catch (BuilderException e) {
            LOG.error("in getting info about employee ", e);
            return JSPName.HR;
        }
        LOG.info("id of the sent employee = " + employeeBuilder.getId());
        return JSPName.EDIT_EMPLOYEE;
    }

    private String submitEdit(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        LOG.info("id of employee = " + id);
        EmployeeBuilder employeeBuilder;
        try {
            employeeBuilder = new EmployeeBuilder(id).init();
            fillEmployeeBuilderFromRequest(employeeBuilder, request);
            employeeBuilder.synchronizeWithDB();
        } catch (BuilderException e) {
            LOG.error("in changing info about employee", e);
        }
        return JSPName.HR;
    }
}
