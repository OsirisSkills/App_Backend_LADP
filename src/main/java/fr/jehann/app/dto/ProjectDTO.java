package fr.jehann.app.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ProjectDTO extends BaseDTO {

    private String projectName;
    //private List<MemberDTO> projectMembers;
    private List<Long> projectMembersIds;
    private LocalDate startDate;
    private LocalDate endDate;
    //private List<ProjectFileDTO> files;
    private List<Long> filesIds;
    private String description;
    private int status;
    // private CategoryDTO category;
    private long categoryId;

    private String url;

    // ----- GETTERS & SETTERS -----


    public ProjectDTO(String projectName, List<Long> projectMembersIds, LocalDate startDate, LocalDate endDate, List<Long> filesIds, String description, int status, long categoryId, String url) {
        this.projectName = projectName;
        this.projectMembersIds = projectMembersIds;
        this.startDate = startDate;
        this.endDate = endDate;
        this.filesIds = filesIds;
        this.description = description;
        this.status = status;
        this.categoryId = categoryId;
        this.url = url;
    }

    public ProjectDTO() {
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Long> getProjectMembersIds() {
        return projectMembersIds;
    }

    public void setProjectMembersIds(List<Long> projectMembersIds) {
        this.projectMembersIds = projectMembersIds;
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

    public List<Long> getFilesIds() {
        return filesIds;
    }

    public void setFilesIds(List<Long> filesIds) {
        this.filesIds = filesIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}