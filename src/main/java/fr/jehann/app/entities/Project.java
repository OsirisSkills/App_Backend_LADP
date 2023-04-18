package fr.jehann.app.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Project extends BaseEntity{

    public Project() {
    }

    @Column(nullable = false, unique = true)
    private String projectName;

    @ManyToMany(mappedBy = "projectGroups")
    private List<Member> projectMembers;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany(mappedBy = "project")
    private List<ProjectFile> files;

    private String description;

    @Column(nullable = false)
    private Integer status;

    @OneToOne
    private Category category;

    private String url;

    @Override
    public String toString() {
        return "Project{" +
                "name='" + projectName + '\'' +
                ", projectMembers=" + projectMembers +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", files=" + files +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", category=" + category +
                ", url='" + url + '\'' +
                '}';
    }

    // ----- GETTERS & SETTERS -----

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Member> getProjectMembers() {
        return projectMembers;
    }

    public void setProjectMembers(List<Member> projectMembers) {
        this.projectMembers = projectMembers;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<ProjectFile> getFiles() {
        return files;
    }

    public void setFiles(List<ProjectFile> files) {
        this.files = files;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
