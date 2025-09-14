package com.azeit.signup.dto;

import java.util.Set;

public class RegisterResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String phone;
    private String country;
    private Set<String> roles;

    public RegisterResponse(Long id, String firstName, String lastName, String email, String username, String phone, String country, Set<String> roles) {
        this.id = id; this.firstName = firstName; this.lastName = lastName; this.email = email; this.username = username;
        this.phone = phone; this.country = country; this.roles = roles;
    }

    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getPhone() { return phone; }
    public String getCountry() { return country; }
    public Set<String> getRoles() { return roles; }
}
