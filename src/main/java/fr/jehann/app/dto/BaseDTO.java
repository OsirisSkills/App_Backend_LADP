package fr.jehann.app.dto;

public abstract class BaseDTO {
    protected long id;
    protected String version;

    // ----- GETTERS & SETTERS -----

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
