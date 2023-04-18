package fr.jehann.app.dto;

import fr.jehann.app.entities.Project;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ProjectFileDTO extends BaseDTO{

    private String filename;
    private String path;
    private String fileType;
    private String size;
    private Project project_id;

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

    public Project getProject_id() {
        return project_id;
    }

    public void setProject_id(Long Project) {
        this.project_id = project_id;
    }

    public String getFullPath() {
        return path + filename;
    }

    public void setFullPath(String fullPath) {
        Path filePath = Paths.get(fullPath);
        Path path = filePath.getParent(); // chemin d'accès
        String fileName = filePath.getFileName().toString(); // nom de fichier
        this.path = path.toString();
        this.filename = fileName;
        System.out.println("Chemin d'accès: " + path);
        System.out.println("Nom de fichier: " + fileName);
    }
}
