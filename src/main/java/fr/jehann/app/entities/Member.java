package fr.jehann.app.entities;

import fr.jehann.app.enums.MemberRole;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Member extends BaseEntity{

    public Member() {
    }

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false, unique = true)
    private String email;

    private String address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    private Date availabilityDate;

    @ManyToMany
    private List<Project> projectGroups;

    // ----- GETTERS & SETTERS -----

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public MemberRole getRole() {
        return role;
    }

    public void setRole(MemberRole role) {
        this.role = role;
    }

    public Date getAvailabilityDate() {
        return availabilityDate;
    }

    public void setAvailabilityDate(Date availabilityDate) {
        this.availabilityDate = availabilityDate;
    }

    public List<Project> getProjectGroups() {
        return projectGroups;
    }

    public void setProjectGroups(List<Project> projectGroups) {
        this.projectGroups = projectGroups;
    }
}
