package fr.jehann.app.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Category extends BaseEntity{

    public Category() {
    }

    @Column(nullable = false, unique = true)
    private String name;
    private String description;

    // ----- GETTERS & SETTERS -----

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
