package fr.jehann.app.dto;

import fr.jehann.app.enums.MemberRole;

import java.util.Date;
import java.util.List;

public class MemberDTO extends BaseDTO {

    private String lastName;
    private String firstName;
    private String email;
    private String address;
    private MemberRole role;
    private Date availabilityDate;
    private List<Long> projectIds;

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

    public List<Long> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(List<Long> projectIds) {
        this.projectIds = projectIds;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", role=" + role +
                ", availabilityDate=" + availabilityDate +
                ", projectIds=" + projectIds +
                ", id=" + id +
                ", version='" + version + '\'' +
                '}';
    }
}
