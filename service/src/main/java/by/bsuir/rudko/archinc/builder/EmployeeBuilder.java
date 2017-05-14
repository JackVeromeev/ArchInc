package by.bsuir.rudko.archinc.builder;

import by.bsuir.rudko.archinc.entity.Employee;
import by.bsuir.rudko.archinc.dao.EmployeeDAO;
import by.bsuir.rudko.archinc.dao.QualificationDAO;
import by.bsuir.rudko.archinc.entity.Qualification;
import by.bsuir.rudko.archinc.enumeration.Education;
import by.bsuir.rudko.archinc.enumeration.Gender;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jack on 27/04/17.
 *
 * @author Jack Veromeyev
 */
public class EmployeeBuilder extends AbstractBuilder {

    private Employee employee;
    private AddressBuilder addressBuilder;
    private Education education;
    private Qualification qualification;

    public EmployeeBuilder(Employee employee) {
        this.employee = employee;
    }

    /**
     * constructor for id of Employee entity in DB
     * @param id id of Employee in DB, 0 - new Employee
     * @throws BuilderException if employee with this id doesn't exist
     */
    public EmployeeBuilder(int id) throws BuilderException {
        if (id == 0) {
            this.employee = new Employee(0, "", "", "", 0, Gender.FEMALE, 0,
                    "", 0, 0, 0, 0, 0);
        } else {
            List<Employee> employees = new LinkedList<>();
            runReadingTransaction("EmployeeBuilder(id=" + id + ")",
                    connection -> {
                        Employee employee = EmployeeDAO.getInstance().
                                findById(id, connection);
                        employees.add(employee);
                    });
            if (employees.size() == 0) {
                throw new BuilderException("employee id=" + id + " not found");
            }
            this.employee = employees.get(0);
        }
    }

