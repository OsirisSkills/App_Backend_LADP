package fr.jehann.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.nio.file.Path;
import java.nio.file.Paths;

@Entity
public class ProjectFile extends BaseEntity{

    public ProjectFile() {
    }

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String fileType;

    @Column(nullable = false)
    private String size;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    // ----- GETTERS & SETTERS -----


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setFileFromFullPath(String fullPath) {
        Path filePath = Paths.get(fullPath);
        this.path = filePath.getParent().toString(); // chemin d'accès
        this.filename = filePath.getFileName().toString(); // nom de fichier
    }

    public String getFullPath() {
        return this.path + "/" + this.filename;
    }
}
