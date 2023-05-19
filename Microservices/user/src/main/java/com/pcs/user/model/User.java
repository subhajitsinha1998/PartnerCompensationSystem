package com.pcs.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(
    name = "pcs_user", 
    uniqueConstraints = @UniqueConstraint(columnNames = { "email" })
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeId;
    private String email;
    private String firstName;
    private String lastName;
    private String location;
    private String jobTitle;
    private String department;
    @Enumerated(EnumType.STRING)
    private Role role;
}
