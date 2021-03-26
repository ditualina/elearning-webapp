package com.alinadev.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by: Alina Ditu
 * Date: 5/20/17
 */
public class UserDto {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 20, message = "Your password must between 6 and 15 characters")
    private String password;

    @NotNull
    @Email
    @NotEmpty(message = "Please enter your email addresss.")
    private String email;

    private String address;

    private String matchingPassword;

    private Integer scholarYear;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getScholarYear() {
        return scholarYear;
    }

    public void setScholarYear(Integer scholar_year) {
        this.scholarYear = scholar_year;
    }
}
