package by.bsuir.rudko.archinc.entity;

import by.bsuir.rudko.archinc.enumeration.Gender;

import java.util.Objects;

/**
 * Created by jack on 19/04/17.
 *
 * @author Jack Veromeyev
 */
public class Employee extends Entity {

    private String firstName;
    private String secondName;
    private String lastName;

    private int age;
    private Gender gender;

    private int experienceYears;
    private String phoneNumber;
    private int salaryPerDay;

    private int qualificationId;
    private int educationId;
    private int addressId;
    private int orderId;

    public Employee(int id, String firstName, String secondName,
                    String lastName, int age, Gender gender,
                    int experienceYears, String phoneNumber,
                    int salaryPerDay, int qualificationId,
                    int educationId, int addressId,
                    int orderId) {
        super(id);
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.experienceYears = experienceYears;
        this.phoneNumber = phoneNumber;
        this.salaryPerDay = salaryPerDay;
        this.qualificationId = qualificationId;
        this.educationId = educationId;
        this.addressId = addressId;
        this.orderId = orderId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSalaryPerDay() {
        return salaryPerDay;
    }

    public void setSalaryPerDay(int salaryPerDay) {
        this.salaryPerDay = salaryPerDay;
    }

    public int getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(int qualificationId) {
        this.qualificationId = qualificationId;
    }

    public int getEducationId() {
        return educationId;
    }

    public void setEducationId(int educationId) {
        this.educationId = educationId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return age == employee.age &&
                experienceYears == employee.experienceYears &&
                salaryPerDay == employee.salaryPerDay &&
                qualificationId == employee.qualificationId &&
                educationId == employee.educationId &&
                addressId == employee.addressId &&
                orderId == employee.orderId &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(secondName, employee.secondName) &&
                Objects.equals(lastName, employee.lastName) &&
                gender == employee.gender &&
                Objects.equals(phoneNumber, employee.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, secondName, lastName,
                age, gender, experienceYears, phoneNumber, salaryPerDay,
                qualificationId, educationId, addressId, orderId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("id=").append(getId());
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", secondName='").append(secondName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", age=").append(age);
        sb.append(", gender=").append(gender);
        sb.append(", experienceYears=").append(experienceYears);
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", salaryPerDay=").append(salaryPerDay);
        sb.append(", qualificationId=").append(qualificationId);
        sb.append(", educationId=").append(educationId);
        sb.append(", addressId=").append(addressId);
        sb.append(", orderId=").append(orderId);
        sb.append('}');
        return sb.toString();
    }
}