    public int getId() {
        return employee.getId();
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public String getQualification() {
        return qualification.getQualification();
    }

    public void setQualification(String qualification) {
        this.qualification.setQualification(qualification);
    }

    public String getStreet() {
        return this.addressBuilder.getStreet();
    }

    public void setStreet(String street) {
        this.addressBuilder.setStreet(street);
    }

    public int getHouse() {
        return this.addressBuilder.getHouse();
    }

    public void setHouse(int house) {
        this.addressBuilder.setHouse(house);
    }

    public String getCountry() {
        return addressBuilder.getCountry();
    }

    public void setCountry(String country) {
        this.addressBuilder.setCountry(country);
    }

    public String getRegion() {
        return addressBuilder.getRegion();
    }

    public void setRegion(String region) {
        this.addressBuilder.setRegion(region);
    }

    public String getTown() {
        return addressBuilder.getTown();
    }

    public void setTown(String town) {
        this.addressBuilder.setTown(town);
    }

    public String getFirstName() {
        return this.employee.getFirstName();
    }

    public void setFirstName(String firstName) {
        this.employee.setFirstName(firstName);
    }

    public String getSecondName() {
        return this.employee.getSecondName();
    }

    public void setSecondName(String secondName) {
        this.employee.setSecondName(secondName);
    }

    public String getLastName() {
        return this.employee.getLastName();
    }

    public void setLastName(String lastName) {
        this.employee.setLastName(lastName);
    }

    public int getAge() {
        return this.employee.getAge();
    }

    public void setAge(int age) {
        this.employee.setAge(age);
    }

    public Gender getGender() {
        return this.employee.getGender();
    }

    public void setGender(Gender gender) {
        this.employee.setGender(gender);
    }

    public int getExperienceYears() {
        return this.employee.getExperienceYears();
    }

    public void setExperienceYears(int experienceYears) {
        this.employee.setExperienceYears(experienceYears);
    }

    public String getPhoneNumber() {
        return this.employee.getPhoneNumber();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.employee.setPhoneNumber(phoneNumber);
    }

    public int getSalaryPerDay() {
        return this.employee.getSalaryPerDay();
    }

    public void setSalaryPerDay(int salaryPerDay) {
        this.employee.setSalaryPerDay(salaryPerDay);
    }

    public EmployeeBuilder qualification() throws BuilderException {
        if (this.employee.getQualificationId() == 0) {
            this.qualification = new Qualification(0, "");
        } else {
            List<Qualification> qualifications = new LinkedList<>();
            runReadingTransaction(
                    "EmployeeBuilder.qualification()",
                    connection -> {
                        Qualification qualification = QualificationDAO.
                                getInstance().findById(this.employee.getId(),
                                        connection);
                        qualifications.add(qualification);
            });
            if (qualifications.size() == 0) {
                throw new BuilderException("qualification with id="
                        + this.employee.getQualificationId() + " not found");
            }
            this.qualification = qualifications.get(0);
        }
        return this;
    }

    public EmployeeBuilder education() {
        if (this.employee.getEducationId() == 0) {
            this.education = Education.BASIC;
        } else {
            this.education = Education.getById(this.employee.getEducationId());
        }
        return this;
    }

    public EmployeeBuilder address() throws BuilderException {
        if (this.employee.getAddressId() == 0) {
            this.addressBuilder = new AddressBuilder(0).init();
        } else {
            this.addressBuilder =
                    new AddressBuilder(this.employee.getAddressId()).init();
        }
        return this;
    }

    public EmployeeBuilder init() throws BuilderException {
        return this.address().education().qualification();
    }
    @Override
    public void synchronizeWithDB() throws BuilderException {
        checkoutQualification();
        this.employee.setQualificationId(this.qualification.getId());

        this.employee.setEducationId(this.education.getId());

        this.addressBuilder.synchronizeWithDB();
        this.employee.setAddressId(this.addressBuilder.getId());
        if (this.employee.getId() == 0) {
            insertEmployee();
        } else {
            updateEmployee();
        }
    }

    /**
     * restores id for qualification name in qualification field of this class
     * @throws BuilderException if internal error occurred
     */
    private void checkoutQualification() throws BuilderException {
        if (this.qualification == null) {
            return;
        }
        try {
            // try to find this qualification in DB
            this.qualification = getQualificationByName(this.qualification.getQualification());
        } catch (BuilderException e) {
            int id = this.qualification.getId();
            insertQualification(this.qualification);
            this.qualification = getQualificationByName(this.qualification.getQualification());
            deleteQualification(id);
        }
    }

    private Qualification getQualificationByName(String name) throws BuilderException {
        List<Qualification> qualifications = new LinkedList<>();
        runReadingTransaction("AddressBuilder.getQualificationByName(" +
                        name + ")",
                connection -> qualifications.add(QualificationDAO.getInstance().
                        findByName(name, connection))
        );
        return qualifications.get(0);
    }

    private void insertQualification(Qualification qualification) throws BuilderException {
        runAtomicTransaction("AddressBuilder.insertQualification(" +
                        qualification.getQualification() + ")",
                connection ->
                        QualificationDAO.getInstance().insert(qualification,connection)
        );
    }

    private void deleteQualification(int id) {
        try {
            runAtomicTransaction("AddressBuilder.deleteQualification(id=" +
                            id + ")",
                    connection ->
                            QualificationDAO.getInstance().deleteById(id, connection)
            );
        } catch (BuilderException e) {
        }
    }

    private void insertEmployee() throws BuilderException {
        runAtomicTransaction("EmployeeBuilder.insert(name='"
                + this.employee.getFirstName() + " "
                + this.employee.getLastName() + "')",
                connection -> EmployeeDAO.getInstance().
                        insert(this.employee, connection)
        );
    }

    private void updateEmployee() throws BuilderException {
        runAtomicTransaction("EmployeeBuilder.update(name='"
                + this.employee.getFirstName() + " "
                + this.employee.getLastName() + "')",
                connection -> EmployeeDAO.getInstance().
                        update(this.employee, connection)
        );
    }
}
