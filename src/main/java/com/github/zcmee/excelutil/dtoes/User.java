package com.github.zcmee.excelutil.dtoes;

import java.util.Date;

public class User {

    private String firstName;
    private String lastName;
    private int age;
    private Date dateSigningContract;

    public User() {
        // default constructor
    }

    public User(String firstName, String lastName, int age, Date dateSigningContract) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.dateSigningContract = dateSigningContract;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public Date getDateSigningContract() {
        return dateSigningContract;
    }

    public void setDateSigningContract(Date dateSigningContract) {
        this.dateSigningContract = dateSigningContract;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", dateSigningContract=" + dateSigningContract +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        return dateSigningContract != null ? dateSigningContract.equals(user.dateSigningContract) : user.dateSigningContract == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (dateSigningContract != null ? dateSigningContract.hashCode() : 0);
        return result;
    }

}
